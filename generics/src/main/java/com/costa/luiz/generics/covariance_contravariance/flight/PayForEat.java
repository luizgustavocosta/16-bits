package com.costa.luiz.generics.covariance_contravariance.flight;

import java.util.List;

public class PayForEat extends RegularMeal {

    @Override
    public List<String> menu() {
        return List.of("Water", "Chips", "Hot dog");
    }
}
