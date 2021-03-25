package com.costa.luiz.base.numbers;

import java.util.concurrent.atomic.AtomicLong;

public class MyAdder {

    private final AtomicLong atomicLong;

    public MyAdder() {
        this.atomicLong = new AtomicLong();
    }
}
