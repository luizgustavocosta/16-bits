package com.costa.luiz.generics.covariance_contravariance.flight;

import java.util.List;

class ChefChoice extends SpecialMeal {

    public static final ChefChoice INSTANCE = new ChefChoice();

    @Override
    public List<String> menu() {
        return List.of("Rice", "Beans", "Eggs", "TBone");
    }
}
