package oom;

import java.nio.ByteBuffer;

/**
 * 演示Direct Buffer Memory错误
 *
 * -Xms5m -Xmx5m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * @author sherman
 */
public class DirectBufferMemory {
    public static void main(String[] args) {
        // 默认应该是1/4系统内存
        System.out.println("配置的堆外内存为：" + (sun.misc.VM.maxDirectMemory()) / (double) 1024 / 1024 + "MB");
        // Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
        ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
