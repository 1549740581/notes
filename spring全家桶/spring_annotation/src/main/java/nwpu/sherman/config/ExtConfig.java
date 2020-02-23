package nwpu.sherman.config;

import nwpu.sherman.bean.Blue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author sherman
 */

@Configuration
@ComponentScan("nwpu.sherman.ext")
public class ExtConfig {


    /**
     * 向IOC容器中注入该Bean对象只是为了比较Bean创建时机和BeanFactoryPostProcessor的执行时机
     * @return
     */
    @Bean
    public Blue blue() {
        return new Blue();
    }
}
