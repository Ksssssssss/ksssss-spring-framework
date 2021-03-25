package github.ksssss.exception;

/**
 * @author ksssss
 * @since 2021/3/25 23:12
 */
public class NoSuchBeanException extends BeansException {
    public NoSuchBeanException(String message) {
        super(message);
    }

    public NoSuchBeanException(String message, Throwable cause) {
        super(message, cause);
    }
}
