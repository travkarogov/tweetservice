package twitsec.tweetservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        if(createdTweet.getProfileId() == tweet.getProfileId()){
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTweet.getId()).toUri();
            return ResponseEntity.created(uri).body(createdTweet);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{/id}")
    public Optional<Tweet> findById(@PathVariable("id") int id){
        return tweetRepository.findById(id);
    }

    @GetMapping
    public Page<Tweet> findAll(Pageable pageable){
        return tweetRepository.findAll(pageable);
    }
}
