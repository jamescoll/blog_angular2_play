package security;

import be.objectify.deadbolt.java.AbstractDeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import be.objectify.deadbolt.java.ExecutionContextProvider;
import be.objectify.deadbolt.java.models.Subject;
import com.google.inject.Inject;
import models.User;
import play.mvc.Http;
import play.mvc.Result;
import services.JsonWebTokenServiceImpl;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;


public class DeadboltBaseHandler extends AbstractDeadboltHandler {

    public DeadboltBaseHandler (ExecutionContextProvider ecProvider)
    {
        super(ecProvider);
    }

    @Inject
    JsonWebTokenServiceImpl jwtService;


    public CompletionStage<Optional<Result>> beforeAuthCheck(final Http.Context context)
    {
        return CompletableFuture.completedFuture(Optional.empty());
    }

    public CompletionStage<Optional<? extends Subject>> getSubject(final Http.Context context)
    {
        // todo this needs to be securely tested as we are not currently dealing with JWT on the angular side

        String token = jwtService.getTokenFromContext(context);

        return CompletableFuture.supplyAsync(() -> Optional.ofNullable(User.findByUserName(jwtService.getUser(token))),
                (Executor) executionContextProvider.get());
    }

    public CompletionStage<Optional<DynamicResourceHandler>> getDynamicResourceHandler(final Http.Context context)
    {
        return CompletableFuture.completedFuture(Optional.of(new DeadboltBaseDynamicResourceHandler()));
    }

    @Override
    public CompletionStage<Result> onAuthFailure(final Http.Context context,
                                                 final Optional<String> content)
    {
        // todo refactor this so it returns a view representing a failed access - some view.accessFailed type view
        // see commented line for an example of how this might be handled
        //return CompletableFuture.completedFuture(ok(accessFailed.render()));
        return CompletableFuture.completedFuture(redirect("http://localhost:9000/s_login"));
    }

}
