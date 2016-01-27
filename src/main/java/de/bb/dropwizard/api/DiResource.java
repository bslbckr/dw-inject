package de.bb.dropwizard.api;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.bb.dropwizard.logic.HelloWorld;

@Path("/api")

public class DiResource {

    private final static Logger LOGGER = LoggerFactory.getLogger(DiResource.class);
    /*
     * we inject two instances to ensure that both are the same. This is only
     * the case if the instances are created as singletons or in the
     * RequestScope. However, if they were created as singletons the
     * "instance number" would not change between two requests
     */
    @Inject
    HelloWorld hw;
    @Inject
    HelloWorld otherHw;

    public DiResource() {
        LOGGER.info("DiResource created!");
    }

    @GET
    @Path("say")
    @Produces(MediaType.APPLICATION_JSON)
    public ReturnType saySomething(@Context @NotNull HttpServletRequest request) {
        final ReturnType result = new ReturnType();
        result.first = this.hw.sayHello();
        result.second = this.otherHw.sayHello();
        result.id = request.getSession(true).getId();
        return result;
    }

    public class ReturnType {
        public String first;
        public String second;
        public String id;
    }
}
