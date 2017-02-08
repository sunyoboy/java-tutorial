package org.lieve.nio.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by DD240 on 2017/2/8.
 */
public class AIOServer {
    private AsynchronousServerSocketChannel server;

    public AIOServer() throws Exception {
        server = AsynchronousServerSocketChannel.open().bind(
                new InetSocketAddress("localhost", 8090));
    }

    public void startWithFuture()
            throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println("start listen port ");
        Future<AsynchronousSocketChannel> future = server.accept();
        AsynchronousSocketChannel socketChannel = future.get();
        ByteBuffer readBuf = ByteBuffer.allocate(1024);
        readBuf.clear();
        socketChannel.read(readBuf).get(100, TimeUnit.SECONDS);
        readBuf.flip();
        System.out.println("receive message :" + new String(readBuf.array()));
        System.out.println(Thread.currentThread().getName());
    }

    public void startWithCompletionHandler() throws InterruptedException {
        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            final ByteBuffer buffer = ByteBuffer.allocate(1024);
            /**
             * Invoked when an operation has completed.
             *
             * @param result     The result of the I/O operation.
             * @param attachment
             */
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                try {
                    result.write(ByteBuffer.wrap("helloWorld".getBytes())).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                System.out.println("start !");
                buffer.clear();
                try {
                    int len = result.read(buffer).get(100, TimeUnit.SECONDS);
                    buffer.flip();
                    System.out.println("received message: " + new String(buffer.array(), 0, len));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        result.close();
                        server.accept(null, this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(" end ");
            }

            /**
             * Invoked when an operation fails.
             *
             * @param exc        The exception to indicate why the I/O operation failed
             * @param attachment
             */
            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("failed: " + exc);
            }
        });

        //
        while (true) {
            System.out.println("main thread");
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) throws Exception {
        new AIOServer().startWithCompletionHandler();
    }
}
