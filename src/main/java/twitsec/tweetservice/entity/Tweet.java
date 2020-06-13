package twitsec.tweetservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tweet")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int profileId;

    private String message;

//    @ManyToMany
//    @JoinTable(
//            name = "tweet_hashtag",
//            joinColumns = @JoinColumn(name = "tweeter_id"),
//            inverseJoinColumns = @JoinColumn(name = "hashtag_id")
//    )
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<Hashtag> hashtags;
}
