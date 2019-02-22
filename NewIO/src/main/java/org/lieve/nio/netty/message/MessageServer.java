package org.lieve.nio.netty.message;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/4/17
 * Time: 10:05 PM
 */
public class MessageServer {
    public static void main(String[] args) {
        // Server 服务器启动器
        ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool())
        );

        // 设置一个处理客户端消息和各种消息事件的类(Handler)
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new MessageServerHandler());
            }
        });

        // 开放8000端口供客户端访问
        bootstrap.bind(new InetSocketAddress(8000));
    }

    private static class MessageServerHandler extends SimpleChannelHandler {

        /**
         * 当有客户端消息到达时触发
         * @param ctx
         * @param e
         * @throws Exception
         */
        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            super.messageReceived(ctx, e);
            ChannelBuffer buffer = (ChannelBuffer)e.getMessage();

            // 五位读取
            while(buffer.readableBytes() >= 5) {
                ChannelBuffer tmpBuffer = buffer.readBytes(5);
                System.out.println(tmpBuffer.toString(Charset.defaultCharset()));
            }

            // 读取剩下的信息
            System.out.println(buffer.toString(Charset.defaultCharset()));

        }
    }
}
