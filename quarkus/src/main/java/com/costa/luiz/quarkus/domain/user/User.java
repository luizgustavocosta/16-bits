package com.costa.luiz.quarkus.domain.user;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    public Integer id;

    @NotBlank
    @Column(length = 40)
    public String name;

    @NotBlank
    @Column(length = 40)
    public String lastName;

    public User() {
    }

    public User(@NotBlank String name, @NotBlank String lastName) {
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

