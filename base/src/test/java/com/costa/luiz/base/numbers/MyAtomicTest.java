package com.costa.luiz.base.numbers;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
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

    @BeforeEach
    void setUp() {
        myAtomic = new MyAtomic();
    }

    @RepeatedTest(3)
    @DisplayName("Increment Atomic and int")
    void shouldRespectTheIncrementAsInteger() {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        int max = 1_000;
        AtomicInteger atomicInteger = new AtomicInteger();
        IntStream.range(0, max)
                .forEach(index -> executorService.execute(() -> {
                    atomicInteger.incrementAndGet();
                    myAtomic.incrementMyInt();
                }));
        Throwable throwable = catchThrowable(() -> executorService.awaitTermination(2, TimeUnit.SECONDS));

        final int atomicValue = atomicInteger.get();
        final int nonAtomicValue = myAtomic.getNumberAsInt();

        logger.log(Level.INFO, "Atomic .: "+atomicValue);
        logger.log(Level.INFO, "Non-atomic .: "+nonAtomicValue);

        assertThat(throwable).isNull();
        assertThat(atomicValue).as("The values should be different").isNotEqualTo(nonAtomicValue);
    }

    @RepeatedTest(3)
    @DisplayName("Increment Atomic and long")
    void shouldRespectTheIncrementAsLong() {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        AtomicLong atomicLong = new AtomicLong();
        long max = 1_000;
        LongStream.range(0, max)
                .forEach(index -> executorService.execute(() -> {
                    atomicLong.incrementAndGet();
                    myAtomic.incrementMyLong();
                }));
        Throwable throwable = catchThrowable(() -> executorService.awaitTermination(2, TimeUnit.SECONDS));

        final long atomicValue = atomicLong.get();
        final long nonAtomicValue = myAtomic.getNumberAsLong();

        logger.log(Level.INFO, "Atomic .: "+atomicValue);
        logger.log(Level.INFO, "Non-atomic .: "+nonAtomicValue);

        assertThat(throwable).isNull();
        assertThat(atomicValue).as("The values should be different").isNotEqualTo(nonAtomicValue);
    }

}