package org.lieve.nio.nbio;

import org.lieve.nio.common.Constant;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 2/11/17
 * Time: 10:39 AM
 */
public class HttpServer {

    public static void main(String[] args) throws Exception {

        // Step 1 : Create ServerSocketChannel, bind port
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(Constant.HOST, Constant.PORT));

        // Step 2 : set NoBlock model
        server.configureBlocking(false);

        // Step 3 : register Selector
        Selector selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);

        // Step 4 : Create Handler
        SocketChannelHandler handler = new SocketChannelHandler(
                Constant.BUFFER_BYTE_SIZE);

        while(true) {
            // wait for request
            if(selector.select(3000) == 0) {
                System.out.println(" waiting for timeout");
                continue;
            }

            System.out.println("handling request");

            // get handling SelectionKey
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                try {
                    // receive connected request
                    if(key.isAcceptable()) {
                        handler.handleAccept(key);
                    }

                    // read data
                    if(key.isReadable()) {
                        handler.handleRead(key);
                    }
                } catch (IOException e) {
                    keyIterator.remove();
                    continue;
                }

                // after handled remove key from iterator of SelectionKey
                keyIterator.remove();
            }

        }
    }
}
