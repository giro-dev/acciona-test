package giro.albert.accionatest.domain.model;

import java.util.ArrayList;
import java.util.List;

import static giro.albert.accionatest.domain.model.UserObjectMother.getRandomUser;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextLong;

public class TweetObjectMother {

    public static Tweet getRandomTweet(){
        return getRandomTweet(getRandomUser(1,10000), "es");
    }
    public static Tweet getRandomTweet(int minimunFollowers, int maximumFollowers, String lang){
        return getRandomTweet(getRandomUser(minimunFollowers,maximumFollowers), lang);
    }
    private static Tweet getRandomTweet(User randomUser, String lang){
        Tweet tweet = Tweet.builder()
                .id(nextLong())
                .text(randomAlphanumeric(250))
                .lang(lang)
                .user(randomUser)
                .build();
        return tweet;
    }

    public static List<Tweet> getRandomTweetCollection(Integer size){
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i<size; i++){
            tweets.add(getRandomTweet());
        }
        return tweets;
    }
}
