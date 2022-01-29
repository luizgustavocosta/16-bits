package com.costa.luiz.airport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@DisplayName("Ticket Service")
@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    PaymentService paymentService;

    TicketService ticketService;

    @BeforeEach
    void setUp() {
        ticketService = new TicketService(paymentService);
    }

    @ParameterizedTest
    @MethodSource("ticketProvider")
    void process(Ticket ticket, BigDecimal expected) {
        ArgumentCaptor<BigDecimal> argument = ArgumentCaptor.forClass(BigDecimal.class);
        doReturn(true).when(paymentService).pay(any(), argument.capture());
        ticketService.process(ticket);
        assertEquals(0, argument.getValue().compareTo(expected));
    }

    private static Stream<Arguments> ticketProvider() {
        Ticket.TicketBuilder ticketBuilder = Ticket.builder();
        Client.ClientBuilder clientBuilder = Client.builder();
        return Stream.of(
                Arguments.of(ticketBuilder
                        .price(BigDecimal.TEN)
                        .client(clientBuilder
                                .fidelity(Fidelity.GOLD)
                                .name("James")
                                .cardNumber("1")
                                .build())
                        .build(), BigDecimal.valueOf(9.0)),
                Arguments.of(ticketBuilder
                        .price(BigDecimal.TEN)
                        .client(clientBuilder
                                .fidelity(Fidelity.INFINITY)
                                .name("PeaceMaker")
                                .cardNumber("99")
                                .build())
                        .build(), BigDecimal.valueOf(7.0)),
                Arguments.of(ticketBuilder
                        .price(BigDecimal.TEN)
                        .client(clientBuilder
                                .fidelity(Fidelity.PLATINUM)
                                .name("Boba Fett")
                                .cardNumber("42")
                                .build())
                        .build(), BigDecimal.valueOf(8.0))
        );
    }
}