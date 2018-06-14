/**     
 * Copyright © 2018 All rights reserved.
 *
 * @Package:org.utils.keyword.threadlocal
 * @author: hudson
 * @date:2018年6月8日 下午2:42:43
 */
package org.utils.keyword.threadlocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/** 
 * @className: Demo01
 * @Description:
 * @author: hudson
 * @date: 2018年6月8日 下午2:42:43
 * @version: 1.0
 */
public class Demo01 {
	
	private static Map<Thread, Integer> threadData = new HashMap<>();
	
	public static void main(String[] args) {
		WordThread thread1 = new WordThread(threadData);
		WordThread thread2 = new WordThread(threadData);
		
		thread1.start();
		thread2.start();
		
		System.out.println(Thread.currentThread().getName()+":   finished!");
		
	}

}

class WordThread extends Thread{
	
	private Map<Thread, Integer> mThreadData;
	
	public WordThread(){}
	
	public WordThread(Map<Thread, Integer> threadData){
		super();
		this.mThreadData = threadData;
	}
	
	/* (non-Javadoc)   
	 *    
	 * @see java.lang.Thread#run()   
	 */  
	@Override
	public void run() {
		super.run();
		System.out.println("WordThread:" + Thread.currentThread().getName());
		
		int dataIn = new Random().nextInt(10);
		
		synchronized (mThreadData) {
			mThreadData.put(Thread.currentThread(), dataIn);
		}
		
		int dataOut = mThreadData.get(Thread.currentThread());
		System.out.println(Thread.currentThread().getName() +"===:"+dataOut);
		
	}
	
}
