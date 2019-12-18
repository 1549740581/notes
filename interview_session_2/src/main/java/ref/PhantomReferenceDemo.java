package ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 演示PhantomReference
 *
 * @author sherman
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(obj, referenceQueue);
        System.out.println(obj);
        /**
         * PhantomReference任何时候get都是null
         */
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("===============");
        /**
         * obj=null，gc之后，引用的对象会被添加到引用队列中，
         * 因此最后的poll方法能够获取到值
         */
        obj = null;
        System.gc();
        Thread.sleep(100);
        System.out.println(obj);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}
