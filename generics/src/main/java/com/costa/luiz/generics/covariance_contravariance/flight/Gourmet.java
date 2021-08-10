package com.costa.luiz.generics.covariance_contravariance.flight;

import java.util.List;

class Gourmet extends SpecialMeal {

    public static final Gourmet INSTANCE = new Gourmet();

    @Override
    public List<String> menu() {
        return List.of("Red whine", "Beer", "Water", "Coca Cola", "Bread",
                "Paella", "Lasagna", "Ice Cream", "Coffee");
    }
}
