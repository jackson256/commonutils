package com.hudson.designpattern.proxy.jt;

/**
 * 
 * @类描述: 静态代理演示-目标对象
 * @创建人: huhan
 * @创建时间:2018年5月19日 上午12:17:41  
 * @version V1.0
 */
public class UserDao implements IUserDao{

	@Override
	public void save() {
		System.out.println("saving data...");
	}

}
