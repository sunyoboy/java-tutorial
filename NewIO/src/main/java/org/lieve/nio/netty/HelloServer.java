package org.lieve.nio.netty;


import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/4/17
 * Time: 9:05 PM
 */
public class HelloServer {
    public static void main(String[] args) {
        // Server 服务器启动器
        ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool())
        );

        // 设置一个处理客户端消息和各种消息事件的类(Handler)
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new HelloServerHandler());
            }
        });

        // 开放8000端口供客户端访问
        bootstrap.bind(new InetSocketAddress(8000));
    }

    private static class HelloServerHandler extends SimpleChannelHandler {
        /**
         * 当有客户端绑定到服务端的时候触发,打印"Hello World, I'm Server."
         * @param ctx
         * @param e
         * @throws Exception
         */
        @Override
        public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            super.channelConnected(ctx, e);
            System.out.println("Hello World, I'm Server.");
        }
    }
}
