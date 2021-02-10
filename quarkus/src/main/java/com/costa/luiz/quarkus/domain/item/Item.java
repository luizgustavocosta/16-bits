package com.costa.luiz.quarkus.domain.item;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "items")
public class Item extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    public Integer id;

    @NotBlank
    @Column(length = 40)
    public String name;

    @NotBlank
    @Column(length = 40)
    public String description;

    public Item() {
    }

    public Item(@NotBlank String name, @NotBlank String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
