package com.costa.luiz.base.numbers;

public class MyAtomic {

    volatile int countAsInt;
    volatile long countAsLong;

    public void incrementCountAsInt() {
        this.countAsInt++;
    }

    public int getCountAsInt() {
        return countAsInt;
    }

    public void incrementCountAsLong() {
        this.countAsLong++;
    }

    public long getCountAsLong() {
        return countAsLong;
    }
}
