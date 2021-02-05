package com.costa.luiz.spring;

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
    CommandLineRunner initDatabase(PaymentRepository paymentRepository) {

        return args -> {
            log.info("Preloading " + paymentRepository.save(new Payment("Bilbo Baggins", "burglar")));
            log.info("Preloading " + paymentRepository.save(new Payment("Frodo Baggins", "thief")));
        };
    }
}