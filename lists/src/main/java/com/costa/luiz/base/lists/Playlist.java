package com.costa.luiz.base.lists;

import java.util.List;

public class Playlist {

    private final String name;
    private final List<Music> musics;

    public Playlist(String name, List<Music> musics) {
        this.name = name;
        this.musics = musics;
    }

    public String getName() {
        return name;
    }

    public List<Music> getMusics() {
        return musics;
    }


}

class Music {
    private final String name;
    private final String album;
    private final String country;

    Music(String name, String album, String country) {
        this.name = name;
        this.album = album;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getAlbum() {
        return album;
    }

    public String getCountry() {
        return country;
    }
}
