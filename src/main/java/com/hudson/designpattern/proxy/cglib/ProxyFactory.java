package com.hudson.designpattern.proxy.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/*
静态代理和动态代理模式都是要求目标对象是实现一个接口的目标对象,但是有时候目标对象只是一个单独的对象,并没有实现任何的接口,这个时候就可以使用以目标对象子类的方式类实现代理,这种方法就叫做:Cglib代理.
Cglib代理,也叫作子类代理,它是在内存中构建一个子类对象从而实现对目标对象功能的扩展.
JDK的动态代理有一个限制,就是使用动态代理的对象必须实现一个或多个接口,如果想代理没有实现接口的类,就可以使用Cglib实现.
Cglib是一个强大的高性能的代码生成包,它可以在运行期扩展java类与实现java接口.它广泛的被许多AOP的框架使用,例如Spring AOP和synaop,为他们提供方法的interception(拦截)
*/
/**
 * Cglib子类代理工厂 对UserDao在内存中动态构建一个子类对象
 */
public class ProxyFactory implements MethodInterceptor {

	private Object target;

	public ProxyFactory() {
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public ProxyFactory(Object target) {
		this.target = target;
	}

	//给目标对象创建一个代理对象
	public Object getProxyInstance() {
		//1.工具类
		Enhancer en = new Enhancer();
		//2.设置父类
		en.setSuperclass(target.getClass());
		//3.设置回调函数
		en.setCallback(this);
		//4.创建子类(代理对象)
		return en.create();
	}

	@Override
	public Object intercept(Object object, Method method, Object[] args, MethodProxy arg3) throws Throwable {
		System.out.println("do something before saving data-3");
		//执行目标对象的方法
		Object returnVal = method.invoke(target, args);
		System.out.println("do something after saving data-3");
		return returnVal;
	}

}
