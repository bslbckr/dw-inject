package de.bb.dropwizard.logic;

import java.util.Objects;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Named;

import org.glassfish.hk2.api.ServiceLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldCallable implements Callable<String> {

    private final static Logger LOGGER = LoggerFactory.getLogger(HelloWorldCallable.class);
    @Inject
    @Named("callableHello") // necessary as otherwise the serviceLocator throws
                            // an exception.
    HelloWorld helloWorld;

    private final ServiceLocator sl;

    public HelloWorldCallable(ServiceLocator serviceLoc) {
        this.sl = Objects.requireNonNull(serviceLoc);
    }

    public String call() throws Exception {
        this.sl.inject(this);
        LOGGER.info(this.helloWorld.sayHello());
        return this.helloWorld.sayHello();
    }

}
