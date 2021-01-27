package com.costa.luiz.domain;


import com.costa.luiz.repository.Auction;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.List;

@ApplicationScoped
public class HelloService {

    @Inject
    EntityManager entityManager;

    public List<?> findAll() {
        Auction auction = new Auction();
        auction.setId(BigInteger.ONE);
        entityManager.persist(auction);
        return entityManager
                .createQuery("from Auction").getResultList();

    }

}
