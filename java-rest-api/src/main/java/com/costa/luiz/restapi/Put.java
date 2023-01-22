package com.costa.luiz.restapi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Put {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newBuilder().build();
        String json = "{\"name\":\"Luiz\",\"age\":40}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response ->\n" + response.body());
    }
}

