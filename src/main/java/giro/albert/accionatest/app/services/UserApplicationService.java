package giro.albert.accionatest.app.services;

import giro.albert.accionatest.domain.model.Tweet;
import giro.albert.accionatest.domain.reposirory.TweetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class UserApplicationService {

    private final TweetRepository tweetRepository;

    public Collection<Tweet> getValidatedTweets(Long userId, Boolean validated) {
        return tweetRepository.getValidatedTweets(userId, validated);
    }
}
