package com.costa.luiz.airport;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Client {
    private int id;
    private String name;
    private Fidelity fidelity;
    private String cardNumber;
}
