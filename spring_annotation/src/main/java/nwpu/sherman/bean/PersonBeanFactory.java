package nwpu.sherman.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 自定义Bean工厂类，实现FactoryBean接口，给IOC容器注入组件
 *
 * @author sherman
 */
public class PersonBeanFactory implements FactoryBean<Person> {
    @Override
    public Person getObject() throws Exception {
        return new Person("gantlei", 23);
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
