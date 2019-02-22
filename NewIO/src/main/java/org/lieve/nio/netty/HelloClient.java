package org.lieve.nio.netty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Netty Client
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/4/17
 * Time: 7:46 PM
 */
public class HelloClient {
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
                return Channels.pipeline(new HelloClientHandler());
            }
        });

        // 连接到本地的8000端口的服务器
        bootstrap.connect(new InetSocketAddress("127.0.0.1", 8000));
    }

    private static class HelloClientHandler extends SimpleChannelHandler {

        /**
         * 当绑定到服务端时触发,打印"Hello World, I'm client"
         * @param ctx
         * @param e
         * @throws Exception
         */
        @Override
        public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            super.channelConnected(ctx, e);
            System.out.println("Hello World, I'm client");
        }
    }
}
