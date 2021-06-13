package com.costa.luiz.base.lists;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Music {

    private final int id;
    private final String name;
    private final String album;
    private final String country;

}
