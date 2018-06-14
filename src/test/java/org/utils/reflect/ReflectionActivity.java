/**     
 * Copyright © 2018 All rights reserved.
 *
 * @Package:org.utils.reflect
 * @author: hudson
 * @date:2018年6月7日 上午10:11:04
 */
package org.utils.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.utils.keyword.serializable.Account;

/** 
 * @className: ReflectionActivity
 * @Description:
 * @author: hudson
 * @date: 2018年6月7日 上午10:11:04
 * @version: 1.0
 */
public class ReflectionActivity {
	
	
	public static void main(String[] args) {
		beanReflex();
//		getMethodsInfo();
//		modifyFieldValue();
	}
	
	private static void beanReflex(){
		try {
			Class<?> account =  Class.forName("org.utils.keyword.Account");
			Account acc = (Account) account.newInstance();
			acc.setBalance(56.5);
			System.out.println(acc.getBalance());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	private static void getMethodsInfo(){
	    Class<ReflectionActivity> cls = ReflectionActivity.class;
	    Method[] methods = cls.getDeclaredMethods();
	    if (methods == null) return;

	    StringBuilder sb = new StringBuilder();
	    for (Method method:methods) {
	        sb.append(Modifier.toString(method.getModifiers())).append(" ");
	        sb.append(method.getReturnType()).append(" ");
	        sb.append(method.getName()).append("(");
	        Class[] parameters = method.getParameterTypes();
	        if (parameters != null) {
	            for (int i=0; i<parameters.length; i++) {
	                Class paramCls = parameters[i];
	                sb.append(paramCls.getSimpleName());
	                if (i < parameters.length - 1) sb.append(", ");
	            }
	        }
	        sb.append(")\n\n");
	    }
	    System.out.println("getMethodsInfo==>\n"+sb.toString());

	}
	
	private static void modifyFieldValue() {
	    Class<ReflectionActivity> cls = ReflectionActivity.class;
	    Field[] fields = cls.getDeclaredFields();
	    if (fields == null) return;

	    StringBuilder sb = new StringBuilder();
	    sb.append("获得类的所有属性信息：\n\n");
	    for (Field field:fields) {
	        sb.append(Modifier.toString(field.getModifiers())).append(" ");
	        sb.append(field.getType().getSimpleName()).append(" ");
	        sb.append(field.getName()).append(";");
	        sb.append("\n\n");
	    }

	    try {
	        sb.append("属性i的默认值：i = ");
	        Field f = cls.getDeclaredField("i");
	        sb.append(f.getInt("i")).append("\n\n");
	        f.set("i", 100);
	        sb.append("属性i修改后的值：i = ");
	        sb.append(f.getInt("i")).append("\n\n");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    System.out.println("modifyFieldValue==>\n"+sb.toString());
	}
	
	

}
