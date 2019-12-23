package nwpu.sherman.singleton;

/**
 * 懒汉式单例：内部类
 *
 * @author sherman
 */
public class Singleton07 {

    /**
     * 静态类并不会随着外部类的加载而初始化，也是线程安全的
     */
    private static class Inner {
        private static final Singleton07 INSTANCE = new Singleton07();
    }

    private Singleton07() {
    }

    /**
     * 只有显式调用内部类才会导致内部类初始化，因此也可以实现延迟加载
     */
    public static Singleton07 getInstance() {
        return Inner.INSTANCE;
    }
}
