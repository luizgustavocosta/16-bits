package com.costa.luiz.base.numbers;

public class MyAtomic {

    int numberAsInt;
    long numberAsLong;

    public void incrementMyInt() {
        this.numberAsInt++;
    }

    public int getNumberAsInt() {
        return numberAsInt;
    }

    public void setNumberAsInt(int numberAsInt) {
        this.numberAsInt = numberAsInt;
    }

    public void incrementMyLong() {
        this.numberAsLong++;
    }

    public long getNumberAsLong() {
        return numberAsLong;
    }

    public void setNumberAsLong(long numberAsLong) {
        this.numberAsLong = numberAsLong;
    }
}
