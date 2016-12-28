package com.pujjr.antifraud.service;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

/**
 * @author tom
 *
 */
public interface IReceiverService {
	public void doReceive(String recStr,ChannelHandlerContext ctx,HttpRequest request);
}
