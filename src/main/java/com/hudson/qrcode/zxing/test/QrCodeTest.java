package com.hudson.qrcode.zxing.test;

import com.google.zxing.Result;
import com.hudson.qrcode.zxing.util.ZxingUtils;
//https://en.wikipedia.org/wiki/VCard 制作名片
public class QrCodeTest {
	
	 public static void main(String[] args) {  
        String imgPath = "E:/zxing.png";  
        /*String contents = "https://www.cnblogs.com/chenmo-xpw/p/5923741.html";
        int width = 300, height = 300;
        ZxingUtils.encode(contents, width, height, imgPath); */
        
        //
        Result result = ZxingUtils.decode(imgPath);
        System.out.println(result.getText());
        System.out.println(result.getBarcodeFormat());
        System.out.println(result.toString());
    } 

}
