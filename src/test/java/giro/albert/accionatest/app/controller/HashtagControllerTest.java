package giro.albert.accionatest.app.controller;

import giro.albert.accionatest.domain.services.HashtagApplicationService;
import giro.albert.accionatest.domain.model.Hashtag;
import giro.albert.accionatest.app.controller.HashtagController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static giro.albert.accionatest.domain.model.HashtagObjectMother.getRandomCollection;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(HashtagController.class)
class HashtagControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private HashtagApplicationService hashtagApplicationService;

    @Test
    void getUserTweets() throws Exception {
        List<Hashtag> hashtags = getRandomCollection(2);
        Integer topLimit = 10;
        when(hashtagApplicationService.getTopHashTags(topLimit)).thenReturn(hashtags);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/twitter-api/hashtags", 24)
                .param("topLimit", "10"))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].hashtag").value(hashtags.get(0).getHashtag()));

        verify(hashtagApplicationService, times(1)).getTopHashTags(topLimit);
    }



}