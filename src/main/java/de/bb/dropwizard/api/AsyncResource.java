package de.bb.dropwizard.api;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.hk2.api.ServiceLocator;

import de.bb.dropwizard.logic.HelloWorldCallable;

@Path("/api")
public class AsyncResource {

    @Inject
    ExecutorService executor;
    @Inject
    ServiceLocator serviceLoc;

    @GET
    @Path("/say-async")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayAsync() {
        Callable<String> hwCall = new HelloWorldCallable(this.serviceLoc);

        this.executor.submit(hwCall);
        return Response.ok().entity("HelloWorldCallable submitted!").build();
    }
}
