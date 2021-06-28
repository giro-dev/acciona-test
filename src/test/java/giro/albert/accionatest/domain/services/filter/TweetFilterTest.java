package giro.albert.accionatest.domain.services.filter;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Spy;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TweetFilterTest {

    @Spy
    private TweetFilter tweetFilter = new TweetFilter();

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(tweetFilter, "followersThreshold", 1500);
        ReflectionTestUtils.setField(tweetFilter, "validLanguages", Arrays.asList("es","fr"));
    }

}