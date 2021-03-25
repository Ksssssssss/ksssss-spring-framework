package github.ksssss.beans.support;

import github.ksssss.beans.config.AutowireCapableBeanFactory;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ksssss
 * @since 2021/3/18 23:05
 */
@Setter
@Getter
public class RootBeanDefinition extends AbstractBeanDefinition {

    public static final int AUTOWIRE_NO = 0;

    public static final int AUTOWIRE_BY_NAME = AutowireCapableBeanFactory.AUTOWIRE_BY_NAME;

    public static final int AUTOWIRE_BY_TYPE = AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE;

    public static final int AUTOWIRE_CONSTRUCTOR = AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR;

    public static final int AUTOWIRE_AUTODETECT = AutowireCapableBeanFactory.AUTOWIRE_AUTODETECT;

    public static final int DEPENDENCY_CHECK_NONE = 0;

    public static final int DEPENDENCY_CHECK_OBJECTS = 1;

    public static final int DEPENDENCY_CHECK_SIMPLE = 2;

    public static final int DEPENDENCY_CHECK_ALL = 3;

    private Object beanClass;

    private ConstructorArgumentValues constructorArgumentValues;

    private int autowireMode = AUTOWIRE_NO;

    private int dependencyCheck = DEPENDENCY_CHECK_NONE;

    private String[] dependsOn;

    private String initMethodName;

    private String destroyMethodName;

    public RootBeanDefinition(Object beanClass, int autowireMode) {
        super(null);
        this.beanClass = beanClass;
        this.autowireMode = autowireMode;
    }

    public RootBeanDefinition(MutablePropertyValues pvs, Object beanClass) {
        super(pvs);
        this.beanClass = beanClass;
    }

    public RootBeanDefinition(MutablePropertyValues pvs, Object beanClass, boolean singleton) {
        super(pvs);
        this.beanClass = beanClass;
        setSingleton(singleton);
    }

    public String[] getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(String[] dependsOn) {
        this.dependsOn = dependsOn;
    }
}
