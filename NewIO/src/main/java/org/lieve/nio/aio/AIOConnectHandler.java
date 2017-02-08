package org.lieve.nio.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

/**
 * Created by DD240 on 2017/2/8.
 */
public class AIOConnectHandler implements CompletionHandler<Void, AsynchronousSocketChannel> {
    /**
     * Invoked when an operation has completed.
     *
     * @param result     The result of the I/O operation.
     * @param attachment
     */
    @Override
    public void completed(Void result, AsynchronousSocketChannel attachment) {
        try {
            attachment.write(ByteBuffer.wrap("hello world".getBytes())).get();
            startRead(attachment);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Invoked when an operation fails.
     *
     * @param exc        The exception to indicate why the I/O operation failed
     * @param attachment
     */
    @Override
    public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
        exc.printStackTrace();
    }

    public void startRead(AsynchronousSocketChannel channel) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer, buffer, new AIOReadHandler(channel));
        // channel.read(buffer, channel, new AIOReadHandler(channel));
    }
}
