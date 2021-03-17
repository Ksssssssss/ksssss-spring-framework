package github.ksssss.beans.factory;

import com.sun.deploy.util.StringUtils;
import github.ksssss.beans.BeanFactory;
import github.ksssss.beans.FactoryBean;
import github.ksssss.beans.config.BeanDefinition;
import github.ksssss.beans.config.BeanPostProcessor;
import github.ksssss.utils.Assert;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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

    private final Set<Class> ignoreDependencyTypes = new HashSet<>();

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    /**
     * Map from alias to canonical bean name
     */
    private final Map<String, String> aliasMap = new ConcurrentHashMap<>();

    /**
     * Cache of singletons: bean name --> bean instance
     */
    private final Map<String, Object> singletonCache = new ConcurrentHashMap<>();

    public AbstractBeanFactory() {
        ignoreDependencyType(BeanFactory.class);
    }

    public AbstractBeanFactory(BeanFactory parentBeanFactory) {
        this();
        this.parentBeanFactory = parentBeanFactory;
    }

    @Override
    public BeanFactory getParentBeanFactory() {
        return null;
    }

    @Override
    public Object getBean(String name) {
        String beanName = transformedBeanName(name);
        Object singletonInstance = this.singletonCache.get(beanName);
        if (singletonInstance != null) {
            return getObjectForSharedInstance(name, singletonInstance);
        } else {
            BeanDefinition beanDefinition = null;
        }
        return null;
    }

    protected Object getObjectForSharedInstance(String name, Object beanInstance) {
        if (name.startsWith(FACTORY_BEAN_PREFIX) && beanInstance instanceof FactoryBean) {
            FactoryBean factoryBean = (FactoryBean) beanInstance;
            beanInstance = factoryBean.getObject();
        }
        return beanInstance;
    }

    public String transformedBeanName(String name) {
        Assert.notNull(name, () -> "不能使用null的name得到bean");
        if (name.startsWith(FACTORY_BEAN_PREFIX)) {
            name = name.substring(FACTORY_BEAN_PREFIX.length());
        }
        String canonicalName = this.aliasMap.get(name);
        return canonicalName == null ? name : canonicalName;
    }

    @Override
    public <T> T getBean(String name, Class<T> requireType) {
        return null;
    }

    @Override
    public Object getBean(String name, Object... obj) {
        return null;
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }

    @Override
    public boolean isSingleton(String name) {
        return false;
    }

    @Override
    public boolean isPrototype(String name) {
        return false;
    }

    @Override
    public boolean isTypeMatch(String name, Class targetType) {
        return false;
    }

    @Override
    public Class getType(String name) {
        return null;
    }

    public void ignoreDependencyType(Class type) {
        this.ignoreDependencyTypes.add(type);
    }

    public Set<Class> getIgnoreDependencyTypes() {
        return ignoreDependencyTypes;
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }

    @Override
    public void registerAlias(String beanName, String alias) {
        synchronized (this.aliasMap) {
            String registeredName = this.aliasMap.get(alias);
            if (registeredName != null) {
//                throw new Exception("Alias");
            }
            this.aliasMap.put(alias, beanName);
        }
    }

    @Override
    public void setParentBeanFactory(BeanFactory parentBeanFactory) {

    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return null;
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        synchronized (this.singletonCache) {
            Object singleton = this.singletonCache.get(beanName);
            Assert.notNull(singleton, () -> "Could not register object singletonObject");
            addSingleton(beanName, singletonObject);
        }
    }

    public void addSingleton(String beanName, Object singletonObject) {
        this.singletonCache.put(beanName, singletonObject);
    }

    @Override
    public void destroySingletons() {

    }

    @Override
    public String[] getAliases(String name) {
        return new String[0];
    }
}
