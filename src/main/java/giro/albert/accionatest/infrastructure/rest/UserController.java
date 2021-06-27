package giro.albert.accionatest.infrastructure.rest;

import giro.albert.accionatest.app.services.UserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/twitter-api")
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userAplicationService;

    @GetMapping(path = "/users/{userId}/validatedTweets")
    public ResponseEntity getUserTweets(@PathVariable Long userId){
        return ResponseEntity.ok(userAplicationService.getValidatedTweets(userId, Boolean.TRUE));
    }

}


