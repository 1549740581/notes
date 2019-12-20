## 注册BeanPostProcessors

refresh()中方法包括：

> 1. prepareRefresh(); 
> 2. ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
> 3. prepareBeanFactory();
> 4. postProcessorBeanFactory();
> 5. invokeBeanFactoryPostProcessors(beanFactory);
> 6. **registerBeanPostProcessor(beanFactory);**
> 7. initMessageSource();
> 8. initApplicationEventMulticaster();
> 9. onRefresh();
> 10. registerListeners();
> 11. finishBeanFactoryInitialization(beanFactory);
> 12. finishRefresh();

### 1.1 registerBeanPostProcessor(beanFactory);
注册BeanPostProcessor（Bean后处理器），用于拦截Bean的创建过程。BeanPostProcessor接口有很多
子接口，不同子接口在Bean创建前后的执行时机是不同的：
- BeanPostProcessor
- DestructionAwareBeanPostProcessor
- InstantiationAwareBeanPostProcessor
- SmartInstantiationAwareBeanPostProcessor
- MergedBeanDefinitionPostProcessor【由internalPostProcessor管理】

整个注册BeanPostProcessor的大致流程：
- 根据BeanPostProcessor.class类型拿到所有的postProcessorNames
- 维护了四个List，管理不同接口的BeanPostProcessor：实现PriorityOrdered、Ordered、普通的和MergedBeanDefinitionPostProcessor
- 按照实现接口不同，注册顺序也不同：优先注册实现PriorityOrdered接口，在注册实现Ordered接口的，最后注册未实现任何接口的
- 最后注册internal BeanPostProcessors，即MergedBeanDefinitionPostProcessor接口类型，由internalPostProcessor管理
- 最终还要注册一个ApplicationListenerDetector，在Bean创建后检查是否ApplicationListener，如果是，将其加入到IOC容器中：
this.applicationContext.addApplicationListener((ApplicationListener<?>) bean);

具体源码：
```java
public static void registerBeanPostProcessors(
        ConfigurableListableBeanFactory beanFactory, AbstractApplicationContext applicationContext) {

    // 根据BeanPostProcessor.class类型拿到所有的postProcessorNames
    String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);

    // Register BeanPostProcessorChecker that logs an info message when
    // a bean is created during BeanPostProcessor instantiation, i.e. when
    // a bean is not eligible for getting processed by all BeanPostProcessors.
    int beanProcessorTargetCount = beanFactory.getBeanPostProcessorCount() + 1 + postProcessorNames.length;
    beanFactory.addBeanPostProcessor(new BeanPostProcessorChecker(beanFactory, beanProcessorTargetCount));

    // Separate between BeanPostProcessors that implement PriorityOrdered,
    // Ordered, and the rest.
    // 维护了四个List，管理不同接口的BeanPostProcessor
    
    // 实现PriorityOrdered接口的PostProcessor
    List<BeanPostProcessor> priorityOrderedPostProcessors = new ArrayList<BeanPostProcessor>();
    // 管理MergedBeanDefinitionPostProcessor
    List<BeanPostProcessor> internalPostProcessors = new ArrayList<BeanPostProcessor>();
    // 实现Ordered接口的PostProcessor
    List<String> orderedPostProcessorNames = new ArrayList<String>();
    // 未实现任何接口的PostProcessor
    List<String> nonOrderedPostProcessorNames = new ArrayList<String>();
    for (String ppName : postProcessorNames) {
        if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
            BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
            priorityOrderedPostProcessors.add(pp);
            if (pp instanceof MergedBeanDefinitionPostProcessor) {
                internalPostProcessors.add(pp);
            }
        }
        else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
            orderedPostProcessorNames.add(ppName);
        }
        else {
            nonOrderedPostProcessorNames.add(ppName);
        }
    }

    // 优先注册PriorityOrdered接口的Bean后处理器
    // First, register the BeanPostProcessors that implement PriorityOrdered.
    sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
    registerBeanPostProcessors(beanFactory, priorityOrderedPostProcessors);

    // 其次注册Ordered接口的Bean后处理器
    // Next, register the BeanPostProcessors that implement Ordered.
    List<BeanPostProcessor> orderedPostProcessors = new ArrayList<BeanPostProcessor>();
    for (String ppName : orderedPostProcessorNames) {
        BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
        orderedPostProcessors.add(pp);
        if (pp instanceof MergedBeanDefinitionPostProcessor) {
            internalPostProcessors.add(pp);
        }
    }
    sortPostProcessors(orderedPostProcessors, beanFactory);
    registerBeanPostProcessors(beanFactory, orderedPostProcessors);

    // 最后注册普通的Bean后处理器
    // Now, register all regular BeanPostProcessors.
    List<BeanPostProcessor> nonOrderedPostProcessors = new ArrayList<BeanPostProcessor>();
    for (String ppName : nonOrderedPostProcessorNames) {
        BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
        nonOrderedPostProcessors.add(pp);
        if (pp instanceof MergedBeanDefinitionPostProcessor) {
            internalPostProcessors.add(pp);
        }
    }
    registerBeanPostProcessors(beanFactory, nonOrderedPostProcessors);

    // 最后还要注册所有的internal Bean后处理器，即MergedBeanDefinitionPostProcessor
    // Finally, re-register all internal BeanPostProcessors.
    sortPostProcessors(internalPostProcessors, beanFactory);
    registerBeanPostProcessors(beanFactory, internalPostProcessors);

    // 想要向beanFactory中添加一个ApplicationListenerDetector
    // Re-register post-processor for detecting inner beans as ApplicationListeners,
    // moving it to the end of the processor chain (for picking up proxies etc).
    beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(applicationContext));
}
```

