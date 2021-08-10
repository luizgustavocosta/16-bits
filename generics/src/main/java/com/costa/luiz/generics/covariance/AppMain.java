package com.costa.luiz.generics.covariance;

import com.costa.luiz.generics.covariance.hierarchy.Entertainment;
import com.costa.luiz.generics.covariance.hierarchy.Metal;
import com.costa.luiz.generics.covariance.hierarchy.Music;
import com.costa.luiz.generics.covariance.producer.ProducerOfEntertainment;
import com.costa.luiz.generics.covariance.producer.ProducerOfMusic;
import com.costa.luiz.generics.covariance.producer.ProducerOfTheMostInsaneSound;

public class AppMain {

    public static void main(String[] args) {
        ProducerOfEntertainment generic = new ProducerOfEntertainment();
        generic.play(Entertainment::new);
        generic.play(Music::new);
        generic.play(Metal::new);

        ProducerOfMusic music = new ProducerOfMusic();
        music.play(Metal::new);
        music.play(Music::new);

        ProducerOfTheMostInsaneSound insaneSound = new ProducerOfTheMostInsaneSound();
        insaneSound.play(Metal::new);
    }
}
