package com.costa.luiz.quarkus.domain.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Path("/api/quarkus/v1/items")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {

    private static final Logger log = Logger.getLogger(ItemResource.class.getName());

    @Inject
    ItemRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Item> findAll() {
        return repository.listAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Item findOne(@PathParam("id") Long id) {
        return repository.findById(id);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        repository.deleteById(id);
        return Response.accepted(id + " deleted").build();
    }

    @POST
    @Transactional
    public Response add(Item item) {
        repository.persist(item);
        return Response.created(null).build();
    }

    @PUT
    @Transactional
    @Path("/id/{id}/name/{name}/description/{description}")
    public Response update(@PathParam("id") Long id,
                           @PathParam("name") String name,
                           @PathParam("description") String description) {
        Item item = repository.findById(id);
        if (isNull(item)) {
            throw new WebApplicationException("Item with id of " + id + " does not exist.", 404);
        }
        if (nonNull(name)) item.name = name;
        if (nonNull(description)) item.description = description;
        repository.persist(item);
        return Response.accepted(item).build();
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception exception) {
            log.error("Failed to handle request", exception);

            int httpStatus = 500;
            if (exception instanceof WebApplicationException) {
                httpStatus = ((WebApplicationException) exception).getResponse().getStatus();
            }

            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson.put("exceptionType", exception.getClass().getName());
            exceptionJson.put("httpStatus", httpStatus);

            if (exception.getMessage() != null) {
                exceptionJson.put("error", exception.getMessage());
            }

            return Response.status(httpStatus)
                    .entity(exceptionJson.toString())
                    .build();
        }

    }
}
