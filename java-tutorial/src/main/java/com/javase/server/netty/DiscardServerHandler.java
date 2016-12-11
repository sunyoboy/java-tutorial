package com.server.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Handles a server-side channel
 * 
 * @author sunlj
 * 
 */

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		// Discard the received data silently.
		/* ((ByteBuf) msg).release(); */
		
		// ByteBuf in = (ByteBuf) msg;
		
		ctx.write(msg);
		ctx.flush();

		/* System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII)); */

		/*
		 * try { // Do something with msg; while (in.isReadable()) {
		 * System.out.print((char) in.readByte()); System.out.flush(); } }
		 * finally { ReferenceCountUtil.release(msg); }
		 */
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}
