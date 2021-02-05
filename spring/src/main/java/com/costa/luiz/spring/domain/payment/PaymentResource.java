package com.costa.luiz.spring.domain.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spring/v1/payments")
public class PaymentResource {

    private final PaymentService service;

    public PaymentResource(@Autowired PaymentService service) {
        this.service = service;
    }


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Payment> allPayments() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Payment paymentById(@PathVariable("id") Integer id) {
        return service.findOne(id);
    }

    @PutMapping
    public ResponseEntity<String> create(@RequestBody Payment payment) {
        service.update(payment);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Payment created");
    }

    @PostMapping
    public ResponseEntity<String> modify(@RequestBody Payment payment) {
        service.update(payment);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Payment modified");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePayment(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Payment deleted");
    }
}
