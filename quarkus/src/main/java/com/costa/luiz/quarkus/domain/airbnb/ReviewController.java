package com.costa.luiz.quarkus.domain.airbnb;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/api/quarkus/v1/airbnb")
public class ReviewController {

    @Inject
    MongoClient mongoClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Document> list(){
        List<Document> list = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();
        try {
            while (cursor.hasNext()) {
                list.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    private MongoCollection getCollection(){
        return mongoClient.getDatabase("sample_airbnb").getCollection("listingsAndReviews");
    }
}
