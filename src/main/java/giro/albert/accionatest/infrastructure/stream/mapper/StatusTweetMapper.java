package giro.albert.accionatest.infrastructure.stream.mapper;

import giro.albert.accionatest.domain.model.Tweet;
import giro.albert.accionatest.domain.model.User;
import org.springframework.stereotype.Component;
import twitter4j.Status;

@Component
public class StatusTweetMapper {

    public Tweet fromStatus(Status status){
        return Tweet.builder()
                .id(status.getId())
                .text(status.getText())
                .lang(status.getLang())
                .user(fromStatusUser(status.getUser())).build();
    }

    public User fromStatusUser(twitter4j.User user){
        return User.builder()
                .userId(user.getId())
                .name(user.getName())
                .screenName(user.getScreenName())
                .followersCount(user.getFollowersCount())
                .build();
    }

}
