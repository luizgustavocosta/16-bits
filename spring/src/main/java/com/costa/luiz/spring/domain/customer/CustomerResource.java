package com.costa.luiz.spring.domain.customer;

import com.costa.luiz.spring.domain.customer.Customer;
import com.costa.luiz.spring.domain.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/spring/v1/customers")
public class CustomerResource {

    private final CustomerRepository repository;

    public CustomerResource(@Autowired CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> allCustomer() {
        repository.save(new Customer("Luiz", "Costa"));
        final List<Customer> customers = new ArrayList<>();
        repository.findAll().forEach(customers::add);
        return customers;
    }

    @GetMapping("{id}")
    public Customer customerById(@PathVariable("id") Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("id "+id+" not found"));
    }
}
