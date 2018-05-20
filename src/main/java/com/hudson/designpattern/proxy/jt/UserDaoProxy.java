package com.hudson.designpattern.proxy.jt;
/*
静态代理总结:
1.可以做到在不修改目标对象的功能前提下,对目标功能扩展.
2.缺点:
因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类,类太多.
同时,一旦接口增加方法,目标对象与代理对象都要维护.
*/
/**
 * 
 * @类描述: 静态代理演示-代理对象  
 * @创建人: huhan  
 * @创建时间:2018年5月19日 上午12:27:58  
 * @version V1.0
 */
public class UserDaoProxy implements IUserDao {

	private IUserDao target;//接受目标对象

	public UserDaoProxy(){}
	
	public UserDaoProxy(IUserDao target) {
		this.target = target;
	}

	public IUserDao getTarget() {
		return target;
	}

	public void setTarget(IUserDao target) {
		this.target = target;
	}

	@Override
	public void save() {
		System.out.println("do something before saving data");
		System.out.println("saving data...");
		System.out.println("do something after saving data");

	}

}
