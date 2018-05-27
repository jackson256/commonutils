package com.hudson.io.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @类描述: IO工具类  
 * @创建人: hudson 
 * @创建时间:2018年5月18日 下午2:06:18  
 * @version V1.0
 */
public class IOUtils {

	// HashMap
	static HashMap<String, String> readConfig(File file) throws IOException {
		FileInputStream fileIn = new FileInputStream(file);
		InputStreamReader isReader = new InputStreamReader(fileIn, "utf-8");
		BufferedReader bufReader = new BufferedReader(isReader);
		HashMap<String, String> art = new HashMap<String, String>();
		String line;
		while ((line = bufReader.readLine()) != null) {
			String[] word = line.split(",");
			art.put(word[0], word[1]);
		}
		bufReader.close();
		return art;
	}

	// Set
	static Set<String> readCorpus(File file) throws IOException {
		FileInputStream fileIn = new FileInputStream(file);
		InputStreamReader isReader = new InputStreamReader(fileIn, "utf-8");
		BufferedReader bufReader = new BufferedReader(isReader);
		Set<String> articleTextList = new HashSet<String>();
		String line;
		while ((line = bufReader.readLine()) != null) {
			articleTextList.add(line);
		}
		bufReader.close();
		return articleTextList;
	}

}
