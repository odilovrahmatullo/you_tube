package you_tube.exceptionHandler;

public class AppBadException extends RuntimeException{
    public AppBadException(String message) {
        super(message);
    }
}
