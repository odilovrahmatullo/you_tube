package you_tube.ExceptionHandler;

public class AppBadException extends RuntimeException{
    public AppBadException(String message) {
        super(message);
    }
}
