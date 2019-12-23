package nwpu.sherman.singleton;

import java.io.IOException;
import java.util.Properties;

/**
 * 饿汉式Singleton 静态代码块初始化 适合稍微复杂的情况
 *
 * @author sherman
 */
public class Singleton02 {
    public static final Singleton02 INSTANCE;
    private String info;

    public String getInfo() {
        return info;
    }

    static {
        Properties properties = new Properties();
        try {
            properties.load(Singleton02.class.getClassLoader().getResourceAsStream("singleton.properties"));
            INSTANCE = new Singleton02(properties.getProperty("info"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private Singleton02(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Singleton02{" +
                "info='" + info + '\'' +
                '}';
    }
}
