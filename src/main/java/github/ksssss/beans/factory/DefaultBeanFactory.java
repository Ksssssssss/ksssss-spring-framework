package github.ksssss.beans.factory;

import github.ksssss.beans.BeanFactory;
import github.ksssss.beans.support.BeanClassesRegistry;
import github.ksssss.exception.BeansException;
import github.ksssss.exception.NoSuchBeanException;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ksssss
 * @since 2021/3/25 22:58
 */
public class DefaultBeanFactory implements BeanFactory, BeanClassesRegistry {
    private final Map<String, Object> singletonCache = new ConcurrentHashMap<>();
    private final Map<Class<? extends Annotation>, Set<Class<?>>> annotationClasses = new ConcurrentHashMap<>();

    @Override
    public Object getBean(String name) {
        Object beanInstance = this.singletonCache.get(name);
        if (Objects.isNull(beanInstance)) {
            throw new NoSuchBeanException("beanName is " + name);
        }
        return beanInstance;
    }

    @Override
    public Object getBean(String name, Class requireType) {
        return null;
    }

    @Override
    public Object getBean(String name, Object... obj) {
        return null;
    }

    @Override
    public boolean containsBean(String name) {
        return this.singletonCache.containsKey(name);
    }

    @Override
    public boolean isSingleton(String name) {
        return true;
    }

    @Override
    public boolean isPrototype(String name) {
        return false;
    }

    @Override
    public boolean isTypeMatch(String name, Class targetType) {
        if (containsBean(name)) {
            Object beanInstance = this.singletonCache.get(name);
            return beanInstance.getClass().equals(targetType);
        }
        return false;
    }

    @Override
    public Class getType(String name) {
        return this.singletonCache.get(name).getClass();
    }

    @Override
    public String[] getAliases(String name) {
        return new String[0];
    }

    public void destrotBeans() {
        this.singletonCache.clear();
    }

    @Override
    public void registerBeanClasses(Class<? extends Annotation> classType, Set<Class<?>> beanClasses) throws BeansException {
        Set<Class<?>> classes = this.annotationClasses.getOrDefault(classType, new HashSet<>());
        classes.addAll(beanClasses);
        addBeanClasses(classType, beanClasses);
    }

    public void addBeanClasses(Class<? extends Annotation> classType, Set<Class<?>> beanClasses) {
        this.annotationClasses.put(classType, beanClasses);
    }
}
