package com.costa.luiz;

import com.costa.luiz.domain.HelloService;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.time.LocalDateTime;
import java.util.List;

@Path("/api/quarkus/helloworld")
public class HelloWorldResource {

    @Inject
    HelloService helloService;

    @ConfigProperty(name = "quarkus.http.port", defaultValue="-99")
    String port;

    @ConfigProperty(name = "quarkus.http.host", defaultValue="unknown")
    String host;

    @GET
    @Path("/{name}")
    public String sayHello(@PathParam("name") String name) {
        return String.format("Quarkus saying 'Hello to %s', from %s:%s. Message created on %s",
                name,
                host,
                port,
                LocalDateTime.now());
    }

    @GET
    @Path("/auction")
    public List auction() {
        return helloService.findAll();
    }
}
