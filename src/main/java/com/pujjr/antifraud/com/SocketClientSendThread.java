package com.pujjr.antifraud.com;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;


/**
 * @author tom
 *
 */
public class SocketClientSendThread implements Runnable{
	private Socket socket;
	private String sendStr = "";
	public SocketClientSendThread(Socket socket,String sendStr) {
		this.socket = socket;
		this.sendStr = sendStr;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		while(true){
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			InputStream is = System.in;
			if(sendStr.length() > 0){
				ByteArrayInputStream is = null;
				try {
					is = new ByteArrayInputStream(this.sendStr.getBytes("gbk"));
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				byte[] buf = new byte[1024];
				int readLength = 0;
				OutputStream os;
				try {
					os = this.socket.getOutputStream();
					while((readLength = is.read(buf)) > 0){
						os.write(buf, 0, readLength);
						os.flush();
					}
//					os.close();
					sendStr = "";
					System.out.println("发送成功");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
//	}

}
