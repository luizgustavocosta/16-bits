package com.costa.luiz.quarkus.domain.auction;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;

@Entity
public class Auction extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column
    public ZonedDateTime startDate;

    @NotBlank
    @Column
    public ZonedDateTime endDate;

    @NotBlank
    @Column(length = 40)
    public String status;

    public Auction() {
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }
}
