/**     
 * Copyright © 2018 All rights reserved.
 *
 * @Package:com.hudson.designpattern.singleton
 * @author: huhan
 * @date:2018年5月20日 下午10:51:25
 */
package com.hudson.designpattern.singleton;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/** 
 * @className: ThreadPoolSingleton
 * @Description:
 * @author: huhan
 * @date: 2018年5月20日 下午10:51:25
 * @version: 1.0
 */
public class ThreadPoolSingleton {
	
	private static ThreadPoolExecutor threadPool;
	
	private ThreadPoolSingleton(){}
	
	public static ThreadPoolExecutor getInstance(){
		if(threadPool == null){
			synchronized (ThreadPoolSingleton.class) {
				if(threadPool==null){
					threadPool = new ThreadPoolExecutor(10, 10, 1000, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<Runnable>(10));
				}
			}
		}
		return threadPool;
		
	}

}
