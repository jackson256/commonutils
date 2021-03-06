package com.hudson.designpattern.proxy.jt;

/**
 * 代理(Proxy)是一种设计模式,提供了对目标对象另外的访问方式,即通过代理对象访问目标对象.
 * 这样做的好处是:可以在目标对象实现的基础上,增强额外的功能操作,即扩展目标对象的功能.
 * 这里使用到编程中的一个思想:不要随意去修改别人已经写好的代码或者方法,如果需改修改,可以通过代理的方式来扩展该方法.
 * @类描述: 代理模式演示 -接口
 * @创建人: huhan
 * @创建时间:2018年5月19日 上午12:17:15  
 * @version V1.0
 */
public interface IUserDao {
	
	public void save();

}
