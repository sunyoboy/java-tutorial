package org.lieve.nio.netty.message;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/4/17
 * Time: 10:06 PM
 */
public class MessageClient {
    public static void main(String[] args) {
        ClientBootstrap bootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()
                )
        );

        // 设置一个处理服务端消息和各种消息事件的类(Handler)
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new MessageClientHandler());
            }
        });

        // 连接到本地的8000端口的服务器
        bootstrap.connect(new InetSocketAddress("127.0.0.1", 8000));
    }

    private static class MessageClientHandler extends SimpleChannelHandler {

        /**
         * 当绑定到服务端时触发,给服务端发消息
         * @param ctx
         * @param e
         * @throws Exception
         */
        @Override
        public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            super.channelConnected(ctx, e);

            /*
            // 将字符串构造成ChannelBuffer, 传递给服务端
            String msg = "Hello World, I'm client";
            ChannelBuffer buffer = ChannelBuffers.buffer(msg.length());
            buffer.writeBytes(msg.getBytes());
            e.getChannel().write(buffer);
            */
            sendMessageByFrame(e);
        }

        /**
         * 分三份发送
         * @param e
         */
        private void sendMessageByFrame(ChannelStateEvent e) {
            String msgOne = "Hello, ";
            String msgTwo = "I'm ";
            String msgThree = "Client. ";
            e.getChannel().write(transString2ChannelBuffer(msgOne));
            e.getChannel().write(transString2ChannelBuffer(msgTwo));
            e.getChannel().write(transString2ChannelBuffer(msgThree));
        }

        /**
         * 将字符串转为ChannelBuffer
         * @param str 字符串,要求非null
         * @return ChannelBuffer
         */
        private ChannelBuffer transString2ChannelBuffer(String str) {
            ChannelBuffer buffer = ChannelBuffers.buffer(str.length());
            buffer.writeBytes(str.getBytes());
            return buffer;
        }
    }


}
