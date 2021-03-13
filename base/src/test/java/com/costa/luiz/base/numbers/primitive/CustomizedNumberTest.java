package com.costa.luiz.base.numbers.primitive;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import static org.junit.jupiter.api.Assertions.assertAll;

class CustomizedNumberTest implements WithAssertions {

    @BeforeEach
    void setUp() {
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void whenBigComesEverythingIsBig() {
        BigInteger bigInteger = new BigInteger(String.valueOf(Long.MAX_VALUE)).multiply(BigInteger.TWO);
        final BigDecimal bigDecimalAsLongMaxValue = new BigDecimal(String.valueOf(Long.MAX_VALUE));
        BigDecimal hughNumber = bigDecimalAsLongMaxValue.multiply(bigDecimalAsLongMaxValue);

        assertThat(bigInteger).as("Has value").isGreaterThan(BigInteger.TEN);
        final BigDecimal scaled = hughNumber.setScale(2);
        assertThat(hughNumber).as("Huge calculation...").isNotNegative();
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void whenIncrementalReturnCorrectValue() {
        // Given
        final CustomizedNumber customizedNumber = new CustomizedNumber();
        AtomicInteger atomicInteger = new AtomicInteger();
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        long max = 100_000L;
        // When
        for (int index = 0; index < max; index++) {
            executorService.execute(() -> {
                atomicInteger.incrementAndGet();
                customizedNumber.intIncrement();
            });
        }
        Throwable throwable = catchThrowable(() -> executorService.awaitTermination(2, TimeUnit.SECONDS));

        // Then
        assertAll(() -> {

            assertThat(throwable).as("No problem detected").isNull();

            assertThat(atomicInteger.intValue()).as("Expecting " + max)
                    .isEqualTo(max);
            assertThat(atomicInteger.doubleValue()).as("Atomic should be bigger than int")
                    .isGreaterThan(customizedNumber.getIntIncrement());
        });
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testDoubleAdder() {
        // Given
        final CustomizedNumber customizedNumber = new CustomizedNumber();
        DoubleAdder doubleAdder = new DoubleAdder();
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        long max = 100_000L;

        // When
        for (int index = 0; index < max; index++) {
            executorService.execute(() -> {
                doubleAdder.add(1d);
                customizedNumber.doubleIncrement();
            });
        }
        Throwable throwable = catchThrowable(() -> executorService.awaitTermination(2, TimeUnit.SECONDS));

        // Then
        assertAll(() -> {

            assertThat(throwable).as("No problem detected").isNull();

            assertThat(doubleAdder.longValue()).as("Expecting " + max)
                    .isEqualTo(max);
            assertThat(doubleAdder.doubleValue()).as("Adder bigger than long")
                    .isGreaterThan(customizedNumber.getDoubleIncrement());
        });
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testIncrementValues() {
        // Given
        final CustomizedNumber customizedNumber = new CustomizedNumber();
        LongAdder longAdder = new LongAdder();
        AtomicLong atomicLong = new AtomicLong();
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        long max = 20_000_000L;

        // when
        for (long index = 0; index < max; index++) {
            executorService.execute(() -> {
                longAdder.increment(); // Long Adder
                atomicLong.incrementAndGet(); // Atomic Long
                customizedNumber.longPrimitiveIncrement(); // Primitive
            });
        }
        Throwable throwable = catchThrowable(() -> executorService.awaitTermination(5, TimeUnit.SECONDS));

        // Then
        assertAll(() -> {
            assertThat(throwable).as("No problem detected").isNull();

            assertThat(longAdder.longValue()).as("Expecting " + max).isEqualTo(max);

            assertThat(longAdder.longValue()).as("Has to have same value")
                    .isEqualTo(atomicLong.longValue());

            assertThat(longAdder.longValue()).as("Adder bigger than long")
                    .isGreaterThan(customizedNumber.getLongIncrement());
        });

    }

    @AfterEach
    void tearDown() {
    }
}