package com.pujjr.SparkApp;

/**
 * @author tom
 *
 */
public class MyThread extends Thread{
	TestMain testMain;
	String threadName;
	public MyThread(TestMain testMain,String threadName){
		this.testMain = testMain;
		this.threadName = threadName;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; i++) {
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			testMain.callBack("线程"+threadName+"第"+i+"次循环计算"+"callBack"+i,10.0);
			System.out.println("线程"+threadName+"第"+i+"次循环计算"+"|"+testMain.getMoney());
//			System.out.println("i:"+i);
		}
	}
}
