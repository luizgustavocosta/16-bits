package com.bits16.tech.annotation.type;


public class ClientService {

    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        clientService.otherDoStuff("ABC", "Luiz Gustavo");
    }

    void doStuff(@CustomType String value, @MaxLength(value = 10) @MinLength(value = 3) String name) {
        System.out.println("value = " + value);
        System.out.println("name = " + name);
    }

    void otherDoStuff(@CustomType String value, @Length(min = 3, max = 10) String name) {
        System.out.println("value = " + value);
        System.out.println("name = " + name);
    }
}
