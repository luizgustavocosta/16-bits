package com.costa.luiz.generics.covariance_contravariance.flight;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
class FirstClass implements SeatConfiguration {

    public static final FirstClass DEFAULT_INSTANCE = new FirstClass(List.of("Messi", "MBappé", "Neymar"));

    private List<String> passengers;

    @Override
    public List<String> passengers() {
        return passengers;
    }

    @Override
    public String toString() {
        return "FirstClass{" +
                "passengers=" + passengers +
                '}';
    }
}
