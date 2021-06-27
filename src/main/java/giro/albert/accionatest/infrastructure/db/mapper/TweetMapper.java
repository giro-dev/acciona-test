package giro.albert.accionatest.infrastructure.db.mapper;

import giro.albert.accionatest.domain.model.Tweet;
import giro.albert.accionatest.domain.model.User;
import giro.albert.accionatest.infrastructure.db.entity.TweetEntity;
import giro.albert.accionatest.infrastructure.db.entity.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
public class TweetMapper {

    public Tweet fromTweetEntity(TweetEntity tweetEntity){
        return Tweet.builder()
                .id(tweetEntity.getId())
                .lang(tweetEntity.getLang())
                .user(fromUserEntity(tweetEntity.getUser()))
                .text(tweetEntity.getText())
                .validated(tweetEntity.getValidated()).build();
    }
    public User fromUserEntity(UserEntity userEntity){
        return User.builder()
                .userId(userEntity.getUserId())
                .name(userEntity.getName())
                .screenName(userEntity.getScreenName())
                .followersCount(userEntity.getFollowersCount())
                .build();
    }

    public TweetEntity toTweetEntity(Tweet tweet){
        TweetEntity tweetEntity = new TweetEntity();
        tweetEntity.setId(tweet.getId());
        tweetEntity.setText(tweet.getText());
        tweetEntity.setLang(tweet.getLang());
        tweetEntity.setUser(toUserEntity(tweet.getUser()));
        tweetEntity.setValidated(tweet.isValidated());
        return tweetEntity;
    }
    public UserEntity toUserEntity(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(user.getUserId());
        userEntity.setName(user.getName());
        userEntity.setScreenName(user.getScreenName());
        userEntity.setFollowersCount(user.getFollowersCount());
        return userEntity;
    }

}
