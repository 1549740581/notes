package nwpu.sherman.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 该Bean对象用于演示Bean的生命周期流程
 *
 * @author sherman
 */
public class LifecycleBean implements BeanNameAware , BeanFactoryAware, InitializingBean , DisposableBean {
    private String foo;

    public LifecycleBean() {
         System.out.println("【1】---Constructor构造Bean对象...");
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        System.out.println("【2】---setter()注入属性值...");
        this.foo = foo;
    }

    @Override
    public String toString() {
        return "LifecycleBean{" +
                "foo='" + foo + '\'' +
                '}';
    }

    /**
     * BeanNameAware接口的setBeanName()
     */
    @Override
    public void setBeanName(String s) {
        System.out.println("【3】---BeanNameAware#setBeanName()...");
    }

    /**
     * BeanFactoryAware接口的setBeanFactory()
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("【4】---BeanFactoryAware#setBeanFactory()...");
    }

    /**
     * jsr250注解
     */
    @PostConstruct
    public void jsr250PostConstruct() {
        System.out.println("【6】---JSR250#PostConstruct()");
    }

    /**
     * jsr250注解
     */
    @PreDestroy
    public void jsr250PreDestroy(){
        System.out.println("【10】---JSR250#PreDestroy()");

    }

    /**
     * InitializingBean接口的afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【7】---InitializingBean#afterPropertiesSet()");
    }

    /**
     * @Bean 注解中指定的init-method方法
     */
    public void initMethod() {
        System.out.println("【8】---@Bean#init-method()");
    }

    /**
     * @Bean 注解中指定的destroy-method方法
     */
    public void destroyMethod() {
        System.out.println("【12】---@Bean#destroy-method");
    }

    /**
     * DisposableBean接口的destroy()方法
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("【11】---DisposableBean#destroy()");
    }
}
