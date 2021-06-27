package giro.albert.accionatest.infrastructure.stream.mapper;

import giro.albert.accionatest.domain.model.Hashtag;
import giro.albert.accionatest.domain.model.Tweet;
import giro.albert.accionatest.domain.model.User;
import org.springframework.stereotype.Component;
import twitter4j.HashtagEntity;
import twitter4j.Status;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StatusTweetMapper {

    public Tweet fromStatus(Status status){
        return Tweet.builder()
                .id(status.getId())
                .text(status.getText())
                .lang(status.getLang())
                .hashtags(parseHastags(status.getHashtagEntities()))
                .user(fromStatusUser(status.getUser())).build();
    }

    public User fromStatusUser(twitter4j.User user){
        return User.builder()
                .userId(user.getId())
                .screenName(user.getScreenName())
                .localization(user.getLocation())
                .followersCount(user.getFollowersCount())
                .build();
    }

    private Hashtag fromStatusHastagEntity(HashtagEntity hashtagEntity){
        return Hashtag.builder().hashtag(hashtagEntity.getText()).build();
    }

    private Set<Hashtag> parseHastags(HashtagEntity[] hashtagEntities) {
       return Arrays.stream(hashtagEntities)
               .map(this::fromStatusHastagEntity)
               .collect(Collectors.toSet());
    }

}
