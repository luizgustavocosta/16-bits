package com.bits16.tech.annotation;

import com.bits16.tech.annotation.processor.numbers.GreaterThanZero;

public class MyNumber {

    @GreaterThanZero
    private double number;

    public MyNumber(double number) {
        this.number = number;
    }

    public static void main(String[] args) {
        final MyNumber myNumber = new MyNumber(-2d);
        System.out.println("myNumber = " + myNumber);
    }
}
