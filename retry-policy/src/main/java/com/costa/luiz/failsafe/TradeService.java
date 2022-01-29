package com.costa.luiz.failsafe;

import dev.failsafe.Failsafe;
import dev.failsafe.RetryPolicy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@Slf4j
public class TradeService {

    private final TradeRepository repository;

    public void sell(Stock stock, Offer offer) {
        log.info("Let's sell");
        RetryPolicy<Stock> retryPolicy = RetryPolicy.<Stock>builder()
                .withMaxRetries(3)
                .handle(IllegalStateException.class)
                //.handleResultIf(result -> result.getCompany().startsWith("APP"))
                .withDelay(Duration.ofSeconds(2))
                .onRetry(stockExecutionAttemptedEvent -> log.info("Let's try again"))
                .onRetriesExceeded(retries -> log.warn("We tried, but nothing can be done"))
                .abortIf((o, throwable) -> throwable instanceof NullPointerException)
                .onSuccess(objectExecutionCompletedEvent -> log.info("Success in sell the stock"))
                .build();

        Failsafe.with(retryPolicy)
                .onComplete(objectExecutionCompletedEvent -> log.info("Complete :)"))
                .onSuccess(objectExecutionCompletedEvent -> log.info("Successful completed"))
                .with(Executors.newSingleThreadExecutor())
                .run(() -> repository.sell(stock, offer));

    }

    public Offer asyncBuy(Stock stock, Offer offer) {
        AtomicInteger times = new AtomicInteger();
        RetryPolicy<Offer> retryPolicy = RetryPolicy.<Offer>builder()
                .withMaxRetries(3)
                .handle(IllegalStateException.class)
                .handleResultIf(resultOffer -> resultOffer.getClient().getFirstName().contains("Costa"))
                .withDelay(Duration.ofSeconds(1))
                .onRetriesExceeded(retries -> log.warn("We tried, but nothing can be done"))
                .abortIf((o, throwable) -> throwable instanceof IllegalArgumentException)
                .onFailedAttempt(offerExecutionAttemptedEvent -> times.incrementAndGet())
                .onSuccess(objectExecutionCompletedEvent -> log.info("Success in sell the stock"))
                .build();

        Offer asyncOffer;
        try {
            asyncOffer =
                    Failsafe.with(retryPolicy)
                            .getAsyncExecution(asyncExecution ->
                                    asyncExecution.recordResult(repository.buy(stock, offer)))
                            .whenComplete((offerCompleted, failure) ->
                                    log.info("Offer completed {}, Failure {}", offerCompleted, failure))
                            .get();
        } catch (InterruptedException | ExecutionException exception) {
            throw new IllegalStateException(exception);
        }
        return asyncOffer;
    }
}
