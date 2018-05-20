package com.hudson.designpattern.proxy.jdk;

import com.hudson.designpattern.proxy.jt.IUserDao;
import com.hudson.designpattern.proxy.jt.UserDao;

public class AppTest {

	public static void main(String[] args) {
		// 目标对象
		IUserDao target = new UserDao();
		// com.hudson.designpattern.proxy.jt.UserDao
		System.out.println(target.getClass());
		// 给目标对象，创建代理对象
		IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
		// com.sun.proxy.$Proxy0
		System.out.println(proxy.getClass());
		// 执行方法   【代理对象】
		proxy.save();
	}

}
