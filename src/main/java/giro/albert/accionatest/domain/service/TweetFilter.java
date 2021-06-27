package giro.albert.accionatest.domain.service;

import giro.albert.accionatest.domain.model.Tweet;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import twitter4j.Status;

import java.util.Set;
import java.util.function.Predicate;

@Component
public class TweetFilter {

    @Value("${twitter.accepted.languages:es,fr,it}")
    private Set<String> validLanguages;
    @Value("${twitter.minimum.followers:1500}")
    private Integer followersThreshold;

    private Predicate<Tweet> acceptedLanguage = tweet -> getValidLanguages().contains(tweet.getLang()) ;
    private Predicate<Tweet> hasMinimumFollowers = tweet -> tweet.getUser().getFollowersCount() > getFollowersThreshold();

    public boolean hasToBeSaved(Tweet tweet){
        return acceptedLanguage.and(hasMinimumFollowers).test(tweet);
    }

    public Set<String> getValidLanguages() {
        return validLanguages;
    }

    public Integer getFollowersThreshold() {
        return followersThreshold;
    }
}


