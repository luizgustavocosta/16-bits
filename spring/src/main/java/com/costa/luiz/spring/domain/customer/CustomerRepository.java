package com.costa.luiz.spring.domain.customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Query("select c from Customer c where c.firstName like %?1%")
    List<Customer> findAllByFirstName(String name);

    @Query("select c from Customer c where c.lastName like %?1%")
    List<Customer> findAllByLastName(String name);
}
