package com.pujjr.antifraud.http;

/**
 * @author tom
 *
 */
import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pujjr.antifraud.vo.PersonBean;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpHeaders.Values;
import io.netty.handler.codec.http.HttpRequest;

public class AntiFraudHttpServerInboundHandler extends ChannelInboundHandlerAdapter {
	private HttpRequest request;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		if (msg instanceof HttpRequest) {
			System.out.println("HttpRequest");
			request = (HttpRequest) msg;

			String uri = request.getUri();
			System.out.println("Uri:" + uri);
		}
		if (msg instanceof HttpContent) {
			
			
			System.out.println("HttpContent");
			HttpContent content = (HttpContent) msg;
			ByteBuf buf = content.content();
			System.out.println("buf:" + buf.toString(io.netty.util.CharsetUtil.UTF_8));
			buf.release();
//			String[] restArray = uri.split("/");
			/*System.out.println("uri:"+uri);
			switch(uri){
			case "//antifraud//current":
				System.out.println("当期反欺诈");
				break;
			case "//antifraud//his":
				System.out.println("历史反欺诈");
				break;
			}*/
//			String res = "I am OK";
			PersonBean person = new PersonBean();
			person.setName("唐亮");
			person.setSex("男");
			String res = JSONObject.toJSONString(person);
			FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK,
					Unpooled.wrappedBuffer(res.getBytes("UTF-8")));
			response.headers().set(CONTENT_TYPE, "application/json;charset=UTF-8");
			response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
			if (HttpHeaders.isKeepAlive(request)) {
				response.headers().set(CONNECTION, Values.KEEP_ALIVE);
			}
			ctx.write(response);
			ctx.flush();
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// log.error(cause.getMessage());
		ctx.close();
	}

}
