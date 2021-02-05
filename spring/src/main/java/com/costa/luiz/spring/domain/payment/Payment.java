package com.costa.luiz.spring.domain.payment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String type;
    private Instant occurredOn;
    private String amount;

    public Payment() {
    }

    public Payment(String type, String amount) {
        this.type = type;
        this.amount = amount;
        this.occurredOn = Instant.now();
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Instant getOccurredOn() {
        return occurredOn;
    }

    public String getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", occurredOn=" + occurredOn +
                ", amount='" + amount + '\'' +
                '}';
    }
}
