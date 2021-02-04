package com.costa.luiz.quarkus.domain.item;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalTime;
import java.util.List;

@Path("/api/quarkus/v1/items")
public class ItemResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Item> findAll() {
        Item item = new Item();
        item.name = "Item";
        item.lastName = LocalTime.now().toString();
        item.persistAndFlush();
        return Item.listAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Item findOne(@PathParam("id") Integer id) {
        return Item.findById(id);
    }
}
