package com.hudson.common.utils;

import java.util.Random;

/**
 * 
 * @类描述:   
 * @创建人: hudson  
 * @创建时间:2018年5月18日 下午4:09:41  
 * @version V1.0
 */
public class OpUtils {
	
	/**
	 * 
	 * @Title check   
	 * @Description 判断传入值是否为【null,""】;若为空,则赋值为默认值defValue
	 * @param v
	 * @param defValue
	 */
	public static<V> V check(V v,V defValue){
		return v == null || v == "" || v == "null" || "".equals(v) ||  "null".equals(v) ? defValue : v;
	}
	/**
	 * 
	 * @Title checkEmpty
	 * @Description 判断传入值是否为空【null,""】;若为空,则返回true
	 * @param v
	 */
	public static<V> boolean checkEmpty(V v){
		return  v == null || v == "" || v == "null" || "".equals(v) ||  "null".equals(v) ? true : false;
	}
	
	/** 
     * 返回一个定长的随机纯大写字母字符串
     * @param length  随机字符串长度 
     * @return 随机字符串 
     */  
	 private static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";  
	 private static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";  
	 public static String generateMixString(String prefix,int length) {  
        StringBuffer sb = new StringBuffer();  
        Random random = new Random();  
        for (int i = 0; i < length; i++) {  
            sb.append(ALLCHAR.charAt(random.nextInt(LETTERCHAR.length())));  
        }  
        return prefix + sb.toString().toLowerCase() + String.valueOf(System.currentTimeMillis()).substring(8) + (long) (Math.random() *10);
	}
	 
	 public static void main(String[] args) {
		int num = 589;
		System.out.println("10:"+Integer.toBinaryString(num));
		System.out.println("16:"+Integer.toHexString(num));
		System.out.println("8:"+Integer.toOctalString(num));
		System.out.println(Integer.valueOf("24d", 16));
	}

}
