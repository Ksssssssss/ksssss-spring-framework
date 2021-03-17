package github.ksssss.beans.factory;

import com.sun.istack.internal.Nullable;
import github.ksssss.beans.BeanFactory;

/**
 * @author ksssss
 * @since 2021/3/9 23:51
 */
public interface HierarchicalBeanFactory extends BeanFactory {

    /**
     * 获取双亲容器
     * @return
     */
    @Nullable
    BeanFactory getParentBeanFactory();
}
