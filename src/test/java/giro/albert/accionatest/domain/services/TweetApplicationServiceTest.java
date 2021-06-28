package giro.albert.accionatest.domain.services;

import giro.albert.accionatest.domain.model.Tweet;
import giro.albert.accionatest.domain.model.TweetObjectMother;
import giro.albert.accionatest.domain.reposirory.HastagRepository;
import giro.albert.accionatest.domain.reposirory.TweetRepository;
import giro.albert.accionatest.domain.services.filter.TweetFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TweetApplicationServiceTest {

    @InjectMocks
    TweetApplicationService tweetApplicationService;

    @Mock
    TweetRepository tweetRepository;

    @Mock
    HastagRepository hastagRepository;

    TweetFilter filterService = new TweetFilter();

    @BeforeEach
    void setup() {
        Set<String> langs = new HashSet<>();
        langs.add("es");
        langs.add("it");
        langs.add("fr");
        ReflectionTestUtils.setField(filterService, "validLanguages", langs);
        ReflectionTestUtils.setField(filterService, "followersThreshold", 1500);

        tweetApplicationService = new TweetApplicationService(tweetRepository, hastagRepository, filterService);
    }


    @Test
    void getAllTweets() {
        Mockito.when(tweetRepository.getAllTweets()).thenReturn(TweetObjectMother.getRandomTweetCollection(15));

        Collection<Tweet> allTweets = tweetApplicationService.getAllTweets();

        assertEquals(15, allTweets.size());

    }

    @Test
    void validateTweet() {
    }

    @Nested
    class TweeterStreamLoad{
        @Test
        @DisplayName("Tweet With more followers than threshold limit")
        void saveTweetLangAndThresholdValid() {
            Tweet tweet = TweetObjectMother.getRandomTweet(1501, 1502, "es");
            tweetApplicationService.saveTweet(tweet);
            Mockito.verify(tweetRepository, Mockito.times(1)).saveTweet(tweet);
        }

        @Test
        @DisplayName("Tweet With less followers than threshold limit")
        void saveTweetWithInsuficientFollowers() {
            Tweet tweet = TweetObjectMother.getRandomTweet(1500, 1501, "es");
            tweetApplicationService.saveTweet(tweet);
            Mockito.verify(tweetRepository, Mockito.times(0)).saveTweet(ArgumentMatchers.any());
        }

        @Test
        @DisplayName("Tweet With invalid Language")
        void saveTweetWithIvalidLang() {
            Tweet tweet = TweetObjectMother.getRandomTweet(1501, 10000, "hi");
            tweetApplicationService.saveTweet(tweet);
            Mockito.verify(tweetRepository, Mockito.times(0)).saveTweet(ArgumentMatchers.any());
        }
    }

}