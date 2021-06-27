package giro.albert.accionatest.infrastructure.stream;

import giro.albert.accionatest.app.services.TweetApplicationService;
import giro.albert.accionatest.infrastructure.stream.mapper.StatusTweetMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class TwitterStatusListener implements StatusListener {

    private final StatusTweetMapper tweetMapper;
    private final TweetApplicationService tweetApplicationService;

    @Override
    public void onStatus(Status status) {
        log.debug("onStatus on statusStreming -> {}",status.getText());
        tweetApplicationService.saveTweet(tweetMapper.fromStatus(status));
    }

    @Override
    public void onException(Exception e) {
        log.error("Exception on statusStreming: {}", e.getMessage());
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
        log.debug("onDeletionNotice on statusStreming -> {}", statusDeletionNotice.getStatusId());

    }

    @Override
    public void onTrackLimitationNotice(int i) {
        log.debug("onTrackLimitationNotice on statusStreming -> {}",i);
    }

    @Override
    public void onScrubGeo(long l, long l1) {
        log.debug("onScrubGeo on statusStreming -> {}-{}",l, l1);

    }

    @Override
    public void onStallWarning(StallWarning stallWarning) {
        log.debug("onStallWarning on statusStreming -> {}",stallWarning.getMessage());

    }
}
