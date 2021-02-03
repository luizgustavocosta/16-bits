package com.costa.luiz.spring.resource;

import com.costa.luiz.spring.database.relational.Customer;
import com.costa.luiz.spring.database.relational.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/spring/customer")
public class CustomerResource {


    @Autowired
    private final CustomerRepository repository;

    public CustomerResource(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping(produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<Customer> allCustomer() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Mono<Customer> customerById(@PathVariable("id") String id) {
        repository.save(new Customer("Luiz", "Costa")).block();
        return repository.findById(Long.parseLong(id));
    }
}
