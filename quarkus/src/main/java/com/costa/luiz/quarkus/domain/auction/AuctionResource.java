package com.costa.luiz.quarkus.domain.auction;


import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.jboss.logging.Logger.Level.INFO;

@Path("/api/quarkus/v1/auctions")
public class AuctionResource {

    private static final Logger log = Logger.getLogger(AuctionResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Auction> findAll() {
        return Auction.listAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Auction findOne(@PathParam("id") Integer id) {
        log.log(INFO, "Looking for auction ["+id+"]");
        return Auction.findById(id);
    }

}
