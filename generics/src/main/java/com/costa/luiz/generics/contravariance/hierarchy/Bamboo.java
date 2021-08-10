package com.costa.luiz.generics.contravariance.hierarchy;

import lombok.Getter;

@Getter
public class Bamboo extends Vegetable {

    public static final Bamboo CHINESE = new Bamboo("Chinese origin");
    public static final Bamboo BRAZILIAN = new Bamboo("Brazilian origin");

    public Bamboo(String payload) {
        super(payload);
    }

    @Override
    public String toString() {
        return "Bamboo{" +
                "payload='" + getPayload() + '\'' +
                '}';
    }
}
