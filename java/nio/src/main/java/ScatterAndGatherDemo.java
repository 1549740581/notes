import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 演示Scatter & Gather
 *
 * @author sherman
 */
public class ScatterAndGatherDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile inRaf = new RandomAccessFile("nio\\tom.jpg", "rw");
        RandomAccessFile outRaf = new RandomAccessFile("nio\\dst-3.jpg", "rw");
        FileChannel inChannel = inRaf.getChannel();
        FileChannel outChannel = outRaf.getChannel();

        // 分散的buffer大小之和要大于输入图片
        ByteBuffer buf1 = ByteBuffer.allocate(1024 * 20);
        ByteBuffer buf2 = ByteBuffer.allocate(1024 * 20);
        ByteBuffer buf3 = ByteBuffer.allocate(1024 * 30);

        ByteBuffer[] bufs = {buf1, buf2, buf3};
        inChannel.read(bufs);
        // 读完之后需要flip操作切换成写模式
        for (ByteBuffer buf : bufs) {
            buf.flip();
        }
        outChannel.write(bufs);
    }
}
