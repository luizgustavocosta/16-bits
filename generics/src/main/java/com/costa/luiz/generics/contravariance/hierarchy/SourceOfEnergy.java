package com.costa.luiz.generics.contravariance.hierarchy;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SourceOfEnergy {

    public static final SourceOfEnergy ETHANOL = new SourceOfEnergy("From sugar cane");

    private final String payload;

    @Override
    public String toString() {
        return "SourceOfEnergy{" +
                "payload='" + payload + '\'' +
                '}';
    }
}
