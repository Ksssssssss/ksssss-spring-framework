package github.ksssss.beans.config;

import github.ksssss.beans.BeanFactory;

/**
 * @author ksssss
 * @since 2021/3/18 23:09
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    int AUTOWIRE_BY_NAME = 1;
    int AUTOWIRE_BY_TYPE = 2;
    int AUTOWIRE_CONSTRUCTOR = 3;
    int AUTOWIRE_AUTODETECT = 4;
}
