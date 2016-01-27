package de.bb.dropwizard;

import org.eclipse.jetty.server.session.SessionHandler;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.bb.dropwizard.api.AsyncResource;
import de.bb.dropwizard.api.DiResource;
import de.bb.dropwizard.logic.HelloWorld;
import io.dropwizard.Application;
import io.dropwizard.jersey.sessions.SessionFactoryProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class InjectionApplication extends Application<InjectionConfig> {
    final static Logger LOGGER = LoggerFactory.getLogger(InjectionApplication.class);

    public static void main(String[] args) throws Exception {
        new InjectionApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<InjectionConfig> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public void run(InjectionConfig conf, Environment env) throws Exception {
        env.jersey().register(SessionFactoryProvider.class);
        env.servlets().setSessionHandler(new SessionHandler());
        // env.jersey().register(new HelloWorldProvider());
        env.jersey().register(DiResource.class);
        env.jersey().register(AsyncResource.class);
        env.jersey().register(new AbstractBinder() {

            @Override
            protected void configure() {
                this.bindFactory(HelloWorldFactory.class).to(HelloWorld.class).in(RequestScoped.class);
                this.bindFactory(HelloWorldFactory.class).to(HelloWorld.class).named("callableHello");
            }
        });
    }

}
