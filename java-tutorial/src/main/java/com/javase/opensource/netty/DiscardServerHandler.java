package com.opensource.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		// Discard the received data silently.
		/*((ByteBuf)msg).release();
		System.out.println(msg);*/
		
		
/*		try {
			// Do something with msg
			ByteBuf in = (ByteBuf)msg;
			while (in.isReadable()) {
				// System.out.println((char)in.readChar());
				System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));
				System.out.flush();
			}
		} finally {
			ReferenceCountUtil.release(msg);
		}*/
		
		ctx.write(msg);
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
	
}
