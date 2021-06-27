package giro.albert.accionatest.infrastructure.rest;

import giro.albert.accionatest.infrastructure.rest.model.PatchRequestBody;
import giro.albert.accionatest.app.services.TweetApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/twitter-api")
public class TweetController {

    private final TweetApplicationService tweetsService;

    @GetMapping(path = "/tweets")
    public ResponseEntity getAllTweets(){
        return ResponseEntity.ok(tweetsService.getAllTweets());
    }

    @PatchMapping(path = "/tweets/{id}/")
    public ResponseEntity validateTweet(@PathVariable Long id, @RequestBody PatchRequestBody validationRequest) {
        return ResponseEntity.ok(tweetsService.validateTweet(id, validationRequest));
    }


}
