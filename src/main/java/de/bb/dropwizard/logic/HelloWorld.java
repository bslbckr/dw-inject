package de.bb.dropwizard.logic;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class HelloWorld {

    private final static Logger LOGGER = LoggerFactory.getLogger(HelloWorld.class);

    private final int number;

    public HelloWorld(int instanceCount) {
        this.number = instanceCount;
    }

    public String sayHello() {
        return String.format("Hello, %s (instance %d)", "World!", this.number);
    }

    // @PreDestroy
    // public void destroy() {
    // LOGGER.info("HelloWorld instance {} destroyed!",number);
    // }
}
