package org.lieve.nio.common;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 2/7/17
 * Time: 11:04 PM
 */
public class NIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc =
                ServerSocketChannel.open();
        ServerSocket serverSocket = ssc.socket();

        //
        serverSocket.bind(new InetSocketAddress(Constant.PORT));
        ssc.configureBlocking(false);

        Selector selector = Selector.open();

        // 注册感兴趣的连接建立事件
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        Set<SelectionKey> keys = selector.selectedKeys();

        for(SelectionKey key : keys) {
            if(key.isAcceptable()) {
                ServerSocketChannel serverSocketChannel =
                        (ServerSocketChannel)key.channel();
                SocketChannel sc = serverSocketChannel.accept();
                if(sc == null) {
                    continue;
                }
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
            }
        }
    }
}
