package org.lieve.nio.common;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * 基于java.nio.channels中的Channel和Selector实现TCP/IP + NIO
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 2/7/17
 * Time: 10:28 PM
 */
public class NIO {

    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open();
        // 设置未非阻塞模式
        channel.configureBlocking(false);

        SocketAddress remoteSocketAddress = new InetSocketAddress("127.0.0.1", 8090);

        channel.connect(remoteSocketAddress);

        Selector selector = Selector.open();

        //向channel注册selector及感兴趣的事件
        channel.register(selector, SelectionKey.OP_CONNECT);
        int nKeys = selector.select(Constant.TIME_OUT); // 1000毫秒超市时间
        SelectionKey sKey = null;
        if (nKeys > 0) {
            Set<SelectionKey> keys = selector.selectedKeys();
            for (SelectionKey key: keys) {

                //
                if (key.isConnectable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    sc.configureBlocking(false);
                    sKey = sc.register(selector, SelectionKey.OP_READ);
                    sc.finishConnect();
                } else if (key.isReadable()) {
                    ByteBuffer buffer = ByteBuffer.allocate(Constant.BUFFER_BYTE_SIZE);
                    SocketChannel sc = (SocketChannel)key.channel();
                    int readBytes = 0;
                    try {
                        int ret = 0;
                        try {
                            while((ret = sc.read(buffer))> 0) {
                                readBytes += ret;
                            }
                        } finally {
                            buffer.flip();
                        }
                    } finally {
                        if (buffer != null) {
                            buffer.clear();
                        }
                    }
                }else if (key.isWritable()) {
                    // 取消对OP_WRITE事件的注册
                    key.interestOps(key.interestOps() & (~SelectionKey.OP_WRITE));
                    SocketChannel sc = (SocketChannel)key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(Constant.BUFFER_BYTE_SIZE);
                    //
                    int writtenedSize = sc.write(buffer);

                    if (writtenedSize == 0) {
                        key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
                    }
                }
            }
            selector.selectedKeys().clear();
        }

        int wSize = channel.write(ByteBuffer.allocate(Constant.BUFFER_BYTE_SIZE));

        if(wSize == 0) {
            // key.interestOps()
        }
    }
}
