package com.bits16.tech.annotation.processor.numbers;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedAnnotationTypes("com.bits16.tech.annotation.processor.numbers.*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MethodProcessor extends AbstractProcessor {

    public boolean process(Set<? extends TypeElement> annotations,
                           RoundEnvironment env) {
        System.out.println("Method processor rocks!");
        annotations.forEach(annotation -> {
                    Set<? extends Element> elements = env.getElementsAnnotatedWith(annotation);
                    System.out.println("elements = " + elements);
                    elements
                            .forEach(element -> {
                                System.out.println(element.getSimpleName() + ", Annotation .: "+annotation.getQualifiedName());
                            });
                }
        );
        return true;
    }
}
