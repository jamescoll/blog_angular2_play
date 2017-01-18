package security;

import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.ExecutionContextProvider;
import be.objectify.deadbolt.java.cache.HandlerCache;
import play.api.libs.concurrent.Execution;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;


@Singleton
public class DeadboltBaseHandlerCache implements HandlerCache {

    private final DeadboltHandler defaultHandler;
    private final Map<String, DeadboltHandler> handlers = new HashMap<>();

    @Inject
    public DeadboltBaseHandlerCache(final ExecutionContextProvider executionContextProvider) {

        defaultHandler = new DeadboltBaseHandler(executionContextProvider);

        handlers.put(DeadboltHandlerKeys.DEFAULT.key, defaultHandler);
    }


    @Override
    public DeadboltHandler apply(final String key) {
        return handlers.get(key);
    }

    @Override
    public DeadboltHandler get() {
        return defaultHandler;
    }
}
