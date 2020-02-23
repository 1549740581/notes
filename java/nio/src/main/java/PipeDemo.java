import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Pipe完成数据单向连接
 *
 * @author sherman
 */
public class PipeDemo {
    public static void main(String[] args) throws IOException {
        // 1. 获取管道
        Pipe pipe = Pipe.open();

        // 2. 将缓冲区中内容sink到pipe中
        ByteBuffer buf = ByteBuffer.allocate(1024);
        Pipe.SinkChannel sinkChannel = pipe.sink();

        // 3. 向缓冲区中写数据
        buf.put("你好NIO ".getBytes());
        buf.flip();
        sinkChannel.write(buf);

        // 4. 从缓冲区中读数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        buf.flip();
        int len = sourceChannel.read(buf);
        System.out.println(new String(buf.array(), 0, len));
    }
}
