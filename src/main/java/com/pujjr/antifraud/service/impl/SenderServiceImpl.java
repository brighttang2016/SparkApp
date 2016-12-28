package com.pujjr.antifraud.service.impl;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.io.UnsupportedEncodingException;
import java.security.AccessControlContext;

import com.alibaba.fastjson.JSONObject;
import com.pujjr.antifraud.service.ISenderService;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpHeaders.Values;
import io.netty.handler.codec.http.HttpRequest;

/**
 * @author tom
 *
 */
public class SenderServiceImpl implements ISenderService {

	@Override
	public void doSend(String sendStr,ChannelHandlerContext ctx,HttpRequest request) {
		System.out.println("send to client："+sendStr);
		FullHttpResponse response = null;
		try {
			response = new DefaultFullHttpResponse(HTTP_1_1, OK,Unpooled.wrappedBuffer(sendStr.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.headers().set(CONTENT_TYPE, "application/json;charset=UTF-8");
		response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
//		response.headers().set(HttpHeaders.Names.ACCESS_CONTROL_ALLOW_ORIGIN, "http://127.0.0.1:8080/ActivitiSpringPro");//白名单暂时无效
		response.headers().set(HttpHeaders.Names.ACCESS_CONTROL_ALLOW_ORIGIN, "*");//所有白名单
		response.headers().set(HttpHeaders.Names.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT");//GET, POST, PUT
		response.headers().set(HttpHeaders.Names.ACCESS_CONTROL_EXPOSE_HEADERS, "X-My-Custom-Header, X-Another-Custom-Header");
		if (HttpHeaders.isKeepAlive(request)) {
			response.headers().set(CONNECTION, Values.KEEP_ALIVE);
		}
//		response.setStatus(HttpResponseStatus.BAD_GATEWAY);
		ctx.write(response);
		ctx.flush();
	}
}
