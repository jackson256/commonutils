/**     
 * Copyright © 2018 All rights reserved.
 *
 * @Package:org.utils.keyword
 * @author: hudson
 * @date:2018年6月6日 下午5:22:18
 */
package org.utils.keyword;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * @className: Demo01
 * @Description:
 * @author: hudson
 * @date: 2018年6月6日 下午5:22:18
 * @version: 1.0
 */
public class Demo01 {

	public static void main(String[] args) {
		String filePath = "E:\\temp\\account.txt";
		Account kingcos = new Account(62278888L, "kingcos02", "123456", 1000.0);
		Account.staticVar = 11;
		System.out.println("序列化之前：");
		System.out.println(kingcos);
		System.out.println("staticVar = " + Account.staticVar);

		write(kingcos, filePath);
		Account.staticVar = 22;
		Account newKingcos = read(filePath);
		System.out.println("序列化之后：");
		System.out.println(newKingcos);
		System.out.println("staticVar = " + Account.staticVar);
	}

	/**
	 * @Title read
	 * @Description
	 * @author hudson
	 * @lastmodified 2018年6月6日下午5:30:19
	 * @modifiedBy
	 */
	private static Account read(String filePath) {
		ObjectInputStream ois = null;
		Account acc = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(filePath));
			acc = (Account) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (ois != null)
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return acc;
	}

	/**
	 * @Title write
	 * @Description
	 * @author hudson
	 * @lastmodified 2018年6月6日下午5:25:03
	 * @modifiedBy
	 */
	private static void write(Account kingcos, String filePath) {
		OutputStream out = null;
		ObjectOutputStream output = null;
		try {
			out = new FileOutputStream(filePath);
			output = new ObjectOutputStream(out);
			output.writeObject(kingcos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (output != null) {
					output.flush();
					output.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
