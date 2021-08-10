package com.costa.luiz.generics.covariance_contravariance.flight;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
class Superior extends Suite {

    public static final Superior DEFAULT_INSTANCE = new Superior(List.of("Pelé", "Maradona", "Cruiff"));

    private List<String> passengers;

    @Override
    public String toString() {
        return "Superior{" +
                "passengers=" + passengers +
                '}';
    }
}
