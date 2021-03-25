package github.ksssss.beans.factory;

import github.ksssss.beans.BeanFactory;

/**
 * @author ksssss
 * @since 2021/3/9 23:34
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    int AUTOWIRED_BY_NAME = 1;

    int AUTOWIRED_BY_TYPE = 2;

    int AUTOWIRED_CONSTRUCTOR = 3;


}
