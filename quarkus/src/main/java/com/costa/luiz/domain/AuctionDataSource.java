package com.costa.luiz.domain;

import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;

import javax.inject.Inject;

public class AuctionDataSource {

    @Inject
    AgroalDataSource defaultDataSource;

    @Inject
    @DataSource("users")
    AgroalDataSource usersDataSource;

    @Inject
    @DataSource("inventory")
    AgroalDataSource inventoryDataSource;
}
