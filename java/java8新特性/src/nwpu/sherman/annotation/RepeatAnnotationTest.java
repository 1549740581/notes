package nwpu.sherman.annotation;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 演示可重复注解
 *
 * @author sherman
 */
public class RepeatAnnotationTest {
    @Test
    @MyAnnotation("tl")
    @MyAnnotation("wqy")
    public void repeatAnnotationTest() throws Exception {
        /**
         * 通过反射获取重复注解的值
         */
        Method method = RepeatAnnotationTest.class.getMethod("repeatAnnotationTest");
        MyAnnotation[] annotations = method.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation annotation : annotations) {
            System.out.println(annotation.value());
        }

        // 类型注解
        /* @NonNull String name = person.getName(); */
        /* List<@NonNull Car> cars = new ArrayList<>(); */
    }
}
