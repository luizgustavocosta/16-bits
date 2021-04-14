package com.bits16.tech.annotation.marker;

public class AppGoodExample {

    public static void main(String[] args) {

        final Class<GoodCodeExample> clazz = GoodCodeExample.class;
        if (clazz.isAnnotationPresent(UseAsExample.class)) {
            System.out.println("Oh yeah, let see the code");
        } else {
            System.out.println("Forget it, it's just a regular code");
        }
    }
}
