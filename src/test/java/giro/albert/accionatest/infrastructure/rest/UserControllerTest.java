package giro.albert.accionatest.infrastructure.rest;

import giro.albert.accionatest.app.services.UserApplicationService;
import giro.albert.accionatest.domain.model.Tweet;
import giro.albert.accionatest.infrastructure.rest.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static giro.albert.accionatest.domain.model.TweetObjectMother.getRandomTweetCollection;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserApplicationService userApplicationService;

    @Test
    void getUserTweets() throws Exception {
        List<Tweet> tweets = getRandomTweetCollection(2);
        when(userApplicationService.getValidatedTweets(24L, Boolean.TRUE)).thenReturn(tweets);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/twitter-api/users/{id}/validatedTweets", 24)
                .param("valid", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(tweets.get(0).getId()));

        verify(userApplicationService, times(1)).getValidatedTweets(24L, Boolean.TRUE);
    }

}