package com.bits16.tech.annotation.custom;

import com.bits16.tech.annotation.processor.custom.Requirement;

import java.lang.reflect.Method;

public class AppRequirement {

    public static void main(String[] args) throws NoSuchMethodException {
        final AppRequirement appRequirement = new AppRequirement();
        appRequirement.startFeature();

        Class<?> clazz = appRequirement.getClass();
        final Method method = AppRequirement.class.getMethod("startFeature");
        final Requirement requirementAnnotation = method.getAnnotation(Requirement.class);

        // Without Retention Policy the annotation is not present on runtime
        System.out.println("Description .: "+requirementAnnotation.description() +
                ", date .: "+requirementAnnotation.date() + ", squad .: "+requirementAnnotation.squad());
    }

    @Requirement(description = "Killer feature", date = "2021.04.13", squad = "bounty hunters")
    public void startFeature() {
        //do a lot of though work
    }
}
