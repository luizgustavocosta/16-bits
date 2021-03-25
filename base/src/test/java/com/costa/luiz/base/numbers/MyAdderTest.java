package com.costa.luiz.base.numbers;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Adder")
class MyAdderTest implements WithAssertions {


    private static final long THREADS = 5;
    MyAdder myAdder;

    @BeforeEach
    void setUp() {
        myAdder = new MyAdder();
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testIt() {
        LongAdder adder = new LongAdder();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        IntStream.range(0, 1000)
                .forEach(i -> executorService.submit(adder::increment));//adder::sum
        Throwable throwable = catchThrowable(() -> executorService.awaitTermination(5, TimeUnit.SECONDS));
        System.out.println("\nAdder"+adder.sumThenReset());   // => 1000

        // Test 2
        LongBinaryOperator operator = Long::sum;
        LongAccumulator accumulator = new LongAccumulator(operator, 0L);

        ExecutorService executor = Executors.newFixedThreadPool(5);
        IntStream.range(0, 1000)
                .forEach(i -> executor.submit(() -> accumulator.accumulate(i)));

        Throwable otherThrowable = catchThrowable(() -> executorService.awaitTermination(2, TimeUnit.SECONDS));
        assertNotNull(otherThrowable);
        System.out.println("\nFrom get and Reset"+accumulator.getThenReset());
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testIncrementValues() {
        // Given
        final MyPrimitive myPrimitive = new MyPrimitive();
        LongAdder longAdder = new LongAdder();
        AtomicLong atomicLong = new AtomicLong();
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        long max = 20_000_000L;

        // when
        for (long index = 0; index < max; index++) {
            executorService.execute(() -> {
                longAdder.increment(); // Long Adder
                atomicLong.incrementAndGet(); // Atomic Long
            });
        }
        Throwable throwable = catchThrowable(() -> executorService.awaitTermination(THREADS, TimeUnit.SECONDS));

        // Then
        assertAll(() -> {
            assertThat(throwable).as("No problem detected").isNull();

            assertThat(longAdder.longValue()).as("Expecting " + max).isEqualTo(max);

            assertThat(longAdder.longValue()).as("Has to have same value")
                    .isEqualTo(atomicLong.longValue());

            assertThat(longAdder.longValue()).as("Adder bigger than long")
                    .isGreaterThan(myPrimitive.getLongIncrement());
        });

    }
}