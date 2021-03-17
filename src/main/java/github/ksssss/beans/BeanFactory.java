package github.ksssss.beans;

import java.util.NoSuchElementException;

/**
 * @author ksssss
 * @since 2021/3/9 23:33
 */
public interface BeanFactory {
    String FACTORY_BEAN_PREFIX = "&";

    /**
     * 获取bean通过name
     *
     * @param name
     * @return
     */
    Object getBean(String name);

    /**
     * 通过类型和名字获取bean
     *
     * @param name
     * @param requireType
     * @param <T>
     * @return
     */
    <T> T getBean(String name, Class<T> requireType);

    /**
     * 获取bean通过构造参数
     *
     * @param name
     * @param obj
     * @return
     */
    Object getBean(String name, Object... obj);

    /**
     * 判读容器是否包含指定名字的bean
     *
     * @param name
     * @return
     */
    boolean containsBean(String name);

    /**
     * 查询该bean在容器中是否是单例
     *
     * @param name
     * @return
     */
    boolean isSingleton(String name);

    /**
     * 查询该bean在容器中是否是isPrototype
     *
     * @param name
     * @return
     */
    boolean isPrototype(String name);

    /**
     * 查询指定名字的bean的类型是否和目标的类型一致
     *
     * @param name
     * @param targetType
     * @return
     */
    boolean isTypeMatch(String name, Class targetType);

    /**
     * 获取指定名字的bean的class类型
     *
     * @param name
     * @return
     */
    Class getType(String name);

    /**
     * 获取指定名字的bean的所有别名
     *
     * @param name
     * @return
     */
    String[] getAliases(String name);
}
