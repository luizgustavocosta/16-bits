package com.costa.luiz.generics.contravariance;

import com.costa.luiz.generics.contravariance.consumer.Human;
import com.costa.luiz.generics.contravariance.consumer.Panda;
import com.costa.luiz.generics.contravariance.consumer.PowerPlant;
import com.costa.luiz.generics.contravariance.hierarchy.Bamboo;
import com.costa.luiz.generics.contravariance.hierarchy.SourceOfEnergy;
import com.costa.luiz.generics.contravariance.hierarchy.Vegetable;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AppMain {

    public static void main(String[] args) {

        Panda panda = new Panda();
        List<Bamboo> pandasFood = new ArrayList<>();
        pandasFood.add(Bamboo.CHINESE);
        pandasFood.add(Bamboo.BRAZILIAN);
        panda.apply(pandasFood);

        List<Vegetable> vegetables = new ArrayList<>(Vegetable.ALL);
        vegetables.add(Bamboo.CHINESE);
        vegetables.add(Bamboo.BRAZILIAN);
        Human human = new Human();
        human.apply(vegetables);

        List<SourceOfEnergy> sourceOfEnergy = new ArrayList<>();
        sourceOfEnergy.add(SourceOfEnergy.ETHANOL);
        sourceOfEnergy.addAll(vegetables);
        sourceOfEnergy.addAll(pandasFood);

        PowerPlant powerPlant = new PowerPlant();
        powerPlant.apply(sourceOfEnergy);


        //                     contravariance and covariance
        //Stream map(Function<? super T, ? extends R> mapper);
//        List<String> aList = null;
//        List<? extends Object> covariantList = aList;
//        List<? super String> contravariantList = aList;
    }
}
