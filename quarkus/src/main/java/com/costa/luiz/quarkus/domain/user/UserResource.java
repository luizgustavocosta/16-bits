package com.costa.luiz.quarkus.domain.user;


import com.costa.luiz.quarkus.domain.item.Item;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Path("/api/quarkus/v1/users")
public class UserResource {

    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> users() {
        return User.listAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User oneUserBy(@PathParam("id") Integer id) {
        return User.findById(id);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        User.deleteById(id);
        return Response.accepted(id).build();
    }

    @POST
    @Transactional
    public Response add(User user) {
        User.persist(user);
        return Response.created(null).build();
    }

    @PUT
    @Transactional
    @Path("/id/{id}/name/{name}/lastName/{lastName}")
    public Response update(@PathParam("id") Integer id,
                           @PathParam("name") String name,
                           @PathParam("lastName") String lastName) {
        User user = User.findById(id);
        if (isNull(user)) {
            throw new WebApplicationException("User with id of " + id + " does not exist.", 404);
        }
        if (nonNull(name)) user.name = name;
        if (nonNull(lastName)) user.lastName = lastName;
        User.persist(user);
        return Response.accepted(user).build();
    }
}