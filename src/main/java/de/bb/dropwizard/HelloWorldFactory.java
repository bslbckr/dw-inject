package de.bb.dropwizard;

import javax.inject.Singleton;

import org.glassfish.hk2.api.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.bb.dropwizard.logic.HelloWorld;

@Singleton
public class HelloWorldFactory implements Factory<HelloWorld> {
    private final static Logger LOGGER = LoggerFactory.getLogger(HelloWorldFactory.class);
    private static int counter = 0;

    public HelloWorld provide() {
        HelloWorldProvider.LOGGER.info("provide called");
        HelloWorld result = new HelloWorld(++counter);
        return result;
    }

    public void dispose(HelloWorld instance) {
        LOGGER.info("dispose called for {}", instance.sayHello());
    }
}