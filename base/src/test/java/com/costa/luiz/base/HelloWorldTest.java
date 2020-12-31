package com.costa.luiz.base;

import nl.altindag.log.LogCaptor;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.costa.luiz.base.HelloWorld.printUsingLog;
import static com.costa.luiz.base.HelloWorld.printUsingSystem;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;

@DisplayName("Hello World")
class HelloWorldTest implements WithAssertions {

    public static final String THE_MESSAGE_IS_NOT_AS_EXPECTED = "The message is not as expected";

    @DisplayName("Print message via System.out")
    @Test
    void printTheMessageForTheUser() throws Exception {
        String name = "John Doe";
        String systemOut = tapSystemOut(() -> printUsingSystem(name));

        assertThat(systemOut)
                .as(THE_MESSAGE_IS_NOT_AS_EXPECTED)
                .contains("Hello World John Doe at ");
    }

    @DisplayName("Print message via Log")
    @Test
    void usingLog() {
        LogCaptor logCaptor = LogCaptor.forClass(HelloWorld.class);
        printUsingLog("Guga");
        assertThat(String.join("", logCaptor.getInfoLogs()))
                .as(THE_MESSAGE_IS_NOT_AS_EXPECTED)
                .contains("Hello World Guga at");

    }
}