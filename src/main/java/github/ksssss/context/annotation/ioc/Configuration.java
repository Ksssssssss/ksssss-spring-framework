package github.ksssss.context.annotation.ioc;

import java.lang.annotation.*;

/**
 * @author ksssss
 * @since 2021/3/22 23:33
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Configuration {
    String value();
}
