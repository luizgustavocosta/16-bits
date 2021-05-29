package com.costa.luiz.base;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StructureTests {

    @Test
    void allowOnlyOneKey() {
        Set<String> mySet = new HashSet<>(3);
        mySet.add("A");
        mySet.add("A");
        mySet.add("A");

        assertThat(mySet.size()).isEqualTo(1);
        mySet.remove("A");
        assertThat(mySet).isEmpty();
    }

    @Test
    void howTheQueueWorks() {
        Deque<String> myDeque = new ArrayDeque<>();
        myDeque.add("A");
        myDeque.add("B");
        myDeque.add("C");

        final String firstElement = myDeque.pop(); //Removes and returns the first element of this deque.
        final String removeTheHead = myDeque.poll(); //Retrieves and removes the first element
        assertThat(firstElement).isEqualTo("A");
        assertThat(removeTheHead).isEqualTo("B");
        assertThat(myDeque.size()).isEqualTo(1);
    }

    @Test
    void workingWithMaps() {
        HashMap<String, String> urlShortening = new HashMap<>();
        urlShortening.put("bit.ly/netflix", "www.netflix.com");
        urlShortening.put("bit.ly/spring", "www.springframework.com");
        urlShortening.put("bit.ly/quarkus", "www.quarkus.com");
        urlShortening.put("bit.ly/java", "www.openjdk.com");

        urlShortening.putIfAbsent("bit.ly/16-bits", "https://www.youtube.com/channel/UCPOdfYoz_hTNrngfouB24jQ");
        urlShortening.putIfAbsent("bit.ly/java", "www.openjdk.com");

        assertThat(urlShortening.size()).isEqualTo(5);
        assertThat(urlShortening.get("bit.ly/java")).isEqualTo("www.openjdk.com");
    }

    @Test
    void workingWithString() {
        final String myName = String.join(" ", new String[]{"Luiz", "Gustavo", "De Oliveira", "Costa"});

        assertThat(myName).as("Name expected").isEqualTo("Luiz Gustavo De Oliveira Costa");

        assertFalse(myName.isEmpty());
        assertFalse(myName.isBlank());

    }
}
