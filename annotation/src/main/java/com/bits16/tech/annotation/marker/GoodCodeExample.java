package com.bits16.tech.annotation.marker;

import java.util.ArrayList;
import java.util.List;

@UseAsExample
public class GoodCodeExample {

    private final List<String> actors;

    public GoodCodeExample(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getActors() {
        return new ArrayList<>(actors);
    }
}
