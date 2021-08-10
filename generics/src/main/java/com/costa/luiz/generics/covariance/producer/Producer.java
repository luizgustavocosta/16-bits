package com.costa.luiz.generics.covariance.producer;

import com.costa.luiz.generics.covariance.hierarchy.Activity;

import java.util.function.Supplier;

public interface Producer<T> {

    default void play(Supplier<? extends T> producer) {
        Activity activity = (Activity) producer.get();
        activity.start();
    }
}
