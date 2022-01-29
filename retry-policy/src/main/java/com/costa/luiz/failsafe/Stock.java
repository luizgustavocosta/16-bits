package com.costa.luiz.failsafe;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Stock {

    private final String symbol;
    private final BigDecimal price;
    private final String company;
}
