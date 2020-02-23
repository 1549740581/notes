## 初始化MessageSource

refresh()中方法包括：

> 1. prepareRefresh(); 
> 2. ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
> 3. prepareBeanFactory();
> 4. postProcessorBeanFactory();
> 5. invokeBeanFactoryPostProcessors(beanFactory);
> 6. registerBeanPostProcessor(beanFactory);
> 7. **initMessageSource();**
> 8. initApplicationEventMulticaster();
> 9. onRefresh();
> 10. registerListeners();
> 11. finishBeanFactoryInitialization(beanFactory);
> 12. finishRefresh();

### 1.1 initMessageSource();
initMessageSource();用于初始化MessageSource组件，做国际化功能、消息绑定、消息解析等等。
主要流程：
- 获取BeanFactory
- 看容器中是否有id为messageSource、类型为MessageSource的组件
- 如果有就赋值给this.messageSource，如果没有就自己创建一个DelegatingMessageSource，并将其注入到IOC容器中
- 以后获取国际化配置文件值的时候，就可以自动注入MessageSource

具体源码：
```java
/**
 * Initialize the MessageSource.
 * Use parent's if none defined in this context.
 */
protected void initMessageSource() {
    // 拿到beanFactory
    ConfigurableListableBeanFactory beanFactory = getBeanFactory();
    // 如果容器中有id为messageSource的组件
    if (beanFactory.containsLocalBean(MESSAGE_SOURCE_BEAN_NAME)) {
        // 直接拿到
        this.messageSource = beanFactory.getBean(MESSAGE_SOURCE_BEAN_NAME, MessageSource.class);
        // Make MessageSource aware of parent MessageSource.
        if (this.parent != null && this.messageSource instanceof HierarchicalMessageSource) {
            HierarchicalMessageSource hms = (HierarchicalMessageSource) this.messageSource;
            if (hms.getParentMessageSource() == null) {
                // Only set parent context as parent MessageSource if no parent MessageSource
                // registered already.
                hms.setParentMessageSource(getInternalParentMessageSource());
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Using MessageSource [" + this.messageSource + "]");
        }
    }
    else {
        // 否则new DelegatingMessageSource
        // Use empty MessageSource to be able to accept getMessage calls.
        DelegatingMessageSource dms = new DelegatingMessageSource();
        dms.setParentMessageSource(getInternalParentMessageSource());
        this.messageSource = dms;
        // 并将其注入到IOC容器中
        beanFactory.registerSingleton(MESSAGE_SOURCE_BEAN_NAME, this.messageSource);
        if (logger.isDebugEnabled()) {
            logger.debug("Unable to locate MessageSource with name '" + MESSAGE_SOURCE_BEAN_NAME +
                    "': using default [" + this.messageSource + "]");
        }
    }
}
```
