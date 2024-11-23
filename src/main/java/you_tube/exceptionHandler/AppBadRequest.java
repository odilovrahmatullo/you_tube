package you_tube.exceptionHandler;

public class AppBadRequest extends RuntimeException {
    public AppBadRequest(String message) {
        super(message);
    }
}
