import java.nio.ByteBuffer;

/**
 * @author sherman
 */
public class MarkFieldDemo {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String str = "abcde";

        byteBuffer.put(str.getBytes());
        byteBuffer.flip();

        byte[] dst = new byte[byteBuffer.limit()];
        byteBuffer.get(dst, 0, 2);
        System.out.println(new String(dst, 0, 2)); // ab
        System.out.println(byteBuffer.position());               // 2

        // mark标记一下
        byteBuffer.mark();

        byteBuffer.get(dst, 2, 2);
        System.out.println(new String(dst, 2, 2));  // cd
        System.out.println(byteBuffer.position());                // 4

        // 恢复到原来mark标记的位置，即position等于2
        byteBuffer.reset();
        System.out.println(byteBuffer.position());                // 2

        // 查看缓冲区中是否还有数据，此时position为2，还有3个数据
        if (byteBuffer.hasRemaining()) {
            System.out.println(byteBuffer.remaining());           // 3
        }
    }
}
