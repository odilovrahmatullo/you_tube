package you_tube.ExceptionHandler;

public class AppBadRequest extends RuntimeException {
    public AppBadRequest(String message) {
        super(message);
    }
}
