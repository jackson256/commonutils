package com.hudson.common.utils;

public class StringUtils {
	
	/**
	 * 
	 * @Title concat_ws
	 * @Description 
	 * @author hudson
	 * @lastmodified 2018年5月20日下午12:48:55
	 * @modifiedBy
	 */
	public static String concat_ws(String delimiter, StringBuilder builder, Object... params) {
		if (params == null)
			return "";
		if (builder == null) {
			builder = new StringBuilder();
		}
		if (delimiter == null || "null".equals(delimiter)) {
			delimiter = "";
		}
		for (int i = 0, length = params.length; i < length; i++) {
			if (i > 0)
				builder.append(delimiter);
			if (params[i] != null) {
				builder.append(params[i]);
			}
		}
		return builder.toString();
	}

}
