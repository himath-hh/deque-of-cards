/**
 * @author Himath Helessage
 * the DequeException class is a custom exception to handle deque exceptions
 * extends runtime exceptions to catch errors during runtime
 */
public class DequeException extends RuntimeException{
    /**
     * constructor with a detail message
     * @param message nature of the error
     */
    public DequeException(String message) {
        super(message);
    }
}
