package com.costa.luiz.failsafe;

import java.math.BigDecimal;

public class AppMain {

    public static void main(String[] args)  {

        Client client = Client.builder()
                .id("1").firstName("Luiz").lastName("Costa")
                .build();

        Offer offer = Offer.builder()
                .client(client)
                .value(BigDecimal.TEN)
                .build();

        Stock stock = Stock.builder()
                .company("Apple")
                .symbol("AAPL")
                .price(BigDecimal.valueOf(168.51))
                .build();

        TradeService tradeService = new TradeService(new TradeRepository());

        tradeService.sell(stock, offer);
        tradeService.asyncBuy(stock, offer);

    }
}
