package com.hudson.common.utils;

import com.spreada.utils.chinese.ZHConverter;

public class ZHConverterUtil {
	
	public static void main(String[] args) {
		String txt = ZHConverter.convert("我们是测试语句", 0);
		System.out.println(txt);
	}

}

