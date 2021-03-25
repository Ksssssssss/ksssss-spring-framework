package github.ksssss.beans.factory;

import github.ksssss.beans.BeanFactory;
import github.ksssss.beans.support.BeanRegistry;
import github.ksssss.exception.BeanRegisteredException;
import github.ksssss.exception.BeansException;
import github.ksssss.exception.NoSuchBeanException;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ksssss
 * @since 2021/3/25 22:58
 */
public class DefaultBeanFactory implements BeanFactory, BeanRegistry {
    private final Map<String, Object> singletonCache = new ConcurrentHashMap<>();

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
    public void registerBean(String beanName, Object bean) throws BeansException {
        if (containsBean(beanName)) {
            throw new BeanRegisteredException("beanName is exist");
        }
        addBean(beanName, bean);
    }

    public void addBean(String beanName, Object bean) {
        this.singletonCache.put(beanName, bean);
    }
}
