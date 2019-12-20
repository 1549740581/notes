package nwpu.sherman.filter;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * 自定义包扫描时的过滤规则：
 * 如果beanName中包含"Dao"，就将该Bean对象加入到扫描范围内
 *
 * @author sherman
 */
public class CustomTypeFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        final String keywords = "Dao";
        // 获取当前类的注解原信息
        // AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata()

        // 获取当前扫描类的资源信息（类路径等）
        // Resource resource = metadataReader.getResource()

        // 获取扫描类的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        return classMetadata.getClassName().contains(keywords);
    }
}
