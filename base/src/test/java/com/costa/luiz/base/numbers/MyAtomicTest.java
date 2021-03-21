package com.costa.luiz.base.numbers;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@EnabledOnOs(OS.MAC)
@DisplayName("Atomic test")
class MyAtomicTest implements WithAssertions {

    private static final Logger logger = Logger.getLogger(MyAtomicTest.class.getName());

    private MyAtomic myAtomic;
    private final short numberOfThread = 5;
    private final short awaitTimeout = 2;

    @BeforeEach
    void setUp() {
        myAtomic = new MyAtomic();
    }

    @RepeatedTest(3)
    @DisplayName("Increment Atomic and int")
    void shouldRespectTheIncrementAsInteger() {
        final ExecutorService executorService = Executors.newFixedThreadPool(numberOfThread);
        int min = 0, max = 1_000_000;
        AtomicInteger atomicInteger = new AtomicInteger();
        IntStream.range(min, max)
                .forEach(index -> executorService.execute(() -> {
                    atomicInteger.incrementAndGet();
                    myAtomic.incrementCountAsInt();
                }));
        Throwable throwable = catchThrowable(() -> executorService.awaitTermination(awaitTimeout, TimeUnit.SECONDS));

        final int atomicValue = atomicInteger.get();
        final int nonAtomicValue = myAtomic.getCountAsInt();

        logger.log(Level.INFO, "Atomic .: "+atomicValue);
        logger.log(Level.INFO, "Non-atomic .: "+nonAtomicValue);

        assertThat(throwable).isNull();
        assertThat(atomicValue).as("The values should be different").isNotEqualTo(nonAtomicValue);
    }

    @RepeatedTest(3)
    @DisplayName("Increment Atomic and long")
    void shouldRespectTheIncrementAsLong() {
        final ExecutorService executorService = Executors.newFixedThreadPool(numberOfThread);
        AtomicLong atomicLong = new AtomicLong();
        long min = 0, max = 1_000_000;
        LongStream.range(min, max)
                .forEach(index -> executorService.execute(() -> {
                    atomicLong.incrementAndGet();
                    myAtomic.incrementCountAsLong();
                }));
        Throwable throwable = catchThrowable(() -> executorService.awaitTermination(awaitTimeout, TimeUnit.SECONDS));

        final long atomicValue = atomicLong.get();
        final long nonAtomicValue = myAtomic.getCountAsLong();

        logger.log(Level.FINE, "Atomic .: "+atomicValue);
        logger.log(Level.FINE, "Non-atomic .: "+nonAtomicValue);

        assertThat(throwable).isNull();
        assertThat(atomicValue).as("The values should be different").isNotEqualTo(nonAtomicValue);
    }

}