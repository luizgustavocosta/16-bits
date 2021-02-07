package com.costa.luiz.spring;

import com.costa.luiz.spring.domain.customer.Customer;
import com.costa.luiz.spring.domain.customer.CustomerRepository;
import com.costa.luiz.spring.domain.payment.Payment;
import com.costa.luiz.spring.domain.payment.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DataLoader {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Bean
    CommandLineRunner initDatabase(PaymentRepository paymentRepository, CustomerRepository customerRepository) {

        return args -> {
            paymentRepository.save(new Payment("CARD", "90.00"));
            paymentRepository.save(new Payment("BITCOINS", "thief"));
            customerRepository.save(new Customer("Poe", "Dameron"));
            customerRepository.save(new Customer("Peter", "Griffin"));
            customerRepository.save(new Customer("Peter", "Pan"));
            log.info("Loaded data to Payment and Customer");
        };
    }
}