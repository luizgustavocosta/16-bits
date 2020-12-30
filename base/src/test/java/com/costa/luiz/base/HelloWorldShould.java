package com.costa.luiz.base;

import nl.altindag.log.LogCaptor;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static com.costa.luiz.base.HelloWorld.*;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;

class HelloWorldShould implements WithAssertions {

    @Test
    void printTheMessageForTheUser() throws Exception {
        String name = "John Doe";
        String systemOut = tapSystemOut(() -> printUsingSystem(name));
        assertThat(systemOut.trim()).as("").contains("Hello World John Doe at ");
    }

    @Test void usingLog() {
        LogCaptor logCaptor = LogCaptor.forClass(HelloWorld.class);
        printUsingLog("Guga");
        assertThat(logCaptor.getInfoLogs().stream().collect(Collectors.joining()))
                .as("")
                .contains("Hello World Guga at");

    }
}