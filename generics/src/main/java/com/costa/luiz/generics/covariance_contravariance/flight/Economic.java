package com.costa.luiz.generics.covariance_contravariance.flight;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
class Economic implements SeatConfiguration {

    protected List<String> passengers;

    @Override
    public List<String> passengers() {
        return passengers;
    }
}
