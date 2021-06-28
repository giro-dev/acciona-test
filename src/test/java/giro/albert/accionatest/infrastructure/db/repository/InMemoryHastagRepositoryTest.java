package giro.albert.accionatest.infrastructure.db.repository;

import giro.albert.accionatest.domain.model.Hashtag;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHastagRepositoryTest {

    InMemoryHastagRepository inMemoryHastagRepository;
    private Set<Hashtag> hashagCounter;

    @BeforeEach
    void setUp() {
        hashagCounter = new HashSet<>();
        hashagCounter.add(Hashtag.builder().hashtag("5").count(6).build());
        hashagCounter.add(Hashtag.builder().hashtag("2").count(9).build());
        hashagCounter.add(Hashtag.builder().hashtag("TheBest").count(10).build());
        hashagCounter.add(Hashtag.builder().hashtag("3").count(8).build());
        hashagCounter.add(Hashtag.builder().hashtag("theWorst").count(1).build());
        hashagCounter.add(Hashtag.builder().hashtag("6").count(5).build());
        hashagCounter.add(Hashtag.builder().hashtag("4").count(7).build());
        hashagCounter.add(Hashtag.builder().hashtag("7").count(4).build());
        hashagCounter.add(Hashtag.builder().hashtag("8").count(3).build());
        hashagCounter.add(Hashtag.builder().hashtag("9").count(2).build());
        inMemoryHastagRepository = new InMemoryHastagRepository(hashagCounter);
    }

    @Test
    void getSorted() {
        val hastagsTopSorted = inMemoryHastagRepository.getHastagsTopSorted(5);

        assertAll("All hastags Sorted",
                () -> assertEquals(5, hastagsTopSorted.size()),
                () -> assertEquals("TheBest", hastagsTopSorted.get(0).getHashtag()),
                () -> assertEquals("2", hastagsTopSorted.get(1).getHashtag()),
                () -> assertEquals("3", hastagsTopSorted.get(2).getHashtag()),
                () -> assertEquals("4", hastagsTopSorted.get(3).getHashtag()),
                () -> assertEquals("5", hastagsTopSorted.get(4).getHashtag())
        );
    }

    @Test
    void AddUnexistingHashtag() {
        final Set<Hashtag> newHastag = new HashSet<>();
        newHastag.add(Hashtag.builder().hashtag("TheNewOne").build());
        inMemoryHastagRepository.updateHastags(newHastag);

        assertAll("The new Hastag was added and has count 1",
                () -> assertEquals(11, hashagCounter.size()),
                () -> assertTrue(hashagCounter.stream()
                        .anyMatch(hashtag -> "TheNewOne".equals(hashtag.getHashtag())
                                && hashtag.getCount() == 1))
                );
    }
    @Test
    void AddexistingHashtag() {
        final Set<Hashtag> newHastag = new HashSet<>();
        newHastag.add(Hashtag.builder().hashtag("2").build());
        inMemoryHastagRepository.updateHastags(newHastag);

        assertAll("The Hastag was added +1 at counter",
                () -> assertEquals(10, hashagCounter.size()),
                () -> assertTrue(hashagCounter.stream()
                        .anyMatch(hashtag -> "2".equals(hashtag.getHashtag())
                                && hashtag.getCount() == 10))
        );
    }
    @Test
    void AddmixedExistingHashtag() {
        final Set<Hashtag> newHastag = new HashSet<>();
        newHastag.add(Hashtag.builder().hashtag("2").build());
        newHastag.add(Hashtag.builder().hashtag("2").build());
        newHastag.add(Hashtag.builder().hashtag("2").build());
        newHastag.add(Hashtag.builder().hashtag("TheNewOne").build());
        newHastag.add(Hashtag.builder().hashtag("TheNewOne").build());

        inMemoryHastagRepository.updateHastags(newHastag);

        assertAll("The Hastag was added +1 at counter",
                () -> assertEquals(11, hashagCounter.size(), "The size must increments by 1"),
                () -> assertTrue(hashagCounter.stream()
                        .anyMatch(hashtag -> "2".equals(hashtag.getHashtag())
                                && hashtag.getCount() == 10), "Counter of '2' must be 10"),
                () -> assertTrue(hashagCounter.stream()
                        .anyMatch(hashtag -> "TheNewOne".equals(hashtag.getHashtag())
                                && hashtag.getCount() == 1), "Counter of 'TheNewOne' must be 1")
        );
    }

}