package com.bits16.tech.annotation.processor.custom;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface KnowledgeLevel {

    Level level() default Level.BEGINNER;
}
