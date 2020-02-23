import java.nio.ByteBuffer;

/**
 * @author sherman
 */
public class ByteBufferDemo {
    public static void main(String[] args) {
        // 1. 分配一个指定大小为1024的Byte类型缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        System.out.println("----------------allocate()-----------------");
        System.out.println(buf.capacity());		    // 1024
        System.out.println(buf.limit());		    // 1024
        System.out.println(buf.position());		    // 0

        // 2. 使用put()方法将数据写入到缓冲区中
        String str = "abcde";
        buf.put(str.getBytes());
        System.out.println("----------------put()-----------------");
        System.out.println(buf.capacity());         // 1024
        System.out.println(buf.limit());            // 1024，此时limit没有变化
        System.out.println(buf.position());         // 5

        // 3. 使用flip()方法切换到写模式
        buf.flip();
        System.out.println("----------------flip()-----------------");
        System.out.println(buf.capacity());         // 1024
        System.out.println(buf.limit());            // 5
        System.out.println(buf.position());         // 0

        // 4. 使用get()方法对缓冲区中数据进行读取
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println("----------------get()-----------------");
        System.out.println("读取到数据: " + new String(dst));
        System.out.println(buf.capacity());         // 1024
        System.out.println(buf.limit());            // 5
        System.out.println(buf.position());         // 5, get()之后，position开始移动

        // 5. 使用rewind()将position恢复到读模式最开始状态, 可重复读
        buf.rewind();
        System.out.println("----------------rewind()-----------------");
        System.out.println(buf.capacity());         // 1024
        System.out.println(buf.limit());            // 5
        System.out.println(buf.position());         // 0

        // 6. 使用clear()方法清空缓冲区, 恢复到allocate状态, 但是缓冲区中数据依然存在
        buf.clear();
        buf.rewind();
        System.out.println("----------------clear()-----------------");
        System.out.println(buf.capacity());         // 1024
        System.out.println(buf.limit());            // 1024
        System.out.println(buf.position());         // 0
        System.out.println((char)buf.get());        // a，仍然能获取到数据，数据在缓冲区中仍然存在
    }
}
