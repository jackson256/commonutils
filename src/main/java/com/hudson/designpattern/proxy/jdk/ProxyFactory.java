package com.hudson.designpattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理不需要实现接口,但是需要指定接口类型
 * 代理对象不需要实现接口,但是目标对象一定要实现接口,否则不能用动态代理
 * @类描述: 动态代理演示 -代理工厂类
 * @创建人: huhan
 * @创建时间:2018年5月19日 上午12:38:02
 * @version V1.0
 */
public class ProxyFactory {

	private Object target;

	public ProxyFactory() {
	}

	public ProxyFactory(Object target) {
		super();
		this.target = target;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	//目标对象生成代理对象
	public Object getProxyInstance() {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("do something before saving data");
						Object returnVal = method.invoke(target, args);
						System.out.println("do something after saving data");
						return returnVal;
					}

				});
	}

}
