package com.costa.luiz.quarkus.domain.auction;


import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.ZonedDateTime;
import java.util.List;

@Path("/api/quarkus/v1/auctions")
public class AuctionResource {

    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public List<Auction> findAll() {
        Auction auction = new Auction();
        auction.status = "Open";
        auction.startDate = ZonedDateTime.now();
        auction.endDate = ZonedDateTime.now().plusDays(2);
        auction.persistAndFlush();
        return Auction.listAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Auction findOne(@PathParam("id") Integer id) {
        return Auction.findById(id);
    }
}
