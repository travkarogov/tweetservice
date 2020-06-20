package twitsec.tweetservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import twitsec.tweetservice.repository.TweetRepository;

@Component
@RequiredArgsConstructor
public class TweetListenerComponent {

    private final TweetRepository repository;
    private final JwtTokenComponent tokenComponent;

    @RabbitListener(queues = "${twitsec.rabbitmq.tweetqueue}")
    @Transactional
    public void deleteTweetsByProfileId(String token) {
        repository.deleteAllByProfileId(tokenComponent.getProfileIdFromToken(token));
    }
}
