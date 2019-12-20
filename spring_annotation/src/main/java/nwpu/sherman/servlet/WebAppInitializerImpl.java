package nwpu.sherman.servlet;

import nwpu.sherman.config.AppConfig;
import nwpu.sherman.config.RootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 继承AbstractAnnotationConfigDispatcherServletInitializer类，重写对应方法
 * 传递Spring配置类和Web配置类信息以及映射规则
 *
 * @author sherman
 */
public class WebAppInitializerImpl extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 获取根容器配置类，指定Spring的配置类
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    /**
     * 获取web容器的配置类，指定SpringMVC的配置类
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    /**
     * 获取DispatcherServlet的映射信息，注意不要写成/*
     * 因为它要拦截所有请求，但是不包括：*.jsp
     * /*：会把*.jsp也拦截了，导致jsp页面无法查看
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
