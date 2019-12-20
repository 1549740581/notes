package nwpu.sherman.servlet;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

/**
 * 自定义ServletContainerInitializer接口的实现类
 * 容器启动的时候会将@HandlersTypes指定类型所有的子类（实现类、抽象类、子接口）传递过来
 *
 * @author sherman
 */

@HandlesTypes({FooInterface.class})
public class FooServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext sc) throws ServletException {
        System.out.println("感兴趣的类：");
        for (Class<?> clazz : set) {
            System.out.println(clazz);
        }

        // 注册Servlet组件
        ServletRegistration.Dynamic dynamicServlet = sc.addServlet("dynamicServlet", new DynamicServlet());
        // 配置映射信息
        dynamicServlet.addMapping("/dynamic");

        // 注册Listener
        sc.addListener(DynamicListener.class);

        // 注册Filter
        FilterRegistration.Dynamic dynamicFilter = sc.addFilter("dynamicFilter", DynamicFilter.class);
        dynamicFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
    }
}
