package com.costa.luiz.base.lists;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString
public class Podcast {

    private int id;
    private String name;
    private String country;
    private String genre;
}
