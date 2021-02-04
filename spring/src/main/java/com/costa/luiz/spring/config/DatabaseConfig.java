package com.costa.luiz.spring.config;

import com.costa.luiz.spring.database.relational.User;
import com.costa.luiz.spring.database.relational.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DatabaseConfig {

    @Bean
    UserRepository getRepository() {
        return new UserRepository() {
            @Override
            public <S extends User> S save(S s) {
                return null;
            }

            @Override
            public <S extends User> Iterable<S> saveAll(Iterable<S> iterable) {
                return null;
            }

            @Override
            public Optional<User> findById(String s) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(String s) {
                return false;
            }

            @Override
            public Iterable<User> findAll() {
                return null;
            }

            @Override
            public Iterable<User> findAllById(Iterable<String> iterable) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(String s) {

            }

            @Override
            public void delete(User user) {

            }

            @Override
            public void deleteAll(Iterable<? extends User> iterable) {

            }

            @Override
            public void deleteAll() {

            }
        };
    }
}
