package com.costa.luiz.failsafe;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class TradeRepository {

    void sell(Stock stock, Offer offer) {
        log.info("Here we call another layer");
    }

    Offer buy(Stock stock, Offer offer) {
        // Simulating interruption
        log.info("Here we call another layer, too");
        int randomNumber = ThreadLocalRandom.current().nextInt(0, 4);
        if (randomNumber % 2 == 0) {
            log.error("An error is coming");
            throw new IllegalStateException("Service not available");
        }
        // Simulating a return of DB
        offer = Offer.builder()
                .value(BigDecimal.ONE)
                .amount(offer.getAmount())
                .client(offer.getClient())
                .build();
        log.info("Stock sell \\o/ by {}", offer);
        return offer;
    }
}
