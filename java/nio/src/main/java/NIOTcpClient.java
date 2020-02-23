import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Scanner;

/**
 * NIO非阻塞IO下C-S模型：客户端
 *
 * @author sherman
 */
public class NIOTcpClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 2571));
        // 设置为非阻塞模式
        socketChannel.configureBlocking(false);
        ByteBuffer buf = ByteBuffer.allocate(1024);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();
            buf.put((new Date().toString() + ": " + next).getBytes());
            buf.flip();
            socketChannel.write(buf);
            buf.clear();
            System.out.println(next);
        }
        socketChannel.close();
        scanner.close();
    }
}
