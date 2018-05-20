package com.hudson.common.utils;

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

}
