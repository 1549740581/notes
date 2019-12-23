package nwpu.sherman.singleton;

/**
 * Double check lock & volatile
 *
 * 懒汉式单例模式
 *
 * @author sherman
 */
public class Singleton06 {
    private static volatile Singleton06 INSTANCE;

    public static Singleton06 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton06.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Singleton06();
                }
            }
        }
        return INSTANCE;
    }

    private Singleton06() {

    }
}
