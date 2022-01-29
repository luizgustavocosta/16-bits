package com.costa.luiz.failsafe;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Builder
public class Offer {

    private final Client client;
    private final BigDecimal value;
    private final ZonedDateTime timestamp;
    private final double amount;
}
