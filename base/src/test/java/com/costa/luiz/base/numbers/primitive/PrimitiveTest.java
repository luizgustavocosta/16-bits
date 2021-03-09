package com.costa.luiz.base.numbers.primitive;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Play with primitives")
class PrimitiveTest implements WithAssertions {

    @Nested
    class NumbersTest {

        @ParameterizedTest
        @ValueSource(ints = {42, 0x2a, 0b101010})
        void acceptValues(int value) {
            assertEquals(value, 42);
        }

        @Test
        void acceptAValueBigger() {
            int myInt = (int) Long.MAX_VALUE;
            assertEquals(myInt, -1);
        }

        @Test
        void autoboxingBoxing() {
            Integer autoboxing = 42;
            int unboxing = Integer.parseInt("42");
            assertEquals(unboxing, autoboxing);
        }

        @Test
        void timeUsingClassAndPrimitive() {
            int max = 100_000;
            int dummy = 0;
            ZonedDateTime start = ZonedDateTime.now();
            for (int index = 0; index < max; index++) {
                dummy += index;
            }
            ZonedDateTime end = ZonedDateTime.now();
            final Duration durationOfPrimitive = Duration.between(start, end);

            Integer dummyAutoboxing = Integer.parseInt("0");
            start = ZonedDateTime.now();
            for (int index = 0; index < max; index++) {
                dummyAutoboxing += index;
            }
            end = ZonedDateTime.now();
            final Duration durationOfUnboxing = Duration.between(start, end);

            assertThat(durationOfPrimitive)
                    .as("Primitive should be faster than Object")
                    .isLessThan(durationOfUnboxing);
        }

        @Test
        void lookingForBoundaries() {
            int min = Integer.MIN_VALUE;
            int max = Integer.MAX_VALUE;
            assertAll(() -> {
                assertEquals(Integer.MIN_VALUE, min);
                assertEquals(Integer.MAX_VALUE, max);
            });
        }
    }

}