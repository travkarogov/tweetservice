package twitsec.tweetservice.controller;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import twitsec.tweetservice.entity.Tweet;
import twitsec.tweetservice.repository.TweetRepository;

import java.net.URI;
import java.util.Optional;

@RestController("TweetController")
@RequestMapping("/tweets")
@CrossOrigin("*")
public class TweetController {

    public TweetController(TweetRepository tweetRepository){ this.tweetRepository = tweetRepository; }

    private final TweetRepository tweetRepository;

    @PostMapping("/")
    public ResponseEntity<Tweet> tweet(@RequestBody Tweet tweet) {
        Tweet createdTweet = tweetRepository.save(tweet);

        if(createdTweet == null){
            return ResponseEntity.notFound().build();
        }
        else{
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTweet.getId()).toUri();
            return ResponseEntity.created(uri).body(createdTweet);
        }
    }

    @GetMapping("{/id}")
    public Optional<Tweet> findById(@PathVariable("id") int id){
        var tweet = tweetRepository.findById(id);
        return tweet;
    }

    @GetMapping("/")
    public ResponseEntity<Object> healthy(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
