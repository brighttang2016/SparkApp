package com.pujjr.SparkApp;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * @author tom
 *
 */
public class MessageLength {
	/**
	 * tom 2017年1月3日
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sendStr = "[模拟客户端0]|{\"name\":\"唐亮\",\"sex\":\"男\"}";
		long sendLength = sendStr.length();
		System.out.println(sendLength);
		byte[] lengthArray = new byte[5];
		lengthArray = (sendLength+"").getBytes();
		System.out.println(StringUtils.leftPad(sendStr.length()+"", 5, "0"));
	}
}
