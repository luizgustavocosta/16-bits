package com.costa.luiz.base.numbers;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

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
    private final long identity = 0L;

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
        LongAccumulator accumulator = new LongAccumulator(operator, identity);

        ExecutorService executor = Executors.newFixedThreadPool(THREADS.get());
        IntStream.range(0, 1000)
                .forEach(i -> executor.submit(() -> accumulator.accumulate(i)));

        Throwable otherThrowable = catchThrowable(() -> executorService.awaitTermination(2, TimeUnit.SECONDS));
        assertNull(otherThrowable);
        System.out.println("\nFrom get and Reset"+accumulator.getThenReset());
    }

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