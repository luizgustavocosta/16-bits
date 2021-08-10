package com.costa.luiz.generics.contravariance.consumer;

import com.costa.luiz.generics.contravariance.hierarchy.Bamboo;
import com.costa.luiz.generics.contravariance.hierarchy.Vegetable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Consumer;

@AllArgsConstructor
@Data
@Slf4j
public class Human implements CustomConsumer<Vegetable> {

//    @Override
//    public void apply(List<? super Vegetable> food) {
//        food.forEach(myFood -> log.info(((Vegetable)myFood).getPayload()));
//    }

//    @Override
//    public void apply(Consumer<? super Vegetable> consumer, Vegetable source) {
//        log.info("Time to eat the vegetable :)");
//        consumer.accept(source);
//    }
}
