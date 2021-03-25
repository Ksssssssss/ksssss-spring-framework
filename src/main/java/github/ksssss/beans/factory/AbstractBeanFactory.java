package github.ksssss.beans.factory;

import com.sun.deploy.util.StringUtils;
import github.ksssss.beans.BeanFactory;
import github.ksssss.beans.FactoryBean;
import github.ksssss.beans.config.BeanDefinition;
import github.ksssss.beans.config.BeanPostProcessor;
import github.ksssss.beans.support.RootBeanDefinition;
import github.ksssss.utils.Assert;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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
        Object sharedInstance = this.singletonCache.get(beanName);
        if (sharedInstance != null) {
            return getObjectForSharedInstance(name, sharedInstance);
        } else {
            RootBeanDefinition mergedBeanDefinition = null;
            try {
                mergedBeanDefinition = getMergedBeanDefinition(beanName);
            } catch (NoSuchElementException e) {
                if (this.parentBeanFactory != null) {
                    return this.parentBeanFactory.getBean(beanName);
                }
                throw e;
            }
            if (mergedBeanDefinition.isSingleton()) {
                synchronized (this.singletonCache) {
                    sharedInstance = this.singletonCache.get(beanName);
                    if (sharedInstance == null) {
                        sharedInstance = createBean(beanName, mergedBeanDefinition);
                        addSingleton(beanName, sharedInstance);
                    }
                }
                return getObjectForSharedInstance(beanName, sharedInstance);
            } else {
                return createBean(beanName, mergedBeanDefinition);
            }
        }
    }

    /**
     * Get the object for the given shared bean, either the bean
     * instance itself or its created object in case of a FactoryBean.
     *
     * @param name
     * @param beanInstance
     * @return
     */
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

    public RootBeanDefinition getMergedBeanDefinition(String beanName) throws NoSuchElementException {
        try {
            return getMergedBeanDefinition(beanName, getBeanDefinition(beanName));
        } catch (NoSuchElementException e) {
            throw e;
        }
    }

    public RootBeanDefinition getMergedBeanDefinition(String beanName, BeanDefinition bd) {
        if (bd instanceof RootBeanDefinition) {
            return ((RootBeanDefinition) bd);
        } else {
            throw new IllegalArgumentException();
        }
    }


    @Override
    public Object getBean(String name, Class requireType) {
        Object bean = getBean(name);
        if (!requireType.equals(bean.getClass())) {
            throw new IllegalArgumentException();
        }
        return bean;
    }


    @Override
    public boolean containsBean(String name) {
        String beanName = transformedBeanName(name);
        if (this.singletonCache.containsKey(beanName) || this.containsBeanDefinition(beanName)) {
            return true;
        }
        if (this.parentBeanFactory != null && this.parentBeanFactory.containsBean(name)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isSingleton(String name) {
        String beanName = transformedBeanName(name);
        try {
            boolean singleton = true;
            Object beanInstance = this.singletonCache.get(beanName);
            if (beanInstance == null) {
                RootBeanDefinition bd = getMergedBeanDefinition(beanName);
                singleton = bd.isSingleton();
            }
            // in case of FactoryBean, return singleton status of created object if not a dereference
            else if (beanInstance instanceof FactoryBean) {
                FactoryBean factoryBean = (FactoryBean) getBean(FACTORY_BEAN_PREFIX + beanName);
                return factoryBean.isSingleton();
            }
            return singleton;
        } catch (NoSuchElementException e) {
            if (this.parentBeanFactory != null) {
                return this.parentBeanFactory.isSingleton(beanName);
            }
            throw e;
        }
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
        this.parentBeanFactory = parentBeanFactory;
    }

    public abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, RootBeanDefinition mergedBeanDefinition)
            throws RuntimeException;

    public abstract boolean containsBeanDefinition(String beanName);

    public abstract void destroyBean(String beanName);

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
        synchronized (this.singletonCache) {
            this.singletonCache.keySet().forEach(beanName -> destroyBean(beanName));
        }
    }

    @Override
    public String[] getAliases(String name) {
        String beanName = transformedBeanName(name);
        if (!this.singletonCache.containsKey(beanName) || !containsBeanDefinition(beanName)) {
            if (this.parentBeanFactory != null) {
                return this.parentBeanFactory.getAliases(name);
            }
            throw new IllegalArgumentException();
        }
        return this.aliasMap.entrySet()
                .stream()
                .filter(aliasEntry -> beanName.equals(aliasEntry.getValue()))
                .map(aliasEntry -> aliasEntry.getKey())
                .toArray(String[]::new);
    }
}
