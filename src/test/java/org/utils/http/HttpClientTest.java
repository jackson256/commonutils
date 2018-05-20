package org.utils.http;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import com.hudson.http.request.HttpRequestBuild;

public class HttpClientTest {
	
	public static void main(String[] args) throws ParseException, IOException {
		String url = "http://mi.gdt.qq.com/gdt_mview.fcg";
		Map<String,Object> headers = new HashMap<String, Object>();
		headers.put("Charset", "UTF-8");
		headers.put("contentType", "application/json");
		headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/39.0.0.0 Mobile Safari/537.36");
		headers.put("X-Forwarded-For", "10.200.100.152");
		headers.put("Referer","");
		headers.put("Host", "toutiao.eastday.com");
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("datafmt", "json");
		params.put("count", 1);
		params.put("charset", "utf8");
		params.put("posid", "7010526535918684");
		params.put("adposcount", 1);
		String ext = "{\"req\":{\"useragent\":\"Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/39.0.0.0 Mobile Safari/537.36\",\"conn\":1,\"apiver\":\"2.0\",\"muidtype\":\"1\",\"appid\":\"1106317313\",\"lng\":121624393,\"c_device\":\"Nexus 5\",\"remoteip\":\"10.200.100.152\",\"muid\":\"c87ea8f952df66ce700059a766d68cca\",\"coordtime\":1525748498132,\"postype\":8,\"c_osver\":\"5.1.1\",\"carrier\":1,\"referer\":\"null\",\"c_pkgname\":\"com.songheng.eastnews\",\"c_os\":\"android\",\"lat\":31205093}}";
		ext=URLEncoder.encode(ext,"UTF-8");
		params.put("ext", ext);
		HttpResponse response = new HttpRequestBuild(url).setHeaders(headers).setParams(params).doGet();
		String result = EntityUtils.toString(response.getEntity(), "utf-8");
		System.out.println(result);
	}

}
