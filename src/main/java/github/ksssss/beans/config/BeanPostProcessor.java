package github.ksssss.beans.config;

/**
 * 对bean的实列进行自定义的修改，例如检查标记接口或使用代理包装它们。
 *
 * @author ksssss
 * @since 2021/3/16 22:58
 */
public interface BeanPostProcessor {
    /**
     * 在bean初始化之前(例如:afterPropertiesSet和init-method)，该bean将已经用属性值填充
     *
     * @param bean
     * @param name
     * @return
     */
    Object postProcessBeforeInitialization(Object bean, String name);

    /**
     * 在bean初始化之后(例如:afterPropertiesSet和init-method)，该bean将已经用属性值填充
     *
     * @param bean
     * @param name
     * @return
     */
    Object postProcessAfterInitialization(Object bean, String name);
}
