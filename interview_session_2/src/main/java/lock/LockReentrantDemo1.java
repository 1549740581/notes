package lock;

/**
 * 演示可重入锁
 *
 * @author sherman
 */
public class LockReentrantDemo1 {
    public static void main(String[] args) {
        // 程序正常运行输出：hello
        new LockReentrantDemo1().lockReentrant();
    }

    public synchronized void lockReentrant() {
        /**
         * 注意这个println方法内部就使用了synchronized关键字，锁住了this
         * 即synchronized块中仍然能够使用synchronized关键字 -> 可重入的
         */
        System.out.println("hello");
    }
}
