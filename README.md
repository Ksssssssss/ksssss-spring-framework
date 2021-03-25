## ksssss-spring-framework
#### 参照spring实现的web框架

#### ioc
(1) 初始化容器
(2) 扫描所有注解并注册到容器中
(3) 实例化容器对象
 1. 注入对象
 2. 声明对象被ioc容器管理
 3. 循环依赖
 4. 注入指定bean
#### aop
 1. @Aspect
 2. @Pointcut
 3. @Before
 4. @After
 5. @Order
#### 异常处理
#### 拦截器
#### 配置文件
#### 代码质量

##### NOTE:
Spring refresh()流程
 0. 设置容器启动
 1. 进入refreshBeanFactory()方法
    1. 进入判断容器是否初始化，如果没初始化创建基本的DefaultListableBeanFactory容器
    2. 调用loadBeanDefinitions(DefaultListableBeanFactory beanFactory)(将配置文件解析成BeanDefinition对象,并注入到BeanFactory中)
    3. 创建BeanDefinitionReader,并调用loadBeanDefinitions(String... locations)(通过模板方法getResource来获取Resource集合)
    4. 调用BeanDefinitionReader的loadBeanDefinitions(Resource resource)(将Resource解析成文档对象，这些对象并没有按照Spring的Bean规则进行解析);
    5. 调用registerBeanDefinitions(Document doc,Resource resource),通过BeanDefinitionDocumentReader的BeanDefinitionParserDelegate
        将文档对象解析成BeanDefinitionHolder,BeanDefinitionHolder包含BeanDefinition和Bean别名，Bean的名字等。
        并通过注入到registerBeanDefinition(String name, BeanDefinition beanDefinition)注入到DefaultListableBeanFactory容器中
