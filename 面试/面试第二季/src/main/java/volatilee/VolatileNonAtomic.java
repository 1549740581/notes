package volatilee;

/**
 * Volatile关键字：【不保证原子性】
 * i++：即使使用volatile关键字，一般情况下，最终的结果都小于 20 * 1000
 * @author sherman
 */
public class VolatileNonAtomic {
    private volatile int i = 0;

    public void addOne() {
        i++;
    }

    public int getI() {
        return i;
    }

    public static void main(String[] args) {
        VolatileNonAtomic vd = new VolatileNonAtomic();
        for (int i = 0; i < 20; ++i) {
            new Thread(() -> {
                for (int j = 0; j < 1000; ++j)
                    vd.addOne();
            }).start();
        }
        while (Thread.activeCount() > 2)
            Thread.yield();
        // 仅使用volatile的情况下, i最终的值一般小于20000
        System.out.println("最终i的值为: " + vd.getI());
    }
}
