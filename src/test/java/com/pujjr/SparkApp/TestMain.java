package com.pujjr.SparkApp;

/**
 * @author tom
 *
 */
public class TestMain {

	public double money = 0.00;
	
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public void hello(Parent child){
		child.parentHello();
		System.out.println("线程回调");
	}
	public synchronized void callBack(String callBackStr,double add){
//		System.out.println(callBackStr);
		money += add;
	}
	/**
	 * tom 2016年12月8日
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestMain testMain = new TestMain();
		System.out.println("0000");
		new MyThread(testMain,"thread1").start();
		System.out.println("11111");
		new MyThread(testMain,"thread2").start();
		System.out.println("2222");
//		new MyThread(testMain,"thread3").start();
//		System.out.println("333");
	}
}
