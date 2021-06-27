package giro.albert.accionatest.app.services;

import giro.albert.accionatest.domain.reposirory.TweetRepository;
import giro.albert.accionatest.domain.model.Tweet;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static giro.albert.accionatest.domain.model.TweetObjectMother.getRandomTweet;
import static giro.albert.accionatest.domain.model.TweetObjectMother.getRandomTweetCollection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TweetApplicationServiceTest {

    @InjectMocks
    TweetApplicationService tweetApplicationService;

    @Mock
    TweetRepository tweetRepository;

    FilterService filterService = new FilterService();

    @BeforeEach
    void setup() {
        Set<String> langs = new HashSet<>();
        langs.add("es");
        langs.add("it");
        langs.add("fr");
        ReflectionTestUtils.setField(filterService, "validLanguages", langs);
        ReflectionTestUtils.setField(filterService, "followersThreshold", 1500);

        tweetApplicationService = new TweetApplicationService(tweetRepository, filterService);
    }


    @Test
    void getAllTweets() {
        when(tweetRepository.getAllTweets()).thenReturn(getRandomTweetCollection(15));

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
            Tweet tweet = getRandomTweet(1501, 1502, "es");
            tweetApplicationService.saveTweet(tweet);
            verify(tweetRepository, times(1)).saveTweet(tweet);
        }

        @Test
        @DisplayName("Tweet With less followers than threshold limit")
        void saveTweetWithInsuficientFollowers() {
            Tweet tweet = getRandomTweet(1500, 1501, "es");
            tweetApplicationService.saveTweet(tweet);
            verify(tweetRepository, times(0)).saveTweet(any());
        }

        @Test
        @DisplayName("Tweet With invalid Language")
        void saveTweetWithIvalidLang() {
            Tweet tweet = getRandomTweet(1501, 10000, "hi");
            tweetApplicationService.saveTweet(tweet);
            verify(tweetRepository, times(0)).saveTweet(any());
        }
    }

}