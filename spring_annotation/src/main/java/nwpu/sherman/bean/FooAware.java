package nwpu.sherman.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

/**
 * 演示Aware接口
 *
 * @author sherman
 */
public class FooAware implements ApplicationContextAware, EmbeddedValueResolverAware {

    /**
     * 该方法的入参applicationContext即为Spring容器底层的组件，能够直接拿到
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext);
    }

    /**
     * 该方法的入参stringValueResolver即为Spring容器底层的组件，能够直接拿到
     */
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println(resolver.resolveStringValue("hello: ${os.name}" + ", I am #{2571 * 2}"));
    }
}
