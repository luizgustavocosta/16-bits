package com.costa.luiz.base.lists;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class Playlist {

    private final String name;
    private final List<Music> musics;
    private List<Podcast> podcasts;

}
