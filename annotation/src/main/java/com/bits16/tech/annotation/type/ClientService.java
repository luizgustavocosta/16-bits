package com.bits16.tech.annotation.type;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class ClientService {

    private static Validator validator;

    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        clientService.doStuff("ABC", null);
        clientService.otherDoStuff("ABC", "Luiz Gustavo");

        Customer customer = new Customer("AA");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Set<ConstraintViolation<Customer>> violations =
                validator.validate(customer);
        for (ConstraintViolation<Customer> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

    void doStuff(@CustomType String value, @MaxLength(value = 10) @MinLength(value = 8) String name) {
        System.out.println("value = " + value);
        System.out.println("name = " + name);
    }

    void otherDoStuff(@CustomType String value, @Length(min = 3, max = 10) String name) {
        System.out.println("value = " + value);
        System.out.println("name = " + name);
    }
}
