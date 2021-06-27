package giro.albert.accionatest.infrastructure.db.repository;

import giro.albert.accionatest.domain.model.Hashtag;
import giro.albert.accionatest.domain.model.Tweet;
import giro.albert.accionatest.domain.reposirory.TweetRepository;
import giro.albert.accionatest.infrastructure.db.entity.TweetEntity;
import giro.albert.accionatest.infrastructure.db.mapper.TweetMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class databaseTweetRepository implements TweetRepository {

    private final JPATweetRepository jpaTweetRepository;
    private final TweetMapper tweetMapper;

    @Override
    public void saveTweet(Tweet tweet) {
        try {
            jpaTweetRepository.save(tweetMapper.toTweetEntity(tweet));
        } catch (Exception e){
            log.error("Error saving Tweet Info: {}", e.getMessage() );
        }
    }

    @Override
    public Collection<Tweet> getAllTweets() {
        List<TweetEntity> all = jpaTweetRepository.findAll();
        if (CollectionUtils.isEmpty(all)){
            return Collections.emptyList();
        }
        return all.stream()
                .map(tweetMapper::fromTweetEntity)
                .collect(Collectors.toList());

    }

    @Override
    public Tweet getTweet(Long id) {
        TweetEntity tweetEntity = jpaTweetRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("There is no tweet with id " + id));
        return tweetMapper.fromTweetEntity(tweetEntity);
    }

    @Override
    public List<Tweet> getValidatedTweetsByUser(Long userId, Boolean validated) {
        return jpaTweetRepository.findValidatedByUser(userId, validated).stream()
                .map(tweetMapper::fromTweetEntity).collect(Collectors.toList());
    }




}
