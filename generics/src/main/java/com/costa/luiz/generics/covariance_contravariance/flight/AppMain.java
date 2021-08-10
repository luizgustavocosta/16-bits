package com.costa.luiz.generics.covariance_contravariance.flight;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

@Slf4j
public class AppMain {

    public static void main(String[] args) {
        FlightService<FirstClass, Gourmet> service = new FlightService<>();

        List<FirstClass> firstClassList = Collections.singletonList(FirstClass.DEFAULT_INSTANCE);
        List<Suite> suiteList = Collections.singletonList(Suite.DEFAULT_INSTANCE);
        List<Superior> superiorList = Collections.singletonList(Superior.DEFAULT_INSTANCE);

        service.covariance(firstClassList);
        service.covariance(suiteList);
        service.covariance(superiorList);

        log.info("Contravariance");
        service.contravariance(firstClassList);
        // firstClassAndGourmet.contravariance(suiteList); // Not allowed
        // firstClassAndGourmet.contravariance(superiorList); // Not allowed

        List<Gourmet> gourmets = Collections.singletonList(Gourmet.INSTANCE);
//        List<Degustacion> degustacions = Collections.singletonList(Degustacion.INSTANCE); // Not Allowed
        List<SpecialMeal> specialMeals = Collections.singletonList(Degustacion.INSTANCE);
        service.contravarianceForTheSecondType(specialMeals);
        service.contravarianceForTheSecondType(gourmets);
        // firstClassAndGourmet.contravarianceForTheSecondType(degustacions); // Not allowed

    }
}
