package com.bits16.tech.annotation.processor.type;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedAnnotationTypes("com.bits16.tech.annotation.processor.type.*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class SampleProcessor extends AbstractProcessor {

    public boolean process(Set<? extends TypeElement> annotations,
                           RoundEnvironment env) {
        System.out.println("Type processor is better!");
        annotations.forEach(annotation -> {
                    Set<? extends Element> elements = env.getElementsAnnotatedWith(annotation);
                    elements.stream()
                            .filter(TypeElement.class::isInstance)
                            .map(TypeElement.class::cast)
                            .map(TypeElement::getQualifiedName)
                            .map(name -> "Class " + name + " is annotated with " + annotation.getQualifiedName())
                            .forEach(System.out::println);
                }
        );
        return true;
    }
}
