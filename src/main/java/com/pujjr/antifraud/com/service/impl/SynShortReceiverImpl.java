package com.pujjr.antifraud.com.service.impl;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.pujjr.antifraud.com.service.IRddService;
import com.pujjr.antifraud.com.service.ISynShortReceiver;
import com.pujjr.antifraud.com.service.ISynShortSender;
import com.pujjr.antifraud.http.service.ISenderService;
import com.pujjr.antifraud.http.service.impl.SenderServiceImpl;
import com.pujjr.antifraud.vo.PersonBean;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author tom
 *
 */
public class SynShortReceiverImpl implements ISynShortReceiver{
	private static final Logger logger = Logger.getLogger(SynShortReceiverImpl.class);
	@Override
	public void doReceive(String recStr,final ChannelHandlerContext ctx) {
		// TODO Auto-generated method stub
		logger.info("receive from client："+recStr);
//		System.out.println("receive from client："+recStr);
		String sendStr = "";
		JSONObject recJson = JSONObject.parseObject(recStr);
		IRddService rddServiceImp = new RddServiceImpl();
		String tranCode = recJson.getString("tranCode");
		logger.info("tranCode："+tranCode);
		switch(tranCode){
		case "10001"://current
			logger.info("10001");
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
		sendStr = "["+recJson.getString("appId")+"]"+"|"+JSONObject.toJSONString(person);
		logger.info("send do client:"+sendStr);
		ISynShortSender sender = new SynShortSenderImpl();
		sender.doSend(sendStr, ctx);
	}

}
