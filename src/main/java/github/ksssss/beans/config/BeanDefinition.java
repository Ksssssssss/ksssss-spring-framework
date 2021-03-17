package github.ksssss.beans.config;

import github.ksssss.beans.support.ConstructorArgumentValues;
import github.ksssss.beans.support.MutablePropertyValues;

/**
 * @author ksssss
 * @since 2021/3/15 17:59
 */
public interface BeanDefinition {
    MutablePropertyValues getPropertyValues();

    ConstructorArgumentValues getConstructorArgumentValues();

}
