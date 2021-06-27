package giro.albert.accionatest.app.services;

import giro.albert.accionatest.domain.reposirory.TweetRepository;
import giro.albert.accionatest.domain.model.Tweet;
import giro.albert.accionatest.infrastructure.rest.model.PatchRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class TweetApplicationService {

    private final TweetRepository tweetRepository;
    private final FilterService filterService;

    public Collection<Tweet> getAllTweets() {
        return tweetRepository.getAllTweets();
    }

    public Tweet validateTweet(Long id, PatchRequestBody validationRequest) {
        Tweet tweet = tweetRepository.getTweet(id);
        tweet.setValidated(validationRequest.getValidated());
        tweetRepository.saveTweet(tweet);
        return tweet;
    }

    public void saveTweet(Tweet tweet) {
        if (filterService.hasToBeSaved(tweet)){
            tweetRepository.saveTweet(tweet);
        }
    }
}
