package de.bb.dropwizard;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.bb.dropwizard.logic.HelloWorld;

public class HelloWorldProvider extends AbstractBinder {
    final static Logger LOGGER = LoggerFactory.getLogger(HelloWorldFactory.class);

    @Override
    protected void configure() {

        this.bindFactory(HelloWorldFactory.class).to(HelloWorld.class).in(RequestScoped.class);
        InjectionApplication.LOGGER.info("HelloWorldProvider configured");
    }

}