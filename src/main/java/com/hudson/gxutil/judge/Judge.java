/**     
 * Copyright © 2018 All rights reserved.
 *
 * @Package:com.hudson.gxutil.judge
 * @author: huhan
 * @date:2018年5月27日 下午12:46:55
 */
package com.hudson.gxutil.judge;

import java.util.Hashtable;
import java.util.Map;

/** 
 * @className: Judge
 * @Description:
 * @author: huhan
 * @date: 2018年5月27日 下午12:46:55
 * @version: 1.0
 */
public class Judge {
	
	private static Map<String, String> map = new Hashtable();
	  private static Judge judge = new Judge();
	  
	  static { map.put("1", "123456");
	    map.put("2", "123456");
	    map.put("3", "123456");
	    map.put("4", "123456");
	    map.put("5", "123456");
	    map.put("6", "123456"); }
	  
	  private long starts = 0L;
	  
	  public static void main(String[] args)
	  {
	    new Judge().start();
	  }
	  
	  public void start()
	  {
	    Thread run1 = new Thread(new Run1(), "线程1");
	    Thread run2 = new Thread(new Run2(), "线程2");
	    this.starts = System.currentTimeMillis();
	    run2.start();
	    run1.start();
	    System.out.println("主线程结束！");
	  }
	  
	  public void judgeMethod() {}
	  
	  public static boolean notNull(String str)
	  {
	    return (str != null) && (str.length() > 0);
	  }
	  
	  public static boolean notTrimNull(String str)
	  {
	    return (str != null) && (notNull(str.replace(" ", "")));
	  }
	  
	  public static boolean notObject(Object obj)
	  {
	    return ((Object[])obj).length > 0;
	  }
	  
	  public static boolean notObject(Object obj, boolean bool)
	  {
	    if ((bool) && (obj != null)) {
	      if (obj.getClass().isArray()) {
	        if (((Object[])obj).length > 0) {
	          return true;
	        }
	        return false; }
	      if ((obj instanceof Iterable)) {
	        if (((Iterable)obj).iterator().hasNext()) {
	          return true;
	        }
	        return false; }
	      if ((obj instanceof Map)) {
	        if (((Map)obj).size() > 0) {
	          return true;
	        }
	        return false;
	      }
	      return true;
	    }
	    
	    return false;
	  }
	  
	  public class Run1 implements Runnable {
	    public Run1() {}
	    
	    public void run() {
	      for (int i = 0; i < 715827882; i++) {
	        Judge.notObject(Judge.map);
	      }
	      System.out.println(Thread.currentThread().getName() + "用时:" + (System.currentTimeMillis() - Judge.this.starts) + "毫秒");
	    }
	  }
	  
	  public class Run2 implements Runnable {
	    public Run2() {}
	    
	    public void run() { for (int i = 0; i < 715827882; i++) {
	        Judge.notObject(Judge.map, true);
	      }
	      System.out.println(Thread.currentThread().getName() + "用时:" + (System.currentTimeMillis() - Judge.this.starts) + "毫秒");
	    }
	  }

}
