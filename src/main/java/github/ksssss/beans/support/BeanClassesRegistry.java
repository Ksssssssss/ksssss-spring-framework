package github.ksssss.beans.support;

import github.ksssss.exception.BeansException;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Bean注册器
 *
 * @author ksssss
 * @since 2021/3/25 23:01
 */
public interface BeanClassesRegistry {
    void registerBeanClasses(Class<? extends Annotation> classType, Set<Class<?>> beanClasses) throws BeansException;
}
