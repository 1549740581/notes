import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Date;
import java.util.Scanner;

/**
 * NIO UDP C-S模型：客户端
 */
public class NIOUdpClient {
    public static void main(String[] args) throws IOException {
        DatagramChannel dc = DatagramChannel.open();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();
            buf.put((new Date().toString() + ": " + next).getBytes());
            buf.flip();
            dc.send(buf, new InetSocketAddress("127.0.0.1", 2571));
            buf.clear();
        }
        dc.close();
        scanner.close();
    }
}
