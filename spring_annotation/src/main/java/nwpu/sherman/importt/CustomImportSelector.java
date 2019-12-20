package nwpu.sherman.importt;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 使用ImportSelector批量导入Green & Blue Bean对象
 *
 * @author sherman
 */
public class CustomImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"nwpu.sherman.bean.Green", "nwpu.sherman.bean.Blue"};
    }
}
