package nwpu.sherman.singleton;

/**
 * 懒汉式单例，synchronized
 *
 * @author sherman
 */
public class Singleton05 {
    private static Singleton05 INSTANCE;

    public static Singleton05 getInstance() {
        synchronized (Singleton05.class) {
            if (INSTANCE == null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Singleton05();
            }
        }
        return INSTANCE;
    }

    private Singleton05() {
    }

}
