package com.costa.luiz.base.numbers;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MyPrimitiveTest implements WithAssertions {

    public static final int THREADS = 5;

    @BeforeEach
    void setUp() {
    }

    @DisplayName("Integer")
    @Test
    void playingWithInteger() {
        int expected = Integer.MAX_VALUE;
        assertThat(expected).as("My expected values")
                .isEqualTo(Integer.MAX_VALUE);

        String magicNumber = "2021";
        final int parseInt = Integer.parseInt(magicNumber);
        final Integer magicNumberConverted = Integer.valueOf(magicNumber);

        assertThat(parseInt).isEqualTo(magicNumberConverted.intValue());
        assertThat(magicNumberConverted.toString())
                .isEqualTo(magicNumber);
    }

    @Test
    void playingWithLong() {
        long expected = Long.MAX_VALUE;
        assertThat(expected).as("My expected values")
                .isEqualTo(Long.MAX_VALUE);

        String magicNumber = "2021";
        final long parseLong = Long.parseLong(magicNumber);
        final Long magicNumberConverted = Long.valueOf(magicNumber);

        assertThat(parseLong).isEqualTo(magicNumberConverted.longValue());
        assertThat(magicNumberConverted.toString())
                .isEqualTo(magicNumber);
    }

    @Test
    void playingWithDouble() {
        Double myDouble = Double.valueOf("999999999d");
        float myFloat = 999999999f;
        assertThat(myDouble.floatValue()).isEqualTo(myFloat);
    }

    @Test
    void playingWithFloat() {
        Float myFloat = Float.valueOf("999999999f");
        double myDouble = 999999999d;
        assertEquals(myFloat, myDouble,  1d);
    }

    @Test
    void playingWithBigInteger() {
        final BigInteger bigInteger =
                BigInteger.valueOf(Long.MAX_VALUE);
        final BigInteger reduce = IntStream.rangeClosed(1, 3)
                .mapToObj(value -> Long.MAX_VALUE)
                .map(BigInteger::valueOf)
                .reduce(BigInteger.ZERO, BigInteger::add);

        final BigInteger multiply = bigInteger.multiply(
                BigInteger.valueOf(3));
        assertThat(multiply).isEqualTo(reduce);
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void playingWithBigDecimal() {
        final BigDecimal maxLongValue =
                BigDecimal.valueOf(Long.MAX_VALUE);
        final BigDecimal reduce = IntStream.rangeClosed(1, 3)
                .mapToObj(value -> Long.MAX_VALUE)
                .map(BigDecimal::valueOf)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        final BigDecimal multiply = maxLongValue.multiply(
                BigDecimal.valueOf(3));

        assertThat(multiply).isEqualTo(reduce);
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void whenIncrementalReturnCorrectValue() {
        // Given
        final MyPrimitive myPrimitive = new MyPrimitive();
        AtomicInteger atomicInteger = new AtomicInteger();
        final ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        long max = 100_000L;
        // When
        LongStream.range(0, max)
                .forEach(index -> executorService.execute(() -> {
                    atomicInteger.incrementAndGet();
                    myPrimitive.intIncrement();
                }));

        Throwable throwable = catchThrowable(() -> executorService.awaitTermination(2, TimeUnit.SECONDS));

        // Then
        assertAll(() -> {

            assertThat(throwable).as("No problem detected").isNull();

            assertThat(atomicInteger.intValue()).as("Expecting " + max)
                    .isEqualTo(max);
            assertThat(atomicInteger.doubleValue()).as("Atomic should be bigger than int")
                    .isGreaterThan(myPrimitive.getIntIncrement());
        });
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testDoubleAdder() {
        // Given
        final MyPrimitive myPrimitive = new MyPrimitive();
        DoubleAdder doubleAdder = new DoubleAdder();
        final ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        long max = 100_000L;

        // When
        for (int index = 0; index < max; index++) {
            executorService.execute(() -> {
                doubleAdder.add(1d);
                myPrimitive.doubleIncrement();
            });
        }
        Throwable throwable = catchThrowable(() -> executorService.awaitTermination(2, TimeUnit.SECONDS));

        // Then
        assertAll(() -> {

            assertThat(throwable).as("No problem detected").isNull();

            assertThat(doubleAdder.longValue()).as("Expecting " + max)
                    .isEqualTo(max);
            assertThat(doubleAdder.doubleValue()).as("Adder bigger than long")
                    .isGreaterThan(myPrimitive.getDoubleIncrement());
        });
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
                myPrimitive.longPrimitiveIncrement(); // Primitive
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

    @AfterEach
    void tearDown() {
    }
}