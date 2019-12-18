package ref;

import java.lang.ref.WeakReference;

/**
 * 演示弱引用
 *
 * @author sherman
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();
        WeakReference<Object> wr = new WeakReference<>(obj1);
        System.out.println(obj1);
        System.out.println(wr.get());

        obj1 = null;

        /**
         * 弱引用活不到下一次gc，因此即使内存充足，弱引用也会被回收
         */
        System.gc();
        System.out.println(obj1);
        System.out.println(wr.get());
    }
}
