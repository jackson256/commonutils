package com.hudson.designpattern.proxy.cglib;

import com.hudson.designpattern.proxy.jt.UserDao;

public class AppTest {

	public static void main(String[] args) {
		// 目标对象
		UserDao target = new UserDao();
		// 代理对象
		UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();
		// 执行方法   【代理对象】
		proxy.save();
	}

}
