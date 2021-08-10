package com.costa.luiz.generics.overloading;

public class NameService {

    String getName(Object object) {
        return object.toString();
    }

    String getName(String object) {
        return object;
    }

    <T> String generic(T any) {
        return getName(any);
    }
}
