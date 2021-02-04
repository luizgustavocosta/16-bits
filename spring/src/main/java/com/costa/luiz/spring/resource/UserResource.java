package com.costa.luiz.spring.resource;

import com.costa.luiz.spring.database.relational.User;
import com.costa.luiz.spring.database.relational.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/spring/user")
public class UserResource {

    private final UserRepository repository;

    public UserResource(@Autowired UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<User> allUsers() {
        return Flux.fromStream(Stream.empty());
    }

    @GetMapping("{id}")
    public Mono<User> customerById(@PathVariable("id") String id) {
        repository.save(new User("Luiz","Gustavo"));
        return Mono.just(repository.findById(id).get());
    }
}
