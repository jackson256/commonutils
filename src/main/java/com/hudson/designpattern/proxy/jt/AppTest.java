package com.hudson.designpattern.proxy.jt;

/**
 * 
 * @类描述: 静态代理演示-测试类
 * @创建人: hudson
 * @创建时间:2018年5月19日 上午12:29:03  
 * @version V1.0
 */
public class AppTest {
	
	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		//代理对象,把目标对象传给代理对象,建立代理关系
		UserDaoProxy user = new UserDaoProxy(userDao);
		user.save();//执行的是代理的方法
	}

}
