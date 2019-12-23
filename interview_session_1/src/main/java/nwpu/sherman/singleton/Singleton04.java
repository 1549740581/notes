package nwpu.sherman.singleton;

/**
 * 懒汉式单例模式，存在多线程安全问题
 *
 * @author sherman
 */
public class Singleton04 {
    private static Singleton04 INSTANCE;

    public static Singleton04 getInstance() {
        // 多线程安全问题
        if (INSTANCE == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Singleton04();
        }
        return INSTANCE;
    }

    private Singleton04() {
    }
}
