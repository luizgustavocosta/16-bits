package com.costa.luiz.failsafe;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Client {

    private final String id;
    private final String firstName;
    private final String lastName;
}
