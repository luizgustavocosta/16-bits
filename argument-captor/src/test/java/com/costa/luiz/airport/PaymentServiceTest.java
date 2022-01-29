package com.costa.luiz.airport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;

@DisplayName("Payment Service")
class PaymentServiceTest {

    @InjectMocks
    PaymentService service = new PaymentService();

    @Test
    @DisplayName("Should return an exception when the payment is made")
    void anExceptionWhenPaymentIsMade() {
        UnsupportedOperationException thrown = Assertions.assertThrows(UnsupportedOperationException.class,
                () -> service.pay("1234567890123456", BigDecimal.ONE));
        Assertions.assertEquals("Not available, try later.", thrown.getMessage());
    }

}