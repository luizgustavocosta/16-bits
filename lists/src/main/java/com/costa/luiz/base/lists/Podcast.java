package com.costa.luiz.base.lists;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Podcast {

    private String name;
    private String country;
}
