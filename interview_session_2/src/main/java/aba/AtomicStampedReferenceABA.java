package aba;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题解决方案，AtomicStampedReference
 *
 * @author sherman
 */
public class AtomicStampedReferenceABA {
    private static AtomicReference<Integer> ar = new AtomicReference<>(0);
    private static AtomicStampedReference<Integer> asr =
            new AtomicStampedReference<>(0, 1);

    public static void main(String[] args) {
        System.out.println("=============演示ABA问题（AtomicReference）===========");
        new Thread(() -> {
            ar.compareAndSet(0, 1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ar.compareAndSet(1, 0);
            System.out.println(Thread.currentThread().getName() + "进行了一次ABA操作");
        }, "子线程").start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean res = ar.compareAndSet(0, 100);
        if (res) {
            System.out.println("main成功修改, 未察觉到子线程进行了ABA操作");
        }

        System.out.println("=============解决ABA问题（AtomicStampReference）===========");
        new Thread(() -> {
            int curStamp = asr.getStamp();
            System.out.println("当前stamp: " + curStamp);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            asr.compareAndSet(0, 1, curStamp, curStamp + 1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            asr.compareAndSet(1, 0, asr.getStamp(), asr.getStamp() + 1);
        }, "t1").start();

        new Thread(() -> {
            int curStamp = asr.getStamp();
            System.out.println("当前stamp: " + curStamp);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = asr.compareAndSet(0, 100, curStamp, curStamp + 1);
            if (!result) {
                System.out.println("修改失败! 预期stamp: " + curStamp + ", 实际stamp: " + asr.getStamp());
            }
        }, "t2").start();
    }
}
