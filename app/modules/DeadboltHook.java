package modules;

import be.objectify.deadbolt.java.cache.HandlerCache;
import play.api.Configuration;
import play.api.Environment;
import play.api.inject.Binding;
import play.api.inject.Module;
import scala.collection.Seq;
import security.DeadboltBaseHandlerCache;

import javax.inject.Singleton;

public class DeadboltHook extends Module {

    @Override
    public Seq<Binding<?>> bindings(Environment environment, Configuration configuration) {
        return seq(bind(HandlerCache.class).to(DeadboltBaseHandlerCache.class).in(Singleton.class));
    }

}
