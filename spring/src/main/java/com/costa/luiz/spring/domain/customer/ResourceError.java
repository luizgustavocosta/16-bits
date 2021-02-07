package com.costa.luiz.spring.domain.customer;

import org.springframework.http.HttpStatus;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;

/**
 * Original code from https://www.toptal.com/java/spring-boot-rest-api-error-handling
 */
public class ResourceError {

    private HttpStatus status;
    private Instant timestamp;
    private String message;
    private String details;

    ResourceError(HttpStatus httpStatus, Exception exception) {
        timestamp = Instant.now();
        this.status = httpStatus;
        this.message = exception.getMessage();

        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        exception.printStackTrace(printWriter);

        this.details = writer.toString();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
