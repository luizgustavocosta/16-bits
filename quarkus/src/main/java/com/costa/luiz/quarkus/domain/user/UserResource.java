package com.costa.luiz.quarkus.domain.user;


import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/api/quarkus/v1/users")
public class UserResource {

    AtomicInteger idGenerator = new AtomicInteger();

    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findAll() {
        User user = new User();
        user.name = "Mock";
        user.lastName = LocalDateTime.now().toString();
        user.persist();
        return User.listAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findOne(@PathParam("id") Integer id) {
        return User.findById(id);
    }
}
