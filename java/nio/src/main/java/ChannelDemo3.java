import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 使用管道完成图片复制（直接使用transferTo和transferFrom完成传输）
 *
 * @author sherman
 */
public class ChannelDemo3 {
    public static void main(String[] args) throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("nio\\tom.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("nio\\dst-2.jpg"),
                StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        // transferTo和transferFrom等价，不同形式而已
        // inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());
        inChannel.close();
        outChannel.close();
    }
}
