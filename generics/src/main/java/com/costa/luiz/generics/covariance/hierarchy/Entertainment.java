package com.costa.luiz.generics.covariance.hierarchy;

public class Entertainment implements Activity{

    @Override
    public void start() {
        System.out.println("Generic activity");
    }
}
