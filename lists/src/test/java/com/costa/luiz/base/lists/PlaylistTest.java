package com.costa.luiz.base.lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Immutable and Unmodifiable")
class PlaylistTest {

    //UNMODIFIABLE -> that the structure of the List itself cannot be altered
    //IMMUTABLE    -> in that if the elements in the List are mutable (which is very common in Java), you can change the contents of the elements themselves. You cannot, however, alter the List so that it refers to any different elements, nor can you add or remove any elements.
    List<Music> musics = new ArrayList<>();

    @BeforeEach
    void setUp() {
        musics.add(Music.builder().name("A").build());
        musics.add(Music.builder().name("B").build());
        musics.add(Music.builder().name("C").build());
    }

    @Test
    void shouldAllowModifyAnPodcast() {
        var originalName = "Lugocast";
        Podcast podcast = Podcast.builder().name(originalName).build();
        Playlist playlist = Playlist.builder()
                .name("Guardians of the Galaxy Vol I")
                .musics(musics)
                .podcasts(List.of(podcast))
                .build();

        assertEquals(originalName, playlist.getPodcasts().iterator().next().getName());

        //Cannot add/remove a element but the list element is modifiable
        var modifiedName = "16 Bits";
        podcast.setName(modifiedName);

        assertEquals(modifiedName, playlist.getPodcasts().iterator().next().getName());
    }

    @Test
    void modifyAList() {
        Playlist playlist = Playlist.builder()
                .name("Guardians of the Galaxy Vol I")
                .musics(musics)
                .build();

        addNewMusic();

        assertEquals(4, playlist.getMusics().size());
    }

    private void addNewMusic() {
        musics.add(Music.builder().name("Invasion music").build());
    }

    @Test
    void shouldCopyToImmutableCollection() {
        List<Music> copiedMusics = List.copyOf(musics);
        Playlist playlist = Playlist.builder()
                .name("Guardians of the Galaxy Vol I")
                .musics(copiedMusics)
                .build();

        addNewMusic(musics, "Invasion music");
        assertEquals(3, playlist.getMusics().size());
        assertEquals(4, musics.size());

    }

    private void addNewMusic(List<Music> musics, String s) {
        musics.add(Music.builder().name(s).build());
    }

    @Test
    void immutableCreatingAgain() {
        List<Music> immutableMusics = List.of(Music.builder().name("A").build(),
                Music.builder().name("B").build(),
                Music.builder().name("C").build());

        Playlist playlist = Playlist.builder()
                .name("Guardians of the Galaxy Vol I")
                .musics(immutableMusics)
                .build();

        ;
        Throwable throwable = catchThrowable(() -> {
            immutableMusics.add(Music.builder().name("Invasion music").build());
        });
        assertNotNull(throwable);
        assertEquals(3, playlist.getMusics().size());

    }

    @Test
    void unmodifiableUsingStream() {
        List<Music> immutable = musics.stream().collect(Collectors.toUnmodifiableList());
        Playlist playlist = Playlist.builder()
                .name("Guardians of the Galaxy Vol I")
                .musics(immutable)
                .build();

        addNewMusic();

        assertEquals(3, playlist.getMusics().size());
    }

}
//    String[] arr = new String[] {"A1", "A2"};
//
//    List<String> ls = new ArrayList<>();
//ls.add("L1");ls.add("L2");
//
//        List<String> la = Arrays.asList(arr);
//        List<String> lf = List.of(arr);
//        List<String> lc = List.copyOf(ls);
//        List<String> lu = Collections.unmodifiableList(ls);
//
//        arr[1]="A3";
//        ls.set(1, "L3");
//
//// line n1
//
//        System.out.println("la=" + la);
//        System.out.println("lf=" + lf);
//        System.out.println("lc=" + lc);
//        System.out.println("lu=" + lu);