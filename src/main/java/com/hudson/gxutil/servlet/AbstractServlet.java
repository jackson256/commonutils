/**     
 * Copyright © 2018 All rights reserved.
 *
 * @Package:com.hudson.gxutil.servlet
 * @author: huhan
 * @date:2018年5月27日 下午1:03:43
 */
package com.hudson.gxutil.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hudson.gxutil.reflex.ReflexUtil;

/**
 * @className: AbstractServlet
 * @Description:
 * @author: hudson
 * @date: 2018年5月27日 下午1:03:43
 * @version: 1.0
 */
public abstract class AbstractServlet extends HttpServlet {
	private static final long serialVersionUID = 6666666666666666666L;
	protected static final char APPENDCHAR = '\t';
	protected static String charcode = "UTF-8";

	public String version = "0.0.0";

	protected AbstractServlet() {
		ReflexUtil.automatically(this, getClass());
	}

	protected abstract void doGet(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws ServletException, IOException;

	protected abstract void doPost(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws ServletException, IOException;

	protected String getGzipParams(HttpServletRequest request) throws IOException {
		ServletInputStream input = null;
		GZIPInputStream gzipin = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			input = request.getInputStream();
			gzipin = new GZIPInputStream(input, 1024);
			String header = request.getHeader("Content-Encoding");
			if ((header == null) || (header.indexOf("gzip") == -1))
				return null;
			byte[] bytes = new byte[1024];
			for (int i = -1; (i = gzipin.read(bytes, 0, 1024)) > -1; out.write(bytes, 0, i)) {
			}
			return out.toString(charcode);
		} catch (IOException e) {
			throw e;
		} finally {
			if (gzipin != null) {
				gzipin.close();
			}
			if (input != null) {
				input.close();
			}
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	protected void result(HttpServletRequest request, HttpServletResponse response, JSON json) throws IOException {
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}

	protected void resultJsonp(HttpServletRequest request, HttpServletResponse response, String jsonpcallback,
			Map<?, ?> json) throws IOException {
		PrintWriter out = response.getWriter();
		String jsonp = request.getParameter(jsonpcallback);
		if (jsonp != null)
			out.print(jsonp + "(" + json + ")");
		else
			out.print(json);
		out.flush();
		out.close();
	}

	protected void resultGzipJsonp(HttpServletRequest request, HttpServletResponse response, String jsonpcallback,
			Map<?, ?> json) throws IOException {
		GZIPOutputStream gzipout = new GZIPOutputStream(response.getOutputStream(), 1024);
		response.setHeader("Content-Encoding", "gzip");
		String jsonp = request.getParameter(jsonpcallback);
		if (jsonp != null)
			gzipout.write((jsonp + "(" + json + ")").getBytes(charcode));
		else
			gzipout.write(json.toString().getBytes(charcode));
		gzipout.flush();
		gzipout.close();
	}

	protected StringBuilder stitchingParameter(long now, String... strs) {
		StringBuilder builder = new StringBuilder().append(now);
		for (String value : strs)
			builder.append('\t').append(value);
		return builder;
	}

	protected String getremoteIp(String[] headers, int i, HttpServletRequest req) {
		String remoteIp = req.getHeader(headers[(i++)]);
		if ((remoteIp == null) || (remoteIp.length() <= 0) || (remoteIp.equalsIgnoreCase("Unknown"))) {
			return headers.length > i ? getremoteIp(headers, i, req) : req.getRemoteAddr();
		}
		return remoteIp;
	}
}