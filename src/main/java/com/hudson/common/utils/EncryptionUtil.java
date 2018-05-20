package com.hudson.common.utils;

import java.io.IOException;
import java.security.MessageDigest;


public abstract class EncryptionUtil {
	public static void main(String[] args) {  
	    String test = "最代码网站地址:www.zuidaima.com";  
	   
	    String unicode = Unicode.string2Unicode(test);  
	       
	    String string = Unicode.unicode2String("\u6700\u4ee3\u7801\u7f51\u7ad9\u5730\u5740\u003a\u0077\u0077\u0077\u002e\u007a\u0075\u0069\u0064\u0061\u0069\u006d\u0061\u002e\u0063\u006f\u006d") ;  
	       
	    System.out.println(unicode);  
	       
	    System.out.println(string);  
	   
	}  
    
	/**
	 * Base64 编码和解码。
	 */
	public static class Base64 {


		/**
		 * 功能：编码字符串
		 */
		public static String encode(String data) {
			return new String(encode(data.getBytes()));
		}

		/**
		 * 功能：解码字符串
		 * @throws IOException 
		 */
		public static String decode(String data) throws IOException {
			return new String(decode(data.toCharArray()),"utf-8");
		}

		/**
		 * 功能：编码byte[]
		 */
		public static char[] encode(byte[] data) {
			char[] out = new char[((data.length + 2) / 3) * 4];
			for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
				boolean quad = false;
				boolean trip = false;

				int val = (0xFF & (int) data[i]);
				val <<= 8;
				if ((i + 1) < data.length) {
					val |= (0xFF & (int) data[i + 1]);
					trip = true;
				}
				val <<= 8;
				if ((i + 2) < data.length) {
					val |= (0xFF & (int) data[i + 2]);
					quad = true;
				}
				out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
				val >>= 6;
				out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
				val >>= 6;
				out[index + 1] = alphabet[val & 0x3F];
				val >>= 6;
				out[index + 0] = alphabet[val & 0x3F];
			}
			return out;
		}

		/**
		 * 功能：解码
		 */
		public static byte[] decode(char[] data) {

			int tempLen = data.length;
			for (int ix = 0; ix < data.length; ix++) {
				if ((data[ix] > 255) || codes[data[ix]] < 0) {
					--tempLen; // ignore non-valid chars and padding
				}
			}

			int len = (tempLen / 4) * 3;
			if ((tempLen % 4) == 3) {
				len += 2;
			}
			if ((tempLen % 4) == 2) {
				len += 1;

			}
			byte[] out = new byte[len];

			int shift = 0; // # of excess bits stored in accum
			int accum = 0; // excess bits
			int index = 0;

			// we now go through the entire array (NOT using the 'tempLen' value)
			for (int ix = 0; ix < data.length; ix++) {
				int value = (data[ix] > 255) ? -1 : codes[data[ix]];

				if (value >= 0) { // skip over non-code
					accum <<= 6; // bits shift up by 6 each time thru
					shift += 6; // loop, with new bits being put in
					accum |= value; // at the bottom.
					if (shift >= 8) { // whenever there are 8 or more shifted in,
						shift -= 8; // write them out (from the top, leaving any
						out[index++] = // excess at the bottom for next iteration.
						(byte) ((accum >> shift) & 0xff);
					}
				}
			}

			// if there is STILL something wrong we just have to throw up now!
			if (index != out.length) {
				throw new Error("Miscalculated data length (wrote " + index
						+ " instead of " + out.length + ")");
			}

			return out;
		}

		//
		// code characters for values 0..63
		//
		private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
				.toCharArray();

		//
		// lookup table for converting base64 characters to value in range 0..63
		//
		private static byte[] codes = new byte[256];
		static {
			for (int i = 0; i < 256; i++) {
				codes[i] = -1;
				// LoggerUtil.debug(i + "&" + codes[i] + " ");
			}
			for (int i = 'A'; i <= 'Z'; i++) {
				codes[i] = (byte) (i - 'A');
				// LoggerUtil.debug(i + "&" + codes[i] + " ");
			}

			for (int i = 'a'; i <= 'z'; i++) {
				codes[i] = (byte) (26 + i - 'a');
				// LoggerUtil.debug(i + "&" + codes[i] + " ");
			}
			for (int i = '0'; i <= '9'; i++) {
				codes[i] = (byte) (52 + i - '0');
				// LoggerUtil.debug(i + "&" + codes[i] + " ");
			}
			codes['+'] = 62;
			codes['/'] = 63;
		}

	}
	
	public static class Unicode{
		/** 
		 * 字符串转换unicode 
		 */  
		public static String string2Unicode(String string) {  
		   
		    StringBuffer unicode = new StringBuffer();  
		   
		    for (int i = 0; i < string.length(); i++) {  
		   
		        // 取出每一个字符  
		        char c = string.charAt(i);  

		        // 转换为unicode  
		        unicode.append("\\u" + addZeroForNum(Integer.toHexString(c),4));  
		    }  
		   
		    return unicode.toString();  
		}  
		
		public static String addZeroForNum(String str, int strLength) {  
		    int strLen = str.length();  
		    if (strLen < strLength) {  
		        while (strLen < strLength) {  
		            StringBuffer sb = new StringBuffer();  
		            sb.append("0").append(str);// 左补0  
		            // sb.append(str).append("0");//右补0  
		            str = sb.toString();  
		            strLen = str.length();  
		        }  
		    }  
		  
		    return str;  
		}  
		
		/** 
		 * unicode 转字符串 
		 */  
		public static String unicode2String(String unicode) {  		   
		    StringBuffer string = new StringBuffer();  
		   
		    if(unicode.indexOf("\\\\u") == -1)return unicode;
		    
		    String[] hex = unicode.split("\\\\u");  
		   
		    for (int i = 1; i < hex.length; i++) {  
		   
		        // 转换出每一个代码点  
		        int data = Integer.parseInt(hex[i], 16);  
		   
		        // 追加成string  
		        string.append((char) data);  
		    }  
		   
		    return string.toString();  
		}  
		  
		
	}
	
    
	public static class MD5 extends EncryptionUtil{
		public static String encode(String content) {
            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                digest.update(content.getBytes());
                return getEncode16(digest);
                //return getEncode32(digest);
            } catch (Exception e) {
            	e.printStackTrace();
            }
            return null;
	    }
	    /**
	    * 16位加密
	    * @param digest
	    * @return
	    */
	    private static String getEncode16(MessageDigest digest) {
            StringBuilder builder = new StringBuilder();
            for (byte b : digest.digest()) {
                builder.append(Integer.toHexString((b >> 4) & 0xf));
                builder.append(Integer.toHexString(b & 0xf));
            }
            // 16位加密，从第9位到25位  
//            return builder.substring(8, 24).toString().toUpperCase();  
            return builder.toString();  
	    }
	}
	
	public static class SHA {  
	    /** 
	     * 定义加密方式 
	     */  
	    private final static String KEY_SHA = "SHA";  
	    private final static String KEY_SHA1 = "SHA-1";  
	    /** 
	     * 全局数组 
	     */  
	    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",  
	            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };  
	  
	  
	    /** 
	     * SHA 加密 
	     * @param data 需要加密的字节数组 
	     * @return 加密之后的字节数组 
	     * @throws Exception 
	     */  
	    public static byte[] encryptSHA(byte[] data) throws Exception {  
	        // 创建具有指定算法名称的信息摘要  
//	        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);  
	        MessageDigest sha = MessageDigest.getInstance(KEY_SHA1);  
	        // 使用指定的字节数组对摘要进行最后更新  
	        sha.update(data);  
	        // 完成摘要计算并返回  
	        return sha.digest();  
	    }  
	  
	    /** 
	     * SHA 加密 
	     * @param data 需要加密的字符串 
	     * @return 加密之后的字符串 
	     * @throws Exception 
	     */  
	    public static String encryptSHA(String data) throws Exception {  
	        // 验证传入的字符串  
	        if (OpUtils.checkEmpty(data)) {  
	            return "";
	        }  
	        // 创建具有指定算法名称的信息摘要  
//	        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);  
	        MessageDigest sha = MessageDigest.getInstance(KEY_SHA1);  
	        // 使用指定的字节数组对摘要进行最后更新  
	        sha.update(data.getBytes());  
	        // 完成摘要计算  
	        byte[] bytes = sha.digest();  
	        // 将得到的字节数组变成字符串返回  
	        return byteArrayToHexString(bytes);  
	    }  
	  
	    /** 
	     * 将一个字节转化成十六进制形式的字符串 
	     * @param b 字节数组 
	     * @return 字符串 
	     */  
	    private static String byteToHexString(byte b) {  
	        int ret = b;  
	        //System.out.println("ret = " + ret);  
	        if (ret < 0) {  
	            ret += 256;  
	        }  
	        int m = ret / 16;  
	        int n = ret % 16;  
	        return hexDigits[m] + hexDigits[n];  
	    }  
	  
	    /** 
	     * 转换字节数组为十六进制字符串 
	     * @param bytes 字节数组 
	     * @return 十六进制字符串 
	     */  
	    private static String byteArrayToHexString(byte[] bytes) {  
	        StringBuffer sb = new StringBuffer();  
	        for (int i = 0; i < bytes.length; i++) {  
	            sb.append(byteToHexString(bytes[i]));  
	        }  
	        return sb.toString();  
	    }  
	  
	    
	}
	
}
