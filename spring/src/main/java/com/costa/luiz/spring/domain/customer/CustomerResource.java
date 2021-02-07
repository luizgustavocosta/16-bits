package com.costa.luiz.spring.domain.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/spring/v1/customers")
public class CustomerResource {

    private final CustomerRepository repository;

    public CustomerResource(@Autowired CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> allCustomer() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toUnmodifiableList());
    }

    @GetMapping("/firstname/{name}")
    public List<Customer> findAllByFirstName(@PathVariable("name") String name) {
        return repository.findAllByFirstName(name);
    }

    @GetMapping("/lastname/{name}")
    public List<Customer> findAllByLastName(@PathVariable("name") String name) {
        return repository.findAllByLastName(name);
    }

    @GetMapping("{id}")
    public Customer customerById(@PathVariable("id") Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("id " + id + " not found"));
    }

    @PostMapping
    public ResponseEntity<String> newCustomer(@RequestBody Customer customer) {
        repository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Customer created");

    }

    @PutMapping
    public ResponseEntity<String> modifyCustomer(@RequestBody Customer customer) {
        if (isNull(customer) || isNull(customer.getId())) {
            throw new IllegalArgumentException("Please, review the data before send");
        }
        repository.save(customer);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Customer modified");

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Integer id) {
        final Customer customer = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id [" + id + "] not found"));
        repository.delete(customer);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Customer deleted");

    }
}
