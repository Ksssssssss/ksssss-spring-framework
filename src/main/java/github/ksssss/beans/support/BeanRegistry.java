package github.ksssss.beans.support;

import github.ksssss.exception.BeansException;

/**
 * Bean注册器
 *
 * @author ksssss
 * @since 2021/3/25 23:01
 */
public interface BeanRegistry {
    void registerBean(String beanName, Object bean) throws BeansException;
}
