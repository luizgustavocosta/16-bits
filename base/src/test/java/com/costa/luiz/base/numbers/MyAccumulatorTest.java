package com.costa.luiz.base.numbers;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.util.IntSummaryStatistics;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.*;
import java.util.function.LongBinaryOperator;
import java.util.function.LongFunction;
import java.util.function.LongToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Accumulator test")
class MyAccumulatorTest implements WithAssertions {

    long identity = 0L;

    private static final ThreadLocal<Integer> THREADS = ThreadLocal.withInitial(() -> 5);

    @RepeatedTest(1)
    @DisplayName("Long accumulator")
    void longAccumulator() {

        AtomicLong atomicLong = new AtomicLong();
        LongAccumulator sum = new LongAccumulator(Long::sum, identity);
        LongAccumulator max = new LongAccumulator(Long::max, identity);

        LongBinaryOperator accumulatorFunction = (left, right) -> left < right ? left : right;
        LongAccumulator min = new LongAccumulator(accumulatorFunction, 2021L);

        ExecutorService executorService = Executors.newFixedThreadPool(THREADS.get());
        long start = 0L, end = 2_000_000L;
        LongStream.rangeClosed(start, end)
                .forEach(number -> executorService.submit(() -> {
                    sum.accumulate(number);
                    max.accumulate(number);
                    min.accumulate(number);
                    atomicLong.accumulateAndGet(number, Long::sum);
                }));

        Throwable throwable =
        catchThrowable(() ->
                executorService.awaitTermination(1, TimeUnit.SECONDS));

        assertEquals(end, max.get());
        assertEquals(start, min.get());

        assertAll(() -> {
            assertNull(throwable);
            assertThat(sum.get()).as("Should be the same").isEqualTo(atomicLong.get());
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
        LongAccumulator longAccumulator = new LongAccumulator(Long::sum, identity);
        LongStream.rangeClosed(1, 10)
                .forEach(longAccumulator::accumulate);

        assertEquals(55, longAccumulator.get());
    }

    @Test
    void equivalentCallsForLong() {
        LongAdder adder = new LongAdder();
//        LongAccumulator accumulator = new LongAccumulator(Long::sum, 0L); // Same as below
        LongAccumulator accumulator = new LongAccumulator((number, otherNumber) -> number + otherNumber, 0L);
        assertThat(adder.longValue()).as("They are equivalents").isEqualTo(accumulator.longValue());
    }

    @Test
    void equivalentCallsForDouble() {
        DoubleAdder adder = new DoubleAdder();
//        DoubleAccumulator accumulator = new DoubleAccumulator(Double::sum, 0L); // Same as below
        DoubleAccumulator accumulator = new DoubleAccumulator((number, otherNumber) -> number + otherNumber, 0L);
        assertThat(adder.doubleValue()).as("They are equivalents").isEqualTo(accumulator.doubleValue());
    }

    @Test
    void sumUsingStream() {
        int start = 0, end = 5;
        final int sumOfInt = IntStream.rangeClosed(start, end).sum();
        final Long sumAsLong = IntStream.rangeClosed(start, end).mapToLong(number -> number).sum();
        final Integer summingInt = IntStream.rangeClosed(start, end).boxed().collect(Collectors.summingInt(Integer::intValue));
        final Integer sameAsAbove = IntStream.rangeClosed(start, end).boxed().mapToInt(Integer::intValue).sum();
        LongBinaryOperator operator = Long::sum;
        final Long usingLongBinaryOperator = IntStream.rangeClosed(start, end).asLongStream()
                .reduce(0L, operator);//Long::sum

        final int expected = 15;
        assertAll(() -> {
            assertThat(expected).as("Sum").isEqualTo(sumOfInt);
            assertThat(expected).as("Map to Long and sum").isEqualTo(sumAsLong.intValue());
            assertThat(expected).as("Summing Int").isEqualTo(summingInt);
            assertThat(expected).as("Map to Int and sum").isEqualTo(sameAsAbove);
            assertThat(expected).as("Binary operator").isEqualTo(usingLongBinaryOperator.intValue());
        });
    }

    @RepeatedTest(1)
    @DisplayName("Double accumulator")
    @EnabledOnOs(OS.MAC)
    void doubleAccumulator() {
        //https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/atomic/DoubleAccumulator.html
        DoubleAdder doubleAdder = new DoubleAdder();
        final Double[] myDouble = {0d};

        DoubleAccumulator sumAccumulator =
                new DoubleAccumulator(Double::sum, identity);

        DoubleAccumulator maxAccumulator =
                new DoubleAccumulator(Double::max, identity);

        ExecutorService executorService = Executors.newFixedThreadPool(THREADS.get());
        long start = 0L, end = 100_000L;
        LongStream.rangeClosed(start, end)
                .forEach(number -> executorService.submit(() -> {
                    sumAccumulator.accumulate(number);
                    doubleAdder.add(number);
                    maxAccumulator.accumulate(number);
                    myDouble[0] = myDouble[0] + number;
                }));

        Throwable throwable =
                catchThrowable(() ->
                        executorService.awaitTermination(1, TimeUnit.SECONDS));

        assertAll(() -> {
            assertNull(throwable);
            assertThat(sumAccumulator.get()).as("Should be the different").isNotEqualTo(myDouble[0]);
            assertThat(maxAccumulator.get()).as("Expected as end variable").isEqualTo(end);
        });
    }

}