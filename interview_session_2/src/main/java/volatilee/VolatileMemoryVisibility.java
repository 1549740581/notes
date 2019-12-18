package volatilee;

/**
 * Volatile关键字：【内存可见性】
 * - 使用语句（1），注释语句（2） -> 程序一直等待
 * - 使用语句（2），注释语句（1） -> 程序正常结束
 *
 * @author sherman
 */
public class VolatileMemoryVisibility {
    //private int i;            // （1）
    private volatile int i;     // （2）

    public void changeI() {
        this.i = 100;
    }

    public static void main(String[] args) {
        VolatileMemoryVisibility vd = new VolatileMemoryVisibility();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            vd.changeI();
            System.out.println("子线程改变了i的值！");
        }).start();

        while (vd.i == 0) {
        }
        System.out.println("main线程感知到i的变化!");
    }
}
