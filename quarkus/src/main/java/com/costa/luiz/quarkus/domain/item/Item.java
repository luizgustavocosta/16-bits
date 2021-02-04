package com.costa.luiz.quarkus.domain.item;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
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
    public String lastName;

    public Item() {
    }

    public Item(@NotNull Integer id, @NotBlank String name, @NotBlank String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +

                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
