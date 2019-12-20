package nwpu.sherman.importt;

import nwpu.sherman.bean.AllColor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 如果存在Red、Green、Blue，就给给当前IOC容器动态注入一个AllColor Bean对象
 *
 * @author sherman
 */
public class CustomImportBdRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        boolean hasRed = registry.containsBeanDefinition("nwpu.sherman.bean.Red");
        boolean hasGreen = registry.containsBeanDefinition("nwpu.sherman.bean.Green");
        boolean hasBlue = registry.containsBeanDefinition("nwpu.sherman.bean.Blue");
        if (hasRed && hasGreen && hasBlue) {
            RootBeanDefinition allColorBD = new RootBeanDefinition(AllColor.class);
            registry.registerBeanDefinition("allColor", allColorBD);
        }
    }
}
