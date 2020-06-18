package twitsec.tweetservice.controller.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoTokenProvidedException extends RuntimeException {
    public NoTokenProvidedException(final String message) {super(message);}
}
