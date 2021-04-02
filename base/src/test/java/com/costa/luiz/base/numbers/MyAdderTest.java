package com.costa.luiz.base.numbers;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Adder")
class MyAdderTest implements WithAssertions {

    private static final ThreadLocal<Integer> THREADS = ThreadLocal.withInitial(() -> 5);
    int start = 0, end = 1_000;
    int timeout = 2;
    long identity = 0L;

    @Test
    void onConcurrentHashMap() {
        ConcurrentHashMap<String,LongAdder> frequency = new ConcurrentHashMap<>();
        String key = "Java";
        long from = 0, to = 5;

        for (long index = from; index <= to; index++) {
            frequency.computeIfAbsent(key, identifier -> new LongAdder()).add(index);
        }
        final long expectedValue = LongStream.rangeClosed(from, to).sum();

        final double sum = LongStream.rangeClosed(from, to).mapToDouble(number -> Double.parseDouble(String.valueOf(number)))
                .sum();

        assertThat(expectedValue).as("LongValue should be equals to sum")
                .isEqualTo(frequency.values().iterator().next().longValue());

        assertThat(expectedValue).as("Sum should be equals to sum")
                .isEqualTo(frequency.values().iterator().next().sum());

        assertThat(sum).as("Sum of double and long are equals").isEqualTo(expectedValue);
    }

    @RepeatedTest(2)
    void longAdder() {
        LongAdder adder = new LongAdder();
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS.get());
        LongStream.range(start, end)
                .forEach(iteration -> executorService.submit(adder::increment)); //adder::sum
        Throwable throwable = catchThrowable(() -> executorService.awaitTermination(timeout, TimeUnit.SECONDS));

        int expected = 1_000;

        assertAll(() -> {
            assertNull(throwable);
            assertThat(adder.sum()).isEqualTo(expected);
            assertThat(adder.longValue()).isEqualTo(expected);
        });
    }

    @Test
    void usingIncrement() {
        LongAccumulator accumulator = new LongAccumulator(Long::sum, identity);

        ExecutorService executor = Executors.newFixedThreadPool(THREADS.get());
        IntStream.range(start, end)
                .forEach(i -> executor.submit(() -> accumulator.accumulate(i)));

        Throwable otherThrowable = catchThrowable(() -> executor.awaitTermination(timeout, TimeUnit.SECONDS));

        long expected = 499_500;

        assertAll(() -> {
            assertNull(otherThrowable);
            assertThat(accumulator.get()).isEqualTo(expected);
        });
    }

    @EnabledOnOs(OS.MAC)
    @RepeatedTest(2)
    @DisplayName("Double adder")
    void doubleAdder() {
        DoubleAdder adder = new DoubleAdder();
        final Double[] myDouble = {0d};

        ExecutorService executorService = Executors.newFixedThreadPool(THREADS.get());
        long start = 0L, end = 100_000L;
        LongStream.rangeClosed(start, end)
                .forEach(number -> executorService.submit(() -> {
                    adder.add(number);
                    myDouble[0] = myDouble[0] + number;
                }));

        Throwable throwable =
                catchThrowable(() ->
                        executorService.awaitTermination(timeout, TimeUnit.SECONDS));

        assertAll(() -> {
            assertNull(throwable);
            assertThat(adder.sum()).as("Should be the different").isNotEqualTo(myDouble[0]);
        });
    }
}