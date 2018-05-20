package com.hudson.qrcode.zxing.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
/**
 * base:https://github.com/zxing/
 * @类描述: 基于Zxing二维码生成、解码工具类
 * @创建人: huhan
 * @创建时间:2018年4月25日 下午1:23:08  
 * @version V1.0
 */
public class ZxingUtils {
	/**
	 * 
	 * @Title: encode   
	 * @Description: TODO  
	 * @param @param content
	 * @param @param width
	 * @param @param height
	 * @param @param imgPath  
	 * @return void  
	 * @throws
	 */
	public static void encode(String content, int width, 
			int height, String imgPath){
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, buildHints());
			MatrixToImageWriter.writeToPath(bitMatrix, "png", new File(imgPath).toPath());
		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static HashMap<EncodeHintType, Object> buildHints(){
		HashMap<EncodeHintType,Object> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		hints.put(EncodeHintType.MARGIN, 2);
		return hints;
	}
	
	/**
     * @param imgPath
     * @return String
     */
    public static Result decode(String imgPath) {
        BufferedImage image = null;
        Result result = null;
        try {
            image = ImageIO.read(new File(imgPath));
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            
            result = new MultiFormatReader().decode(bitmap);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
