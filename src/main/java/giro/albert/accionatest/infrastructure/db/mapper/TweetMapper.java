package giro.albert.accionatest.infrastructure.db.mapper;

import giro.albert.accionatest.domain.model.Tweet;
import giro.albert.accionatest.infrastructure.db.entity.TweetEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TweetMapper {

    private final HashtagMapper hashtagMapper;
    private final UserMapper userMapper;

    public Tweet fromTweetEntity(TweetEntity tweetEntity){
        return Tweet.builder()
                .id(tweetEntity.getId())
                .text(tweetEntity.getText())
                .valid(tweetEntity.getValidated())
                .user(userMapper.fromUserEntity(tweetEntity.getUser()))
                .hashtags(hashtagMapper.fromHashtagEntity(tweetEntity.getHashtags()))
                .build();
    }

    public TweetEntity toTweetEntity(Tweet tweet){
        TweetEntity tweetEntity = new TweetEntity();
        tweetEntity.setId(tweet.getId());
        tweetEntity.setText(tweet.getText());
        tweetEntity.setValidated(tweet.isValid());
        tweetEntity.setUser(userMapper.toUserEntity(tweet.getUser()));
        tweetEntity.setHashtags(hashtagMapper.toHashtagEntity(tweet.getHashtags()));
        return tweetEntity;
    }


}
