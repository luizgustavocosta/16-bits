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

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Adder")
class MyAdderTest implements WithAssertions {

    private static final ThreadLocal<Integer> THREADS = ThreadLocal.withInitial(() -> 5);

    @Test
    void onConcurrentHashMap() {
        ConcurrentHashMap<String,LongAdder> frequency = new ConcurrentHashMap<>();
        String key = "Java";
        long from = 0, to = 5;
        for (long index = from; index <= to; index++) {
            frequency.computeIfAbsent(key, identifier -> new LongAdder()).add(index);
        }
        final long expectedValue = LongStream.rangeClosed(from, to).sum();
        assertThat(expectedValue).as("LongValue should be equals to sum")
                .isEqualTo(frequency.values().iterator().next().longValue());

        assertThat(expectedValue).as("Sum should be equals to sum")
                .isEqualTo(frequency.values().iterator().next().sum());
    }

    @RepeatedTest(2)
    void longAdder() {
        LongAdder adder = new LongAdder();
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS.get());
        IntStream.range(0, 1000)
                .forEach(number -> executorService.submit(adder::increment)); //adder::sum
        Throwable throwable = catchThrowable(() -> executorService.awaitTermination(2, TimeUnit.SECONDS));
        System.out.println("\nAdder"+adder.sumThenReset());   // => 1000
        assertNull(throwable);

        // Test 2
        LongBinaryOperator operator = Long::sum;
        long identity = 0L;
        LongAccumulator accumulator = new LongAccumulator(operator, identity);

        ExecutorService executor = Executors.newFixedThreadPool(THREADS.get());
        IntStream.range(0, 1000)
                .forEach(i -> executor.submit(() -> accumulator.accumulate(i)));

        Throwable otherThrowable = catchThrowable(() -> executorService.awaitTermination(2, TimeUnit.SECONDS));
        assertNull(otherThrowable);
        System.out.println("\nFrom get and Reset"+accumulator.getThenReset());
    }

    @EnabledOnOs(OS.MAC)
    @RepeatedTest(2)
    @DisplayName("Double adder")
    void doubleAdder() {
        DoubleAdder doubleAdder = new DoubleAdder();
        final Double[] myDouble = {0d};

        ExecutorService executorService = Executors.newFixedThreadPool(THREADS.get());
        long start = 0L, end = 100_000L;
        LongStream.rangeClosed(start, end)
                .forEach(number -> executorService.submit(() -> {
                    doubleAdder.add(number);
                    myDouble[0] = myDouble[0] + number;
                }));

        Throwable throwable =
                catchThrowable(() ->
                        executorService.awaitTermination(1, TimeUnit.SECONDS));

        assertAll(() -> {
            assertNull(throwable);
            assertThat(doubleAdder.sum()).as("Should be the different").isNotEqualTo(myDouble[0]);
        });
    }
}