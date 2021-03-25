package github.ksssss.context.annotation.ioc;

import java.lang.annotation.*;

/**
 * @author ksssss
 * @since 2021/3/22 23:28
 */
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    boolean required() default true;
}
