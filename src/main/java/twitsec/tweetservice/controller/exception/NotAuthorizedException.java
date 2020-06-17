package twitsec.tweetservice.controller.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException(final String message) {super(message);}
}
