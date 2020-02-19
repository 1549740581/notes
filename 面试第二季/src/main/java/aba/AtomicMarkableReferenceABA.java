package aba;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * ABA问题解决方案，AtomicMarkableReference
 *
 * @author sherman
 */
public class AtomicMarkableReferenceABA {
    private static AtomicMarkableReference<Integer> amr = new AtomicMarkableReference<>(0, false);

    public static void main(String[] args) {
        new Thread(() -> {
            amr.compareAndSet(0, 1, false, true);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            amr.compareAndSet(1, 0, true, true);
            System.out.println("子线程进行了ABA修改!");
        }, "子线程").start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean res = amr.compareAndSet(0, 100, false, true);
        if (!res) {
            System.out.println("修改失败! 当前isMarked: " + amr.isMarked());
        }
    }
}
