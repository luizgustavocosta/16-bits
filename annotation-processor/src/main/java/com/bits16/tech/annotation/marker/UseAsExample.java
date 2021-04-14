package com.bits16.tech.annotation.marker;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
//The @Documented annotation is a marker interface that tells a tool that an annotation is to be documented.
// It is designed to be used only as an annotation to an annotation declaration. Java Complete Reference, 11th Edition
@Documented
public @interface UseAsExample {
}
