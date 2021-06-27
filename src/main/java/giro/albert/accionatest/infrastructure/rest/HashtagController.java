package giro.albert.accionatest.infrastructure.rest;

import giro.albert.accionatest.app.services.HashtagApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/twitter-api")
public class HashtagController {

    private final HashtagApplicationService hashtagApplicationService;

    @GetMapping(path = "/hashtags")
    @Operation(summary = "Get Top Hastags")
    public ResponseEntity getAllTweets(@RequestParam(required = false) Integer topLimit){
        return ResponseEntity.ok(hashtagApplicationService.getTopHashTags(topLimit));
    }


}
