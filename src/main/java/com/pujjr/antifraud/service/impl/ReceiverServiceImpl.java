package com.pujjr.antifraud.service.impl;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.pujjr.antifraud.http.AntiFraudHttpServerInboundHandler;
import com.pujjr.antifraud.service.IRddService;
import com.pujjr.antifraud.service.IReceiverService;
import com.pujjr.antifraud.service.ISenderService;
import com.pujjr.antifraud.vo.PersonBean;
import com.pujju.antifraud.enumeration.ETrans;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;

/**
 * @author tom
 *
 */
public class ReceiverServiceImpl implements IReceiverService{
	private Logger logger = Logger.getLogger(ReceiverServiceImpl.class);
	@Override
	public void doReceive(String recStr,ChannelHandlerContext ctx,HttpRequest request) {
		logger.info("receive from client："+recStr);
		String sendStr = "";
		JSONObject recJson = JSONObject.parseObject(recStr);
		IRddService rddServiceImp = new RddServiceImpl();
		switch(recJson.getString("tranCode")){
		case "10001"://current
			rddServiceImp.selectCurr(recJson.getString("appId"));
			break;
		case "10002"://his
			rddServiceImp.selectHis(recJson.getString("appId"));
			break;
		}
		//发送
		PersonBean person = new PersonBean();
		person.setName("唐亮");
		person.setSex("男");
		sendStr = JSONObject.toJSONString(person);
		ISenderService sender = new SenderServiceImpl();
		sender.doSend(sendStr, ctx,request);
	}
}
