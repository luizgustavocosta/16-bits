package com.costa.luiz.domain;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class AuctionService {

    @Inject
    EntityManager entityManager;

    AtomicInteger id = new AtomicInteger();

    @Transactional
    public List<?> findAll() {
        Auction auction = new Auction();
        auction.id = id.incrementAndGet();
        auction.name = "John Doe";
        entityManager.merge(auction);
        return entityManager
                .createQuery("from Auction").getResultList();

    }
}
