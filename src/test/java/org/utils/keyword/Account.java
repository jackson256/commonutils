/**     
 * Copyright © 2018 All rights reserved.
 *
 * @Package:org.utils.keyword
 * @author: hudson
 * @date:2018年6月6日 下午5:15:07
 */
package org.utils.keyword;

import java.io.Serializable;

/**
 * @className: Account
 * @Description:Serializable transient 关键字Demo
 * @author: hudson
 * @date: 2018年6月6日 下午5:15:07
 * @version: 1.0
 */
public class Account implements Serializable {

	/**
	 * @Fields: serialVersionUID Java 的序列化机制通过判断以下 ID 来进行版本比对，本处使用默认
	 * 序列化时系统会把当前类的 serialVersionUID 写入序列化的文件中（也可能是其他的中介），当反序列化的时候系统会去检测文件中的 serialVersionUID，
	 * 看它是否和当前类的 serialVersionUID 一致，如果一致就说明序列化的类的版本和当前类的版本是相同的，
	 * 这个时候可以成功反序列化，否则就说明当前类和序列化的类相比发生了某些变化
	 */
	private static final long serialVersionUID = 1L;

	private long accountId;
	private String userName;

	private transient String password;//transient 关键字只能修饰属性（变量），不能修饰方法和类。属性如果也是对象，则该对象对应的类需要实现 Serializable 接口
	private transient double balance;

	public static int staticVar;//静态变量是相对于类的，序列化会忽略静态变量

	public Account() {
	}

	public Account(long accountId, String userName, String password, double balance) {
		super();
		this.accountId = accountId;
		this.userName = userName;
		this.password = password;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", userName=" + userName + ", password=" + password + ", balance="
				+ balance + "]";
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public static int getStaticVar() {
		return staticVar;
	}

	public static void setStaticVar(int staticVar) {
		Account.staticVar = staticVar;
	}

}
