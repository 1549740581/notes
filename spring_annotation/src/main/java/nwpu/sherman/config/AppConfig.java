package nwpu.sherman.config;

import nwpu.sherman.intercept.CustomIntercept;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

/**
 * SpringMvc的配置文件
 * 只扫描Controller，形成互补配置
 *
 * 注意：
 * 1、因为是只扫描Controller，因此需要使用useDefaultFilters禁用默认规则
 * 2、这个类不加@Configuration注解也能正常工作，但是WebAppInitController中 success()会提示找不到View Resolver
 *    且对应方法旁边没有"Related Views"的图标，加上之后就能正常显示
 *
 * @author sherman
 */
@EnableWebMvc
@ComponentScan(value = "nwpu.sherman", includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
}, useDefaultFilters = false)
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

    /**
     * 注册ViewResolver
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // 默认前缀"/WEB-INF/", 后缀".jsp"
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    /**
     * 静态资源访问
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 定制拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 将自定义的拦截器加入进来，拦截任意级路径
         * 也可以将拦截器注入进来，这里直接new
         */
        registry.addInterceptor(new CustomIntercept()).addPathPatterns("/**");
    }
}
