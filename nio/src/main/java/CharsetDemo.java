import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @author sherman
 */
public class CharsetDemo {
    public static void main(String[] args) throws CharacterCodingException {
        // 获取所有编码集
//        SortedMap<String, Charset> charsets = Charset.availableCharsets();
//        for (Map.Entry<String, Charset> charset : charsets.entrySet()) {
//            System.out.println(charset.getKey() + ": " + charset.getValue());
//        }

        Charset gbk = Charset.forName("GBK");
        // 获取编码器和解码器
        CharsetEncoder encoder = gbk.newEncoder();
        CharsetDecoder decoder = gbk.newDecoder();

        // CharBuffer编码成ByteBuffer
        CharBuffer charBuf = CharBuffer.allocate(1024);
        charBuf.put("你好NIO");
        charBuf.flip();

        // 开始编码
        ByteBuffer byteBuf = encoder.encode(charBuf);
        for (int i = 0; i < byteBuf.limit(); i++) {
            System.out.println(byteBuf.get());
        }

        // 开始解码
        byteBuf.flip();
        CharBuffer decodeCharBuf = decoder.decode(byteBuf);
        System.out.println(decodeCharBuf.toString());

        // 使用UTF-8进行解码，出现乱码
        byteBuf.flip();
        // 直接解码，不需要显示指定使用解码器
        System.out.println(Charset.forName("UTF-8").decode(byteBuf).toString());
    }
}
