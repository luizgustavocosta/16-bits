package com.costa.luiz.base;

import java.time.LocalDateTime;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class.getName());

    public static void main(String[] args) {
        String printOption = Objects.requireNonNull(args[0]);
        String name = Objects.requireNonNull(args[1]);

        switch (printOption) {
            case "system":
                printUsingSystem(name);
                break;
            case "log":
                printUsingLog(name);
                break;
            default:
                throw new IllegalArgumentException(printOption +" not availble, use system or log instead");
        }
    }

    public static void printUsingLog(String name) {
        if (logger.isInfoEnabled()) {
            logger.info("Hello World {} at {}", name, LocalDateTime.now());
        }
    }

    public static void printUsingSystem(String name) {
        System.out.println("Hello World "+name+" at "+ LocalDateTime.now());
    }
}
