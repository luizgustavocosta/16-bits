package com.costa.luiz.spring.domain.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(@Autowired PaymentRepository repository) {
        this.repository = repository;
    }

    public Payment findOne(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(" id " + id + " not found"));
    }

    public List<Payment> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toUnmodifiableList());
    }

    public Payment create(Payment payment) {
        return repository.save(payment);
    }

    public Payment update(Payment payment) {
        return repository.save(payment);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
