package nwpu.sherman.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义一个BeanFactoryPostProcessor接口实现类，查看它的创建时机
 *
 * @author sherman
 */
@Component
public class FooBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("FooBeanFactoryPostProcessor#postProcessBeanFactory()......");
        System.out.println("BeanDefinitionCount: " + beanFactory.getBeanDefinitionCount());
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("================end================");
    }
}
