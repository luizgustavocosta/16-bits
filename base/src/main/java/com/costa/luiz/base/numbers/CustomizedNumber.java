package com.costa.luiz.base.numbers;

public class CustomizedNumber extends Number {

    private final int magicNumber = 42;

    private int intPrimitive;
    private long longPrimitive;
    private double doublePrimitive;

    void intIncrement() { intPrimitive++;}
    double getIntIncrement() { return intPrimitive;}

    void doubleIncrement() { doublePrimitive++;}
    double getDoubleIncrement() { return doublePrimitive;}

    void longPrimitiveIncrement() { longPrimitive++; }
    long getLongIncrement() {
        return longPrimitive;
    }

    @Override
    public int intValue() {
        return magicNumber;
    }

    @Override
    public long longValue() {
        return magicNumber;
    }

    @Override
    public float floatValue() {
        return magicNumber;
    }

    @Override
    public double doubleValue() {
        return magicNumber;
    }
}
