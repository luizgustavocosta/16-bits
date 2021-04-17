package com.bits16.tech.annotation.type;

public class Customer {

    @MinLength(value = 90)
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
