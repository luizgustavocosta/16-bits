package com.costa.luiz.generics.covariance.hierarchy;

public class Metal extends Music implements Activity {

    @Override
    public void start() {
        System.out.println("Lets play AC/DC");
    }
}
