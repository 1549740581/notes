package nwpu.sherman.annotation;

import java.lang.annotation.*;

/**
 * 演示可重复注解
 *
 * @author sherman
 */
@Repeatable(MyAnnotations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE_PARAMETER})
public @interface MyAnnotation {
    String value() default "sherman";
}
