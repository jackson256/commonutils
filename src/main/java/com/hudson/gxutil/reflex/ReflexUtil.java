/**     
 * Copyright © 2018 All rights reserved.
 *
 * @Package:com.hudson.gxutil.reflex
 * @author: hudson
 * @date:2018年5月27日 下午1:02:23
 */
package com.hudson.gxutil.reflex;

import com.hudson.gxutil.annotation.Automatically;
import com.hudson.gxutil.judge.Judge;

/**
 * @className: ReflexUtil
 * @Description:
 * @author: hudson
 * @date: 2018年5月27日 下午1:02:23
 * @version: 1.0
 */
public final class ReflexUtil {
	public static final void automatically(Object obj, Class<?> cls) {
		java.lang.reflect.Field[] declaredFields = cls.getDeclaredFields();
		for (java.lang.reflect.Field field : declaredFields) {
			if (field.isAnnotationPresent(Automatically.class)) {
				field.setAccessible(true);
				Automatically annotation = (Automatically) field.getAnnotation(Automatically.class);
				String value = annotation.value();
				if (Judge.notTrimNull(value)) {
					try {
						Object newInstance = Class.forName(value).newInstance();
						automatically(newInstance, field.getType());
						field.set(obj, newInstance);
					} catch (IllegalArgumentException | IllegalAccessException | ClassNotFoundException
							| InstantiationException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}