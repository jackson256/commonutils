/**     
 * Copyright © 2018 All rights reserved.
 *
 * @Package:com.hudson.thread.printnum
 * @author: huhan
 * @date:2018年5月26日 下午5:19:35
 */
package com.hudson.thread.printnum;

/**
 * @className: NumPrint2
 * @Description:10个线程按顺序打印0-10
 * @author: huhan
 * @date: 2018年5月26日 下午5:19:35
 * @version: 1.0
 */
public class NumPrint2 {
	public static void main(String[] args) {
		final NumPrint numprint = new NumPrint();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					numprint.print(Thread.currentThread().getName());

				}

			}, "" + i).start();
		}
	}
}

class NumPrint {
	private int number = 0;
	private int activeNum = 1;

	public synchronized void print(String name) {
		int num = Integer.parseInt(name) + 1;
		while (activeNum != num) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		number++;
		System.out.println("线程-" + num + ":" + number);
		activeNum++;
		this.notifyAll();
	}
}
