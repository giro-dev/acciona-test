package giro.albert.accionatest.infrastructure.rest.controller;

import giro.albert.accionatest.app.services.UserApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/twitter-api")
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userAplicationService;

    @GetMapping(path = "/users/{userId}/validatedTweets")
    @Operation(summary = "Get validated Tweets from user")
    public ResponseEntity getUserTweets(
            @PathVariable Long userId,
            @RequestParam(name = "valid", required = true) Boolean valid){
        return ResponseEntity.ok(userAplicationService.getValidatedTweets(userId, valid));
    }

}


