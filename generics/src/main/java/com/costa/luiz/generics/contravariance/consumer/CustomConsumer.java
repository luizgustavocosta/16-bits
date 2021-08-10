package com.costa.luiz.generics.contravariance.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public interface CustomConsumer<T> {

    Logger logger = LoggerFactory.getLogger(CustomConsumer.class);

    default void apply(List<? super T> items) {
        logger.info("-------");
        logger.info("Applying for {}", items.iterator().next().getClass().getSimpleName());
        items.forEach(item -> logger.info(item.toString()));
        logger.info("-------");
    }
}
