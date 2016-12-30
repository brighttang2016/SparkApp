package com.pujjr.antifraud.com.service.impl;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.pujjr.antifraud.com.service.ISynShortSender;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author tom
 *
 */
public class SynShortSenderImpl implements ISynShortSender {
	private static final Logger logger = Logger.getLogger(SynShortSenderImpl.class);
	@Override
	public void doSend(String sendStr,final ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		final ByteBuf time = ctx.alloc().buffer(4); // (2)
//		String sendStr = System.currentTimeMillis()+"1234567重庆永川区大安";
        byte[] send = null;
        try {
			send = sendStr.getBytes("gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        time.writeBytes(send);
        while(time.readableBytes() < 100){
        	try {
    			Thread.currentThread().sleep(1000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
        	 logger.info("time.readableBytes():"+time.readableBytes());
        	 time.writeBytes(send);
        } 
        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                /*ctx.close();
                System.out.println("服务端已主动断开链接");*/
            }
        }); // (4)
	}
	
}