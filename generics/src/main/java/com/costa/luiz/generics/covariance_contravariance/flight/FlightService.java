package com.costa.luiz.generics.covariance_contravariance.flight;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class FlightService<T, U> {

    void covariance(List<? extends T> items) {
        print(items);
    }

    private void print(List<? extends T> items) {
        items.forEach(item -> log.info(item.toString()));
    }

    void covarianceForTheSecondType(List<? extends U> items) {
        items.forEach(item -> log.info(item.toString()));
    }

    void contravariance(List<? super T> items) {
        items.forEach(item -> log.info(item.toString()));
    }

    void contravarianceForTheSecondType(List<? super U> items) {
        items.forEach(item -> log.info(item.toString()));
    }

}
