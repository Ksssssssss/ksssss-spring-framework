package github.ksssss.context;

import com.sun.xml.internal.ws.transport.http.ResourceLoader;
import github.ksssss.beans.BeanFactory;
import github.ksssss.beans.factory.AutowireCapableBeanFactory;
import github.ksssss.beans.factory.DefaultBeanFactory;
import github.ksssss.context.annotation.ComponentScan;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ksssss
 * @since 2021/3/9 23:37
 */
public class ApplicationContext {
    private final AtomicBoolean closed = new AtomicBoolean();
    private final AtomicBoolean active = new AtomicBoolean();
    private volatile DefaultBeanFactory beanFactory;
    private Class<?> primarySource;


    public ApplicationContext(Class<?> primarySource) {
        this.primarySource = primarySource;
    }

    public void run() {
        prepareRefresh();
        this.beanFactory = createBeanFactory();
    }

    private void prepareRefresh() {
        this.active.set(true);
        this.closed.set(false);
    }

    private DefaultBeanFactory createBeanFactory() {
        if (hasBeanFactory()) {
            destroyBeans();
            closeBeanFactory();
        }
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        loadBeans(beanFactory);
        return defaultBeanFactory;
    }

    private void loadBeans(DefaultBeanFactory beanFactory) {
        String[] packageNames = getPackageNames();
        loadClasses(packageNames);
    }

    private Set<Class<?>> loadClasses(String[] packageNames) {
        return null;
    }

    private String[] getPackageNames() {
        ComponentScan annotation = this.primarySource.getAnnotation(ComponentScan.class);
        return annotation != null ? annotation.value() :
                new String[]{this.primarySource.getPackage().getName()};
    }

    private void destroyBeans() {
        getBeanFactory().destrotBeans();
    }

    private void closeBeanFactory() {
        this.beanFactory = null;
    }

    private boolean hasBeanFactory() {
        return this.beanFactory != null;
    }

    public DefaultBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
