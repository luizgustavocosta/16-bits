package com.costa.luiz.generics.covariance.hierarchy;

public class Music extends Entertainment implements Activity {

    @Override
    public void start() {
        System.out.println("Highway to hell");
    }
}
