package ref;

/**
 * 演示强引用
 *
 * @author sherman
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = obj1;
        obj1 = null;
        System.gc();
        /**
         * obj2属于强引用，不会回收
         */
        System.out.println(obj2);
    }
}
