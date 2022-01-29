package com.costa.luiz.airport;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Ticket {
    private int id;
    private BigDecimal price;
    private BigDecimal discount;
    private Client client;

}
