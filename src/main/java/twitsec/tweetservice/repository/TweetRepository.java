package twitsec.tweetservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import twitsec.tweetservice.entity.Tweet;

@CrossOrigin("*")
public interface TweetRepository extends PagingAndSortingRepository<Tweet, Integer> {

    @RestResource(path = "/byProfileId")
    Page<Tweet> getTweetsByProfileId(Pageable pageable, int profileId);
}
