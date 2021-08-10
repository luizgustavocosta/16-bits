package com.costa.luiz.generics.covariance_contravariance.flight;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
class StandUp extends Economic {
    private List<String> passengers;
}
