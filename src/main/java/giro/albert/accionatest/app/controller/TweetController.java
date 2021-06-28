package giro.albert.accionatest.app.controller;

import giro.albert.accionatest.app.model.PatchTweettBody;
import giro.albert.accionatest.domain.services.TweetApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/twitter-api")
public class TweetController {

    private final TweetApplicationService tweetsService;

    @GetMapping(path = "/tweets")
    @Operation(summary = "Get all saved Tweets")
    public ResponseEntity getAllTweets(){
        return ResponseEntity.ok(tweetsService.getAllTweets());
    }

    @PatchMapping(path = "/tweets/{id}")
    @Operation(summary = "Set validation status on selected tweet")
    public ResponseEntity validateTweet(@PathVariable Long id, @RequestBody PatchTweettBody validationRequest) {
        return ResponseEntity.ok(tweetsService.validateTweet(id, validationRequest.getValid()));
    }


}
