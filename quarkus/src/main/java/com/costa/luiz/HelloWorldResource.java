package com.costa.luiz;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.time.LocalDateTime;

@Path("/api/quarkus/helloworld")
public class HelloWorldResource {

    @GET
    @Path("/{name}")
    public String sayHello(@PathParam("name") String name) {
        return "Hello "+name+" from Quarkus at "+ LocalDateTime.now()+"\n";
    }
}
