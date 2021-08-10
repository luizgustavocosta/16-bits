package com.costa.luiz.generics.covariance_contravariance.flight;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Suite extends FirstClass {

    public static final Suite DEFAULT_INSTANCE = new Suite(List.of("Coutinho", "Pique", "Suarez"));

    private List<String> passengers;

    @Override
    public String toString() {
        return "Suite{" +
                "passengers=" + passengers +
                '}';
    }
}
