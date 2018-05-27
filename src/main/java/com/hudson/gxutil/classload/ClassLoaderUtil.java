/**     
 * Copyright © 2018 All rights reserved.
 *
 * @Package:com.hudson.gxutil.classload
 * @author: huhan
 * @date:2018年5月27日 下午12:43:13
 */
package com.hudson.gxutil.classload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import com.hudson.gxutil.annotation.Automatically;
import com.hudson.gxutil.servlet.AbstractServlet;

/**
 * @className: ClassLoaderUtil
 * @Description:
 * @author: hudson
 * @date: 2018年5月27日 下午12:43:13
 * @version: 1.0
 */
public class ClassLoaderUtil extends ClassLoader {

	private static final String CODE = "/";
	private static final String $CLASS = ".class";
	private ClassLoaderUtil clazz;
	private ClassLoader parent;
	boolean recursive = false;
	private String rootpath;

	public ClassLoaderUtil() {
		this.rootpath = ClassLoaderUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	}

	public ClassLoaderUtil(ClassLoader parent) {
		super(parent);
		this.parent = parent;
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		try {
			Class[] loadPackageAllClass = new ClassLoaderUtil().loadPackageAllClass("org.util.classload.Test");
			for (int i = 0; i < loadPackageAllClass.length; i++) {
				System.out.println(loadPackageAllClass[i].getName());
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	public boolean automatically(AbstractServlet servlet, String version) {
		try {
			Class<? extends Object> clazz = servlet.getClass();
			for (Field field : clazz.getDeclaredFields()) {
				field.setAccessible(true);
				if ((field.isAnnotationPresent(Automatically.class))
						&& (field.getType().toString().endsWith("java.util.Map"))) {
					Map<Object, Object> map = (Map) field.get(servlet);
					if (map == null) {
						field.set(servlet, map = new HashMap());
					} else if (map.get(version) != null) {
						return false;
					}

					Automatically annotation = (Automatically) field.getAnnotation(Automatically.class);
					String value = annotation.value();
					for (Class<?> claz : loadPackageAllClass(value)) {
						if (claz != null) {
							Field declaredField = claz.getDeclaredField("version");
							declaredField.setAccessible(true);
							Object newInstance = claz.newInstance();
							if (version.equals(declaredField.get(newInstance))) {
								map.put(version, newInstance);
							}
						}
					}
					return true;
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		return false;
	}

	public Class<?>[] loadPackageAllClass(String packagepath) throws ClassNotFoundException, IOException {
		Class[] classs = null;
		if (packagepath.endsWith(".*")) {
			String packname = packagepath.substring(0, packagepath.length() - 1);
			String replace = this.rootpath + packname.replace(".", File.separator);
			File file = new File(replace);
			if ((!file.exists()) || (!file.isDirectory())) {
				System.out.println("用户定义包名下没有任何文件");
				return null;
			}
			File[] dirfiles = file.listFiles(new FileFilter() {
				public boolean accept(File file) {
					return ((ClassLoaderUtil.this.recursive) && (file.isDirectory()))
							|| ((file.getName().endsWith(".class")) && (file.getName().indexOf("$") == -1));
				}
			});
			classs = new Class[dirfiles.length];
			int i = 0;
			for (int length = dirfiles.length; i < length; i++) {
				File fil = dirfiles[i];
				String path = fil.getPath();
				String name = fil.getName();
				name = packname + name.substring(0, name.length() - 6);
				if (!Modifier.isAbstract(Class.forName(name).getModifiers())) {
					classs[i] = forName(new FileInputStream(path), name);
				}
			}
		} else {
			classs = new Class[1];
			classs[0] = forName(packagepath);
		}
		return classs;
	}

	private Class<?> forName(FileInputStream input, String name) throws ClassNotFoundException, IOException {
		ByteArrayOutputStream bytearray = new ByteArrayOutputStream();
		byte[] bytes = new byte[1024];
		int len = -1;
		while ((len = input.read(bytes)) != -1)
			bytearray.write(bytes, 0, len);
		input.close();
		byte[] classdata = bytearray.toByteArray();
		bytearray.flush();
		bytearray.close();
		return super.defineClass(name, classdata, 0, classdata.length);
	}

	public Class<?> forName(String name) throws ClassNotFoundException, IOException {
		Class<?> cls = Class.forName(name);
		String classpath = cls.getResource("/").getPath() + name.replace(".", "/") + ".class";
		if (Modifier.isAbstract(cls.getModifiers())) {
			return null;
		}
		FileInputStream intput = new FileInputStream(classpath);
		return forName(intput, name);
	}


}

