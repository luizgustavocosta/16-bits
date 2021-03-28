package com.costa.luiz.base.numbers;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongToDoubleFunction;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * References
 * https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/atomic/DoubleAccumulator.html
 *
 */
@DisplayName("Accumulator test")
class MyAccumulatorTest implements WithAssertions {

    @EnabledOnOs(OS.MAC)
    @RepeatedTest(3)
    @DisplayName("Long accumulator")
    void longAccumulator() {
        AtomicLong atomicLong = new AtomicLong();
        LongAccumulator longAccumulator =
                new LongAccumulator(Long::sum, 0L);

        int threads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        long start = 0L, end = 2_000_000L;
        LongStream.rangeClosed(start, end)
                .forEach(number -> executorService.submit(() -> {
                    longAccumulator.accumulate(number);
                    atomicLong.accumulateAndGet(number, Long::sum);
                }));

        Throwable throwable =
        catchThrowable(() ->
                executorService.awaitTermination(1, TimeUnit.SECONDS));

        assertAll(() -> {
            assertNull(throwable);
            assertThat(longAccumulator.get()).as("Should be the same").isEqualTo(atomicLong.get());
        });
    }

    @Test
    void playingWithLong() {
        LongBinaryOperator binaryOperator = (left, right) -> 2 * left + right; //20+3 = 23

        final long asLong = binaryOperator.applyAsLong(10, 3);

        assertEquals(23, asLong);

        LongToDoubleFunction longToDoubleFunction = divisor -> divisor / 3d;
        final double apply = longToDoubleFunction.applyAsDouble(30);
        assertEquals(10, apply);

        //LongAccumulator accumulator = new LongAccumulator(binaryOperator, 0L);

        LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 0L);
        LongStream.rangeClosed(1, 10)
                .forEach(longAccumulator::accumulate);

        assertEquals(55, longAccumulator.get());
    }

    @EnabledOnOs(OS.MAC)
    @RepeatedTest(3)
    @DisplayName("Double accumulator")
    void doubleAccumulator() {
        DoubleAdder doubleAdder = new DoubleAdder();
        final Double[] myDouble = {0d};
        DoubleAccumulator doubleAccumulator =
                new DoubleAccumulator(Double::sum, 0L);

        int threads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        long start = 0L, end = 100_000L;
        LongStream.rangeClosed(start, end)
                .forEach(number -> executorService.submit(() -> {
                    doubleAccumulator.accumulate(number);
                    doubleAdder.add(number);
                    myDouble[0] = myDouble[0] + number;
                }));

        Throwable throwable =
                catchThrowable(() ->
                        executorService.awaitTermination(1, TimeUnit.SECONDS));

        assertAll(() -> {
            assertNull(throwable);
            assertThat(doubleAccumulator.get()).as("Should be the different").isNotEqualTo(myDouble[0]);
        });
    }

}