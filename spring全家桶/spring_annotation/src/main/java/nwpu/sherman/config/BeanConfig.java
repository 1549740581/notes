package nwpu.sherman.config;

import nwpu.sherman.bean.*;
import nwpu.sherman.condition.LinuxCondition;
import nwpu.sherman.condition.WindowsCondition;
import nwpu.sherman.dao.PersonDao;
import nwpu.sherman.importt.CustomImportBdRegistrar;
import nwpu.sherman.importt.CustomImportSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

/**
 * Bean的配置类，将@Bean注解的类放入到IOC容器中管理
 *
 * @ComponentScan：
 *      * 可重复注解：
 *      * 可以通过excludeFilters & includeFilters进行包含或者排除
 *      * 当使用includeFilters进行包含时，必须使用【useDefaultFilters=false】
 *        因为默认是包含所有，必须禁用掉，才能正确包含
 *
 *      * @Component可重复注解的，多个@Component之间是相互补全的关系，
 *        因此如果要禁用包含所有的默认情况，在所有的@Component中都要禁用
 *
 *      * @ComponentScan可以自定义规则【ComponentScan.Filter】：
 *          自定义的过滤规则类需要实现TypeFilter接口，重写match方法来自定义规则
 *
 * @author sherman
 */
/*
@ComponentScan(value = "nwpu.sherman", includeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {
                CustomTypeFilter.class
        })
}, useDefaultFilters = false)
*/

@ComponentScan("nwpu.sherman")
@Configuration
@Import({Red.class, CustomImportSelector.class, CustomImportBdRegistrar.class})
@PropertySource("classpath:/programmer_property.properties")
public class BeanConfig {

    /**
     * 向IOC容器中注入一个Person类Bean
     * 该Bean对象的作用域为：prototype
     * @return
     */
    @Scope("prototype")
    @Bean("person")
    public Person getPerson() {
        return new Person("sherman", 23);
    }


    /**
     * 演示@Conditional类使用：
     * 创建Bill Gates Bean对象，如果操作系统为Windows，则输出该Bean对象
     */
    @Conditional({WindowsCondition.class})
    @Bean("Bill Gates")
    public Person billPerson() {
        return new Person("Bill Gates", 60);
    }
    /**
     * 演示@Conditional类使用：
     * 创建Linus Torvalds Bean对象，如果操作系统为Linux，则输出该Bean对象
     */
    @Conditional({LinuxCondition.class})
    @Bean("Linus Torvalds")
    public Person linuxPerson() {
        return new Person("Linus Torvalds", 52);
    }

    /**
     * 给IOC容器中注册一个PersonFactoryBean
     */
    @Bean("personFactoryBean")
    public PersonBeanFactory getPersonBeanFactory() {
        return new PersonBeanFactory();
    }

    /**
     * 将LifecircleBean注入到IOC容器中
     */
    @Bean(value = "lifecycleBean", initMethod = "initMethod", destroyMethod = "destroyMethod")
    @Scope(scopeName = "singleton")
    public LifecycleBean getLifecycleBean() {
        LifecycleBean lifecircleBean = new LifecycleBean();
        lifecircleBean.setFoo("foo");
        return lifecircleBean;
    }

    /**
     * 将Programmer 注入到IOC容器中
     * 该Bean对象的属性通过不同属性赋值方式进行设置
     */

    @Bean("programmer")
    public Programmer getProgrammer() {
        return new Programmer();
    }

    /**
     * 创建两个类型为Autowired的Bean，测试@Qualifier 和 @Primary注解不同
     */
    // @Primary
    @Bean("personDao-copy")
    public PersonDao getPersonDao() {
        return new PersonDao(100);
    }

    /**
     * 把Red Bean对象也注入进来，比较下面Rainbow对象getRed获得的Red对象
     * 和直接从容器中拿到的Red对象是否是同一个
     */
    @Bean("red")
    public Red getRed() {
        return new Red();
    }

    /**
     * 演示@Autowired注释在构造器、方法和参数上
     */
    @Bean("rainbow")
    //@Autowired
    public Rainbow getRainbow(@Autowired Red red){
        return new Rainbow(red);
    }

    /**
     * 将FooAware组件注入到IOC容器中
     */
    @Bean("fooAware")
    public FooAware getFooAware() {
        return new FooAware();
    }
}
