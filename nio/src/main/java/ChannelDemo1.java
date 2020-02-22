import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用管道完成图片拷贝（非直接缓冲区）
 * @author sherman
 */
public class ChannelDemo1 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            // 相对工程根路径，这里nio以mudule形式存在于project（notes）下
            // 因此工程根路径为notes，需要加上nio前缀
            fis = new FileInputStream("nio\\tom.jpg");
            fos = new FileOutputStream("nio\\dst.jpg");

            // 从stream流中获取Channel
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(1024);
            while ((inChannel.read(buf)) != -1) {
                // 切换成读模式
                buf.flip();
                outChannel.write(buf);
                buf.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null) {
                outChannel.close();
            }
            if (inChannel != null) {
                inChannel.close();
            }
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }
}
