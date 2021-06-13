package com.costa.luiz.base.lists;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Immutable and Unmodifiable")
class PlaylistTest {

    @Nested
    @DisplayName("Elements in the List are mutable")
    class Immutable {

        @Test
        @DisplayName("Change the podcast")
        void givenAListWhenAnyElementIsChangedThenRetainTheChange() {
            // Given
            int id = 1;
            int otherId = 2;
            Podcast firstPodcast = Podcast.builder().id(id)
                    .name("Sixteen bits").country("Brazil").build();
            Podcast secondPodcast = Podcast.builder().id(otherId)
                    .name("BBC Learn English").country("UK").build();

            List<Podcast> podcasts = List.of(firstPodcast, secondPodcast);

            Playlist playlist = Playlist.builder().podcasts(podcasts).build();

            //When
            secondPodcast.setCountry("Spain");

            // Then
            assertThat(podcasts)
                    .usingRecursiveFieldByFieldElementComparator()
                    .isEqualTo(playlist.getPodcasts());

            Podcast podcastInPlaylist = playlist.getPodcasts().stream()
                    .filter(podcast -> otherId == podcast.getId())
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);

            assertThat(secondPodcast).isEqualTo(podcastInPlaylist);
        }
    }

    @Nested
    @DisplayName("Structure of the List itself cannot be altered")
    class Unmodifiable {
        @Test
        void givenAListWhenAnyElementIsChangedThenRemainTheSame() {
            //Given
            String musicName = "Hooked on a Felling";
            Music firstMusic = Music.builder().name(musicName).build();
            final Music secondMusic = Music.builder().name("Go All the Way").build();

            List<Music> musics = List.of(firstMusic, secondMusic);
            Playlist playlist = Playlist.builder()
                    .name("Guardians of the Galaxy Vol. I")
                    .musics(musics)
                    .build();

            List<Music> expectedMusics = List.of(firstMusic, secondMusic);

            // When
            firstMusic = Music.builder().name("The Chain").build();

            String musicFromPlaylist = playlist.getMusics().stream()
                    .map(Music::getName)
                    .filter(musicName::equals)
                    .findFirst().orElseThrow(IllegalArgumentException::new);

            // Then
            assertThat(firstMusic.getName()).isNotEqualTo(musicFromPlaylist);
            assertThat(expectedMusics)
                    .usingRecursiveFieldByFieldElementComparator()
                    .isEqualTo(playlist.getMusics());

            assertThat(expectedMusics)
                    .usingRecursiveFieldByFieldElementComparator()
                    .isEqualTo(musics);
        }
    }
}