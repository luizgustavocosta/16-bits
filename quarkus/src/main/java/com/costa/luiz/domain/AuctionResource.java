package com.costa.luiz.domain;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/quarkus/auction")
public class AuctionResource {

    @Inject
    AuctionService helloService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List auction() {
        return helloService.findAll();
    }
}
