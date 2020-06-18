package twitsec.tweetservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import twitsec.tweetservice.controller.exception.NoTokenProvidedException;
import twitsec.tweetservice.controller.exception.NotAuthorizedException;
import twitsec.tweetservice.entity.Tweet;
import twitsec.tweetservice.model.Role;
import twitsec.tweetservice.repository.TweetRepository;
import twitsec.tweetservice.service.JwtTokenComponent;

import java.net.URI;
import java.util.Optional;

@RestController("TweetController")
@RequestMapping("/tweets")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TweetController {

    private final TweetRepository tweetRepository;
    private final JwtTokenComponent tokenComponent;

    @PostMapping
    public ResponseEntity<Tweet> tweet(@RequestHeader("Authorization") final String token, @RequestBody Tweet tweet) {
        if(token.isEmpty()) throw new NoTokenProvidedException("Token is empty");

        if(tokenComponent.validateJwt(token) && tokenComponent.getRoleFromToken(token) == Role.USER){
            tweet.setProfileId(tokenComponent.getProfileIdFromToken(token));

            Tweet createdTweet = tweetRepository.save(tweet);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTweet.getId()).toUri();
            return ResponseEntity.created(uri).body(createdTweet);
        }

        throw new NotAuthorizedException("Not authorized to perform this action");
    }

    @GetMapping("/{id}")
    public Optional<Tweet> findById(@PathVariable("id") final int id){
        return tweetRepository.findById(id);
    }
}
