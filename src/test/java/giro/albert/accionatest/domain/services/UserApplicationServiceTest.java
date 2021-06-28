package giro.albert.accionatest.domain.services;

import giro.albert.accionatest.domain.model.Tweet;
import giro.albert.accionatest.domain.model.TweetObjectMother;
import giro.albert.accionatest.domain.reposirory.TweetRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserApplicationServiceTest {


    private final String user1 = "user1";
    private final String user2 = "user2";

    @InjectMocks
    private UserApplicationService userApplicationService;
    @Mock
    private TweetRepository tweetRepository;

    List<Tweet> validatedTweets, noValidatedTweets;


    @BeforeEach
    void setUp() {
        validatedTweets = TweetObjectMother.getRandomTweetCollection(2);
        noValidatedTweets = TweetObjectMother.getRandomTweetCollection(15);
    }

    @Test
    void UserTweetsValidated() {
        when(tweetRepository.getValidatedTweetsByUser(user1, true)).thenReturn(validatedTweets);

        val response = userApplicationService.getValidatedTweets(user1, true);

        assertEquals(2, response.size());
    }

    @Test
    void UserTweetsNotValidated() {
        when(tweetRepository.getValidatedTweetsByUser(user1, false)).thenReturn(noValidatedTweets);

        val response = userApplicationService.getValidatedTweets(user1, false);

        assertEquals(15, response.size());
    }

    @ParameterizedTest
    @ValueSource(booleans =  {true, false})
    void NoUserTweetsToShow(Boolean validated) {
        when(tweetRepository.getValidatedTweetsByUser(eq(user2), any())).thenReturn(Collections.emptyList());

        val response = userApplicationService.getValidatedTweets(user2, validated);

        assertEquals(0, response.size());
    }

}