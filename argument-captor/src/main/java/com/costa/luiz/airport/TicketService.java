package com.costa.luiz.airport;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class TicketService {

    private final PaymentService paymentService;

    public void process(Ticket ticket) {
        switch (ticket.getClient().getFidelity()) {
            case GOLD:
                ticket.setDiscount(ticket.getPrice().multiply(BigDecimal.valueOf(0.1)));
                break;
            case PLATINUM:
                ticket.setDiscount(ticket.getPrice().multiply(BigDecimal.valueOf(0.2)));
                break;
            case INFINITY:
                ticket.setDiscount(ticket.getPrice().multiply(BigDecimal.valueOf(0.3)));
                break;
        }
        ticket.setPrice(ticket.getPrice().subtract(ticket.getDiscount()));
        if (!paymentService.pay(ticket.getClient().getCardNumber(), ticket.getPrice())) {
            throw new IllegalStateException("Payment  failed");
        }
    }
}
