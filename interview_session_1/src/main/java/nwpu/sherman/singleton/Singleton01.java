package nwpu.sherman.singleton;

/**
 * Singleton设计模式
 *
 * 例如：Runtime类代表JVM运行环境
 *
 * @author sherman
 */
public class Singleton01 {
    /**
     * 饿汉式01 在类初始化时候直接创建对象，不存在线程安全问题
     */
    public static final Singleton01 INSTANCE = new Singleton01();

    private Singleton01() {
    }
}
