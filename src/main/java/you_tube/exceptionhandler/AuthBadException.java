package you_tube.exceptionhandler;

public class AuthBadException extends RuntimeException{
    public AuthBadException(String message) {
        super(message);
    }
}
