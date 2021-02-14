package com.costa.luiz;

import com.costa.luiz.quarkus.domain.auction.Auction;
import com.costa.luiz.quarkus.domain.item.Item;
import com.costa.luiz.quarkus.domain.user.User;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.stream.IntStream;

@Singleton
public class DataLoader {

    private static final Logger LOGGER = Logger.getLogger(DataLoader.class.getName());

    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        // reset and load all test users
        LOGGER.info("Loading data");
        IntStream.rangeClosed(0, 10).map(index -> index + 1)
                .forEach(index -> {
                    Auction auction = new Auction();
                    auction.status = "Open";
                    auction.startDate = ZonedDateTime.now();
                    auction.endDate = ZonedDateTime.now().plusDays(2);
                    PanacheEntityBase.persist(auction);
                    PanacheEntityBase.persist(new Item("Item "+index, "Desc "+index));
                    PanacheEntityBase.persist(new User("User "+index, "Last "+index));
                });


        LOGGER.info("Data loaded");
    }

}
