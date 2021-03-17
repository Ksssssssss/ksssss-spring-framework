package github.ksssss.beans;

import com.sun.istack.internal.NotNull;

/**
 * @author ksssss
 * @since 2021/3/17 23:43
 */
public interface FactoryBean {
    /**
     * 通过工厂返回一个实例
     *
     * @return
     */
    @NotNull
    Object getObject();

    /**
     *
     * @return the type of object that this FactoryBean creates
     */
    Class getObjectType();

    /**
     *
     * @return if this bean is a singleton
     */
    boolean isSingleton();
}
