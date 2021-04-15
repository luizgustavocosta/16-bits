package com.bits16.tech.annotation.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE_USE) // Enable tools to perform additional checks on code to help prevent errors.
public @interface CustomType {
}
