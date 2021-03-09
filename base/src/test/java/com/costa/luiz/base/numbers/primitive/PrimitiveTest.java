package com.costa.luiz.base.numbers.primitive;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("Play with primitives")
class PrimitiveTest {

    @Nested
    class NumbersTest {

        @ParameterizedTest
        @ValueSource(ints = {26, 0x1a, 0b11010})
            //@DisplayName("Play using int \uD83D\uDE30 \uD83D\uDE20 \uD83D\uDE29 \uD83D\uDE31 \uD83D\uDE03")
        void acceptValues(int value) {
            assertEquals(value, 26);
        }

        @Test
        void acceptAValueBigger() {
            int myInt = (int) Long.MAX_VALUE;
            assertEquals(myInt, -1);
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

    @Nested
    public class NonNumbersTest {

    }

}