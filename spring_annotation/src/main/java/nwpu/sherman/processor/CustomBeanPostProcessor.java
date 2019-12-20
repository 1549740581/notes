package nwpu.sherman.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 演示BeanPostProcessor在Bean的创建前后实现拦截功能
 *
 * 拦截到beanName为"lifecycleBean"并进行相应输出
 * @author sherman
 */
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    private final String targetBeanName = "lifecycleBean";
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (targetBeanName.equals(beanName)) {
            System.out.println("【5】---BeanPostProcessor#postProcessBeforeInitialization()");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (targetBeanName.equals(beanName)) {
            System.out.println("【9】---BeanPostProcessor#postProcessAfterInitialization()");
        }
        return bean;
    }
}
