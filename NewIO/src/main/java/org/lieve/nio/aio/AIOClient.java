package org.lieve.nio.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * Created by DD240 on 2017/2/8.
 */
public class AIOClient {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withFixedThreadPool(Runtime.getRuntime().availableProcessors(),
                Executors.defaultThreadFactory());
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open(group);
        // socketChannel.connect(new InetSocketAddress("localhost", 8090));
        socketChannel.setOption(StandardSocketOptions.TCP_NODELAY, true);
        socketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
        socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
        socketChannel.connect(new InetSocketAddress("localhost", 8090), socketChannel, new AIOConnectHandler());
        // socketChannel.write(ByteBuffer.wrap("test".getBytes())).get();
    }
}
