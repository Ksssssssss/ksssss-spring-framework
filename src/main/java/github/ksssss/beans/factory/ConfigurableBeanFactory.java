package github.ksssss.beans.factory;

import github.ksssss.beans.BeanFactory;
import github.ksssss.beans.config.BeanDefinition;
import github.ksssss.beans.config.BeanPostProcessor;

/**
 * @author ksssss
 * @since 2021/3/9 23:52
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory {
    /**
     * 设置双亲容器
     *
     * @param parentBeanFactory
     */
    void setParentBeanFactory(BeanFactory parentBeanFactory);

    /**
     * 创建一个BeanPostProcessor，应用于该工厂创建的bean，在工厂配置的时候唤醒
     *
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 通过beanName拿到bean的属性
     *
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 给bean创建一个别名，工厂应该实现通过别名访问
     *
     * @param beanName
     * @param alias
     */
    void registerAlias(String beanName, String alias);

    /**
     * 注册一个单例的bean
     *
     * @param beanName
     * @param singletonObject
     */
    void registerSingleton(String beanName, Object singletonObject);

    /**
     * 销毁工厂中所有的单例bean
     */
    void destroySingletons();
}
