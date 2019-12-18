package ref;

import java.lang.ref.SoftReference;

/**
 * 演示软引用
 * VM: -Xms5m -Xmx5m -XX:+PrintGCDetails
 *
 * @author sherman
 */
public class SoftReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();
        SoftReference<Object> softReference = new SoftReference(obj1);
        System.out.println(obj1);
        System.out.println(softReference.get());

        obj1 = null;
        try {
            byte[] bytes = new byte[20 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(obj1);
            System.out.println(softReference.get());
        }

    }
}
