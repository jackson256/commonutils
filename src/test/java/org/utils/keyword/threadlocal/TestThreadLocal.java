/**     
 * Copyright © 2018 All rights reserved.
 *
 * @Package:org.utils.keyword.threadlocal
 * @author: hudson
 * @date:2018年6月8日 下午2:22:54
 */
package org.utils.keyword.threadlocal;

/**
 * @className: TestThreadLocal
 * @Description:
 * @author: hudson
 * @date: 2018年6月8日 下午2:22:54
 * @version: 1.0
 */
public class TestThreadLocal {

	private static final ThreadLocal<String> threadLocalA = new ThreadLocal<>();
	private static final ThreadLocal<String> threadLocalB = new ThreadLocal<>();

	public static void setValueA(String value) {
		threadLocalA.set(value);
	}

	public static void setValueB(String value) {
		threadLocalB.set(value);
	}

	public static String getValueA() {
		return threadLocalA.get();
	}

	public static String getValueB() {
		return threadLocalB.get();
	}

	public static void clearValueA() {
		threadLocalA.remove();
	}

	public static void clearValueB() {
		threadLocalB.remove();
	}

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				setValueA("A1");
				System.out.println("THREAD1:" + getValueA());
				clearValueA();

				setValueB("B1");
				System.out.println("thread1:" + getValueB());
				clearValueB();
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				setValueA("A2");
				System.out.println("THREAD2:" + getValueA());
				clearValueA();

				setValueB("B2");
				System.out.println("thread2:" + getValueB());
				clearValueB();
			}
		}).start();

	}

}
