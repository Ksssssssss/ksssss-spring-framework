package github.ksssss.exception;

/**
 * @author ksssss
 * @since 2021/3/25 23:12
 */
public class BeanRegisteredException extends BeansException {
    public BeanRegisteredException(String message) {
        super(message);
    }

    public BeanRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }
}
