package com.costa.luiz.quarkus.domain.item;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemRepository implements PanacheRepository<Item> {
}
