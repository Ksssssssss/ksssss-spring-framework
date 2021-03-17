package github.ksssss.beans.support;

import github.ksssss.beans.config.BeanDefinition;

/**
 * Common base class for bean definitions
 *
 * @author ksssss
 * @since 2021/3/18 0:04
 */
public abstract class AbstractBeanDefinition implements BeanDefinition {

    private MutablePropertyValues propertyValues;

    private boolean singleton;

    private boolean lazyInit;

    protected AbstractBeanDefinition(MutablePropertyValues pvs) {
        this.propertyValues = (pvs != null ? pvs : new MutablePropertyValues());
    }

    @Override
    public MutablePropertyValues getPropertyValues() {
        return this.propertyValues;
    }

    @Override
    public ConstructorArgumentValues getConstructorArgumentValues() {
        return null;
    }

    public void validate() {
        if (this.lazyInit && !this.singleton) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }
}
