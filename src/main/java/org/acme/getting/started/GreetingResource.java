package org.acme.getting.started;

import java.nio.Buffer;
import java.util.Observable;
import java.util.stream.Stream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        Streaming streaming = new Streaming("/home/bahl/Quarkus/data.txt");
        streaming.imperative();
        return "Hello RESTEasy";
    }

    @GET
    @Path("file")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> fileReader() {
        System.out.println("Started and Reading the file");

        Streaming streaming = new Streaming("/home/bahl/Quarkus/data.txt");
        // streaming.imperative();

        Uni.createFrom().item(streaming)
        .subscribe().with((item)->streaming.imperative());

        System.out.println("File Read Successfully");
        return Uni.createFrom().item("Completed");

    }

    @GET
    @Path("reactive")
    @Produces(MediaType.TEXT_PLAIN)
    public void reactive() {
        System.out.println("Started reading the file..................");
        Streaming streaming = new Streaming("/home/bahl/Quarkus/data.txt");
        streaming.reactive();
        System.out.println("Finished reading the file..................");
        System.out.println("Hello WOrld");
    }
}