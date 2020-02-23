import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO非阻塞IO下C-S模型：服务端
 *
 * @author sherman
 */
public class NIOTcpServer {
    public static void main(String[] args) throws IOException {
        // 1. 创建Channel
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        // 2. 设置为非阻塞
        ssChannel.configureBlocking(false);
        // 3. 绑定端口
        ssChannel.bind(new InetSocketAddress(2571));
        // 4. 获得选择器
        Selector selector = Selector.open();
        // 5. 将通道注册在选择器上，并绑定监听的事件
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 6. 轮训获取selector上已经就绪的事件
        while(selector.select() > 0) {
            // 7. 获取当前selector中所有注册的键
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while(iter.hasNext()) {
                SelectionKey sk = iter.next();
                // 8. 判断具体是注册的哪种键
                if (sk.isAcceptable()){
                    // 8.1. 接受准备就绪，获取客户端的连接
                    // 注意需要返回一个和原来ssChannle不一样的Channel
                    SocketChannel acceptChannel = ssChannel.accept();
                    // 将客户端连接设置为非阻塞模式
                    acceptChannel.configureBlocking(false);
                    // 将accept后的channel再注册到selector上，并且键为读就绪状态
                    acceptChannel.register(selector, SelectionKey.OP_READ);
                }else if (sk.isReadable()){
                    // 8.2. 读就绪状态
                    SocketChannel sChannel = (SocketChannel) sk.channel();
                    // 读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len = 0;
                    while((len = sChannel.read(buf)) > 0) {
                        System.out.println(len);
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }
                // 9. 取消键
                iter.remove();
            }
        }
    }
}
