package com.costa.luiz.failsafe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Trade Service")
class TradeServiceTest {

    @Mock
    TradeRepository repository;

    TradeService tradeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tradeService = new TradeService(repository);
    }

    @Test
    void sellRetry() {
        Stock stock = Stock.builder().build();
        Offer offer = Offer.builder().build();
        doThrow(IllegalStateException.class).when(repository).sell(stock, offer);
        IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class,
                () -> tradeService.sell(stock, offer));
        Assertions.assertNotNull(exception);
        verify(repository, times(4)).sell(stock, offer);
    }

    @Test
    void sell() {
        Stock stock = Stock.builder().build();
        Offer offer = Offer.builder().build();
        doNothing().when(repository).sell(stock, offer);
        tradeService.sell(stock, offer);
        verify(repository, times(1)).sell(stock, offer);
    }

    @Test
    void asyncBuyRetry() {
        Stock stock = Stock.builder().build();
        Offer offer = Offer.builder().build();
        doThrow(IllegalStateException.class).when(repository).buy(stock, offer);
        IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class,
                () -> tradeService.asyncBuy(stock, offer));
        Assertions.assertNotNull(exception);
        verify(repository, times(4)).buy(stock, offer);
    }

    @Test
    void asyncBuy() {
        // Given
        double magicNumber = 42d;
        BigDecimal value = BigDecimal.TEN;

        Stock stock = Stock.builder().build();
        Offer offer = Offer.builder().amount(magicNumber).value(value).build();

        Offer stockBought = Offer.builder().amount(magicNumber-2d).value(value).build();//image a price variation

        doReturn(stockBought).when(repository).buy(stock, offer);

        // When
        Offer asyncStock = tradeService.asyncBuy(stock, offer);

        // Then
        verify(repository, timeout(100).times(1)).buy(stock, offer);
        Assertions.assertAll(() -> {
            Assertions.assertEquals(stockBought.getAmount(), asyncStock.getAmount());
            Assertions.assertEquals(stockBought.getValue(), asyncStock.getValue());
            Assertions.assertTrue(offer.getAmount() > asyncStock.getAmount());
        });
    }
}