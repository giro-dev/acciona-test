package giro.albert.accionatest.infrastructure.rest;

import giro.albert.accionatest.domain.model.Tweet;
import giro.albert.accionatest.app.services.TweetApplicationService;
import giro.albert.accionatest.infrastructure.rest.model.PatchRequestBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.NoSuchElementException;

import static giro.albert.accionatest.domain.model.TweetObjectMother.getRandomTweet;
import static giro.albert.accionatest.domain.model.TweetObjectMother.getRandomTweetCollection;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TweetController.class)
public class TweetControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TweetApplicationService tweetsService;

    @Captor
    ArgumentCaptor<PatchRequestBody> validationRequest;

    @Test
    void getTweets() throws Exception {
        List<Tweet> tweets = getRandomTweetCollection(3);
        when(tweetsService.getAllTweets()).thenReturn(tweets);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/twitter-api/tweets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id").value(tweets.get(0).getId()));

        verify(tweetsService, times(1)).getAllTweets();
    }


    @Test
    void validateTweet() throws Exception {
        Tweet tweet = getRandomTweet();
        when(tweetsService.validateTweet(eq(15L), eq(new PatchRequestBody(Boolean.TRUE)))).thenReturn(tweet);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .patch("/twitter-api/tweets/{id}", 15L)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content("{\"validated\" : true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(tweet.getId()));

        assertAll("Check Call to update",
                ()-> verify(tweetsService, times(1)).validateTweet(eq(15L),  validationRequest.capture()),
                ()-> assertTrue(validationRequest.getValue().getValidated())
        );

    }
  @Test
  @DisplayName("When validate a non existing TweetId throw error")
    void validateNoExistingTweet() throws Exception {
        Tweet tweet = getRandomTweet();
        when(tweetsService.validateTweet(eq(15L),  eq(new PatchRequestBody(Boolean.TRUE)))).thenThrow(new NoSuchElementException("No exists tweets with this Id"));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .patch("/twitter-api/tweets/{id}", 15L)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content("{\"validated\" : true}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("No exists tweets with this Id"));

      assertAll("Check Call to update",
              ()->verify(tweetsService, times(1)).validateTweet(eq(15L),  validationRequest.capture()),
              ()-> assertTrue(validationRequest.getValue().getValidated())
      );    }

}
