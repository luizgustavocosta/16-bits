package com.costa.luiz.generics.contravariance.consumer;

import com.costa.luiz.generics.contravariance.hierarchy.Bamboo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class Panda implements CustomConsumer<Bamboo> {

}
