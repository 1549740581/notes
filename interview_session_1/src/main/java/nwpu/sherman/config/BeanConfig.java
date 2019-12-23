package nwpu.sherman.config;

import nwpu.sherman.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 默认情况下，Spring只为每个在IOC容器中声明的Bean创建唯一一个实例，整个IOC容器
 * 返回内都能共享该实例，所有后续的getBean方法都只会返回唯一的Bean实例。
 *
 * Bean的作用域：
 * - singleton：单例的，IOC容器中仅存在一个Bean实例
 * - prototype：容器初始化时候并不会初始化该Bean实例，每次getBean时候都会创建一个新的Bean实例
 * - request：仅适用WebApplicationContext环境，每个http请求都会创建一个新的Bean
 * - session：仅适用WebApplicationContext环境，每个session会话会创建一个新的Bean
 *
 * @author sherman
 */
@Configuration
@ComponentScan("nwpu.sherman.bean")
public class BeanConfig {
    @Bean("person")
    @Scope("prototype")
    Person person() {
        Person person = new Person();
        person.setId(1);
        person.setName("sherman");
        return person;
    }
}
