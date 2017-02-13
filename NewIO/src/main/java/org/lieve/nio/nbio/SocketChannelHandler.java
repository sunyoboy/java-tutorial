package org.lieve.nio.nbio;

import org.lieve.nio.common.Constant;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 2/10/17
 * Time: 11:57 PM
 */
public class SocketChannelHandler {

    private int bufferSize = Constant.BUFFER_BYTE_SIZE;

    private String localCharset = Constant.CHARSET;

    public SocketChannelHandler() {
    }

    public SocketChannelHandler(int bufferSize) {
        this(bufferSize, null);
    }

    public SocketChannelHandler(String localCharset) {
        this(-1, localCharset);
    }

    public SocketChannelHandler(int bufferSize, String localCharset) {
        if (bufferSize > 0) {
            this.bufferSize = bufferSize;
        }

        if (localCharset != null) {
            this.localCharset = localCharset;
        }
    }

    public void handleAccept(SelectionKey key) throws IOException {
        SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ,
                ByteBuffer.allocate(Constant.BUFFER_BYTE_SIZE));
    }

    public void handleRead(SelectionKey key) throws IOException {
        // get channel
        SocketChannel sc = (SocketChannel) key.channel();

        // get buffer and reset buffer
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();

        // if nothing to read then close it
        if (sc.read(buffer) == -1) {
            sc.close();
        } else {
            // 将buffer转换为读状态
            buffer.flip();

            // 将接收到的值按localCharset格式编码后保存到receivedString
            String receivedStr = Charset.forName(localCharset).newDecoder()
                    .decode(buffer).toString();
            System.out.println("received from client: " + receivedStr);

            // 返回数据给客户端
            String sendStr = "received data : " + receivedStr;
            buffer = ByteBuffer.wrap(sendStr.getBytes(localCharset));
            sc.write(buffer);

            // close socket
            sc.close();
        }
    }
}
