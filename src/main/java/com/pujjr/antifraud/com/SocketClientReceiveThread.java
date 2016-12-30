package com.pujjr.antifraud.com;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author tom
 *
 */
public class SocketClientReceiveThread implements Runnable{
	private Socket socket;
	public SocketClientReceiveThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		int i = 0;
		while(i < 10){
			i++;
			System.out.println(i);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				InputStream is = this.socket.getInputStream();
				byte[] buf = new byte[1024];
				int readLenth = 0;
				StringBuffer sb = new StringBuffer();
				while((readLenth = is.read(buf)) > 0){
					String tempStr = new String(buf,"gbk");
					sb.append(tempStr);
					System.out.println("接收tempStr："+tempStr);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
