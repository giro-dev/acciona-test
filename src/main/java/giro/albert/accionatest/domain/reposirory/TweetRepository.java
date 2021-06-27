package giro.albert.accionatest.domain.reposirory;

import giro.albert.accionatest.domain.model.Tweet;

import java.util.Collection;
import java.util.List;

public interface TweetRepository {

    void saveTweet(Tweet tweet);

    Collection<Tweet> getAllTweets();

    Tweet getTweet(Long id);

    List<Tweet> getValidatedTweetsByUser(String userId, Boolean validated);
}
