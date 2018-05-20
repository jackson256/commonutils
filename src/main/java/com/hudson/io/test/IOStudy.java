package com.hudson.io.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class IOStudy {
	
	public static void main(String[] args){
		//String inputPath = IOStudy.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String inputPath = IOStudy.class.getResource("/").getPath();
		String fileName = "input.txt";
		BufferedReader reader = null;
		OutputStreamWriter ou = null;
		try {
			File file = new File(inputPath+fileName);
			if(!file.exists()){
				file.createNewFile();
				ou=new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
				for(int i=0;i<100;i++){
					ou.write("测试-"+i+"\r\n");
				}
				ou.flush();
			}else{
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				//InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
				String line = null;
				while((line=reader.readLine())!=null){
					System.out.println(line);
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(reader!=null)
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

}
