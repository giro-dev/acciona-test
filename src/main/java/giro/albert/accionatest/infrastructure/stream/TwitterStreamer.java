package giro.albert.accionatest.infrastructure.stream;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import twitter4j.StatusListener;
import twitter4j.TwitterStreamFactory;

@Component
@RequiredArgsConstructor
public class TwitterStreamer {

    private final StatusListener streamListener;

    @EventListener(ApplicationReadyEvent.class)
    public void setupListener(){
        TwitterStreamFactory.getSingleton()
                .addListener(streamListener)
                .sample();
    }
}
