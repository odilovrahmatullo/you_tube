package you_tube.exceptionhandler;

public class AppBadRequest extends RuntimeException {
    public AppBadRequest(String message) {
        super(message);
    }
}
