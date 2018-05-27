/**     
 * Copyright © 2018 All rights reserved.
 *
 * @Package:com.hudson.thread.printnum
 * @author: huhan
 * @date:2018年5月26日 下午5:01:23
 */
package com.hudson.thread.printnum;

/**
 * @className: MyNumberPrint
 * @Description:三个线程按顺序12345,678910,1112131415打印，一直打印到数字75
 * @author: huhan
 * @date: 2018年5月26日 下午5:01:23
 * @version: 1.0
 */
public class MyNumberPrint {

	public static void main(String[] args) {
		final MyBusiness business = new MyBusiness();
		for (int i = 0; i < 3; i++) {// 3个线程
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int m = 0; m < 5; m++) {// 每个线程循环打印5次；一直到75
						business.print(Thread.currentThread().getName());// 线程名为i
					}
				}
			}, String.valueOf(i)).start();
		}
	}

}

class MyBusiness {
	private int number = 0;// 打印的数字
	private int activeThreadNum = 1;// 标记打印该数字的线程

	public synchronized void print(String threadName) {
		int num = Integer.parseInt(threadName) + 1;
		while (activeThreadNum != num) {// 不是本线程打印该数字
			try {
				this.wait();// 线程等待,notify后继续判断循环条件
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < 5; i++) {// 连续打印5个数字
			number++;
			System.out.println("线程-" + num + ":" + number);
		}
		activeThreadNum = activeThreadNum % 3 + 1;// 重置下一个active线程
		this.notifyAll();
	}
}
