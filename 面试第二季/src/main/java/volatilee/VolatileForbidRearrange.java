package volatilee;

/**
 * Volatile关键字：【禁止指令重排序】
 * 高并发环境下DCL（Double Check Lock）单例模式使用volatile防止指令重排序
 *
 * @author sherman
 */
public class VolatileForbidRearrange {
    /**
     * 对INSTANCE使用volatile可以防止下面3条语句发生指令重排变成:1 -> 3 -> 2
     * 多线程环境下：当发生指令重排时, 进行到第3条语句时, INSTANCE != null但是该内存仅仅是原始内存
     * 对象并没有在该原始内存上初始化, 该方法直接return INSTANCE导致当前线程拿到一个非null但是未初始化
     * 的对象。如果在该内存上进行相应的访问对象数据操作就会出错
     */
    private static volatile VolatileForbidRearrange INSTANCE = null;

    private VolatileForbidRearrange() {
    }

    public static VolatileForbidRearrange getInstance() {

        if (INSTANCE == null) {
            synchronized (VolatileForbidRearrange.class) {
                if (INSTANCE == null) {
                    // 以下语句实际等同于3条语句：
                    // 1. rawMemory = memoryAllocate();     // 开辟原始内存
                    // 2. instance(rawMemory);              // 在原始内存上对对象初始化
                    // 3. 将rawMemory的地址赋值给INSTANCE, 此时INSTANCE != null
                    INSTANCE = new VolatileForbidRearrange();
                }
            }
        }
        return INSTANCE;
    }
}
