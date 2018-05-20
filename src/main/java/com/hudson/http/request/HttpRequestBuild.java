package com.hudson.http.request;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class HttpRequestBuild {
	
	/**
	 * 连接超时时间
	 */
	public static final int CONNECTION_TIMEOUT_MS = 3000;

	/**
	 * 读取数据超时时间
	 */
	public static final int SO_TIMEOUT_MS = 3000;

	/**
	 * 连接请求超时
	 */
	public static final int CONNECTION_REQ_TIMEOUT_MS = 3000;

	
	private String url;
	
	public HttpRequestBuild(String url){
		this.url = url;
	}
	
	private Map<String, Object> headers;
	
	public HttpRequestBuild setHeaders(Map<String, Object> headers){
		this.headers = headers;
		return this;
	}
	
	private Map<String,Object> params;
	
	public HttpRequestBuild setParams(Map<String,Object> params){
		this.params = params;
		return this;
	}
	
	public HttpResponse doGet(){
		HttpClient client = HttpClients.custom().setDefaultRequestConfig(buildRequestConfig()).build();
		HttpGet request = new HttpGet();
		if(headers!=null && headers.size()>0){
			for (Entry<String, Object> headerEntry : headers.entrySet()) {
				request.setHeader(headerEntry.getKey(), String.valueOf(headerEntry.getValue()));
			}
		}
		if(params!=null && params.size()>0){
			StringBuilder paramsStr = new StringBuilder();
			if(!url.endsWith("?")){
				paramsStr.append("?");
			}
			for (Entry<String, Object> paramEntry : params.entrySet()) {
				paramsStr.append(paramEntry.getKey()).append("=").append(paramEntry.getValue()).append("&");
			}
			paramsStr.deleteCharAt(paramsStr.lastIndexOf("&"));
			url = url + paramsStr.toString();
		}
		try {
			request.setURI(new URI(url));
			HttpResponse response = client.execute(request);
			return response;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	RequestConfig buildRequestConfig() {
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(SO_TIMEOUT_MS)
				.setConnectTimeout(CONNECTION_TIMEOUT_MS)
				.setConnectionRequestTimeout(CONNECTION_REQ_TIMEOUT_MS)
				.setStaleConnectionCheckEnabled(true).build();
		return requestConfig;
	}
	
	

}
