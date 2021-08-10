package com.costa.luiz.generics.contravariance.hierarchy;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
public class Vegetable extends SourceOfEnergy {

    public static final Vegetable CARROT = new Vegetable("Carrot, Carota sativa, Daucus sativus");
    public static final Vegetable LETTUCE = new Vegetable("Lettuce, Lactuca scariola, L. scariola");
    public static final List<Vegetable> ALL = List.of(CARROT, LETTUCE);

    public Vegetable(String payload) {
        super(payload);
    }

    @Override
    public String toString() {
        return "Vegetable{" +
                "payload='" + getPayload() + '\'' +
                '}';
    }
}
