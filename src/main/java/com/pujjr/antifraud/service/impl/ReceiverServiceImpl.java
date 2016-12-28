package com.pujjr.antifraud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pujjr.antifraud.service.IReceiverService;
import com.pujjr.antifraud.service.ISenderService;
import com.pujjr.antifraud.vo.PersonBean;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

/**
 * @author tom
 *
 */
public class ReceiverServiceImpl implements IReceiverService{

	@Override
	public void doReceive(String recStr,ChannelHandlerContext ctx,HttpRequest request) {
		System.out.println("receive from client："+recStr);
		
		String sendStr = "";
		//发送
		PersonBean person = new PersonBean();
		person.setName("唐亮");
		person.setSex("男");
		sendStr = JSONObject.toJSONString(person);
		ISenderService sender = new SenderServiceImpl();
		sender.doSend(sendStr, ctx,request);
	}
}
