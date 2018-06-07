/**     
 * Copyright © 2018 All rights reserved.
 *
 * @Package:org.utils.keyword
 * @author: hudson
 * @date:2018年6月6日 下午5:15:07
 */
package org.utils.keyword;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @className: AccountExt
 * @Description:
 * Externalizable 接口内部实现了 Serializable 接口，但是为其扩展了两个方法，
 * writerExternal() 方法在序列化时被自动调用，可以在其中控制序列化内容，
 * readExternal() 方法在反序列化时被自动调用，可以在其中控制反序列化的内容。
 * 如果留空，（反）序列化行为将不会保存或读取任何一个字段，所以 transient 关键字也失效。因此我们可以对（反）序列化进行控制
 * 
 * 使用 Externalizable 进行序列化时，当读取对象时，会调用被序列化类的无参构造方法去创建一个新的对象，然后再将被保存对象的字段的值分别填充到新对象中。
 * 因此，实现 Externalizable 接口的类必须要提供一个无参的构造器，且它的访问权限为 public
 * @author: hudson
 * @date: 2018年6月6日 下午5:52:11
 * @version: 1.0
 */
public class AccountExt implements Externalizable {

	private long accountId;
	private String userName;

	private transient String password;//transient 关键字只能修饰属性（变量），不能修饰方法和类。属性如果也是对象，则该对象对应的类需要实现 Serializable 接口
	private transient double balance;

	public static int staticVar;//静态变量是相对于类的，序列化会忽略静态变量

	public AccountExt() {
		super();
		System.out.println("调用了无参构造。。。");
	}

	public AccountExt(long accountId, String userName, String password, double balance) {
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
		AccountExt.staticVar = staticVar;
	}

	/* (non-Javadoc)   
	 * @param out
	 * @throws IOException   
	 * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)   
	 */  
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("调用了 writeExternal。。。");
		out.writeLong(accountId);
		out.writeObject(password);
		//out.writeDouble(balance);
	}

	/* (non-Javadoc)   
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException   
	 * @see java.io.Externalizable#readExternal(java.io.ObjectInput)   
	 */  
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("调用了 readExternal。。。");
		accountId=in.readLong();
		password=(String) in.readObject();
		balance=in.readDouble();
		
	}

}
