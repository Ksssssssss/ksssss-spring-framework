package github.ksssss.beans.factory;

import github.ksssss.beans.BeanFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ksssss
 * @since 2021/3/16 23:08
 */
@Slf4j
public abstract class AbstractBeanFactory implements ConfigurableBeanFactory {

    /**
     * 用&来区分FactoryBean和bean
     */
    public static final String FACTORY_BEAN_PREFIX = "&";

    private BeanFactory parentBeanFactory;

}
