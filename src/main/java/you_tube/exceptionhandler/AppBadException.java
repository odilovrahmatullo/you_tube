package you_tube.exceptionhandler;

public class AppBadException extends RuntimeException{
    public AppBadException(String message) {
        super(message);
    }
}
