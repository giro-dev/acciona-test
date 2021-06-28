package giro.albert.accionatest.domain.services;

import giro.albert.accionatest.domain.reposirory.HastagRepository;
import giro.albert.accionatest.domain.reposirory.TweetRepository;
import giro.albert.accionatest.domain.model.Tweet;
import giro.albert.accionatest.domain.services.filter.TweetFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class TweetApplicationService {

    private final TweetRepository tweetRepository;
    private final HastagRepository hastagRepository;
    private final TweetFilter filterService;

    public Collection<Tweet> getAllTweets() {
        return tweetRepository.getAllTweets();
    }

    public Tweet validateTweet(Long id, Boolean isValidated) {
        Tweet tweet = tweetRepository.getTweet(id);
        tweet.setValid(isValidated);
        tweetRepository.saveTweet(tweet);
        return tweet;
    }

    public void saveTweet(Tweet tweet) {
        if (filterService.hasToBeSaved(tweet)){
            tweetRepository.saveTweet(tweet);
            hastagRepository.updateHastags(tweet.getHashtags());
        }
    }
}
