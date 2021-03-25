package github.ksssss.exception;

/**
 * @author ksssss
 * @since 2021/3/25 23:05
 */
public abstract class BeansException extends RuntimeException {
    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
