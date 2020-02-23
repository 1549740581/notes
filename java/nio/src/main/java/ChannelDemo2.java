import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 使用管道完成图片复制（只用直接内存，即内存映射文件）
 * @author sherman
 */
public class ChannelDemo2 {
    public static void main(String[] args) throws IOException {

        // 注意：Channel.open的StandardOpenOption要和Channel.map中MapMode模式相互对应
        FileChannel inChannel = FileChannel.open(Paths.get("nio\\tom.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("nio\\dst-1.jpg"),
                StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        // 内存映射文件，和ByteBuffer.allocateDirect()原理类似
        MappedByteBuffer inMapBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        // 现在不需要通道，因为都直接在内存映射文件中
        byte[] buf = new byte[inMapBuf.limit()];
        inMapBuf.get(buf);
        outMapBuf.put(buf);

        inChannel.close();
        outChannel.close();
    }
}
