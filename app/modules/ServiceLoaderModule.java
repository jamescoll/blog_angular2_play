package modules;


import com.google.inject.AbstractModule;
import services.JsonWebTokenService;
import services.JsonWebTokenServiceImpl;


public class ServiceLoaderModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(JsonWebTokenService.class).to(JsonWebTokenServiceImpl.class).asEagerSingleton();
    }
}