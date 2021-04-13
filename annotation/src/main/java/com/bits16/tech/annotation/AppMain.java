package com.bits16.tech.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AppMain {

    public static void main(String[] args) {
        try {
            Class<UserService> annotationExample = UserService.class;
            for (Method method : annotationExample.getMethods()) {
                if (method.isAnnotationPresent(User.class)) {
                    for (Annotation annotation : method.getDeclaredAnnotations()) {
                        System.out.println("Method .:" + method + ", " + annotation);
                    }
                    User user = method.getAnnotation(User.class);
                    System.out.println("user.name() = " + user.name());
                    System.out.println("user.production() = " + user.production());
                    System.out.println("user.project() = " + user.project());
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
