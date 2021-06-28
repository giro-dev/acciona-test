package giro.albert.accionatest.app.controller;

import giro.albert.accionatest.domain.services.UserApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/twitter-api")
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userAplicationService;

    @GetMapping(path = "/users/{userName}/tweets")
    @Operation(summary = "Get validated Tweets from user")
    public ResponseEntity getUserTweets(
            @PathVariable String userName,
            @RequestParam(name = "valid", required = true) Boolean valid){
        return ResponseEntity.ok(userAplicationService.getValidatedTweets(userName, valid));
    }

}


