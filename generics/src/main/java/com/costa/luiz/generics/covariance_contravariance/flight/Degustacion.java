package com.costa.luiz.generics.covariance_contravariance.flight;

public class Degustacion extends Gourmet {

    public static final Degustacion INSTANCE = new Degustacion();

    @Override
    public String toString() {
        return "3 dishes";
    }
}
