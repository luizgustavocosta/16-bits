package com.costa.luiz.generics.basic;

import lombok.ToString;

@ToString
public class ToAvoid<T1, T2> {

    T1 firstType;
    T2 secondType;

    public ToAvoid(T1 arg1, T2 arg2) {
        this.firstType = arg1;
        this.secondType = arg2;
    }

    public static void main(String[] args) {
        ToAvoid<String, Long> p1 = new ToAvoid<String, Long>("Dont do it", 42L);
        System.out.println(p1);
    }

}
