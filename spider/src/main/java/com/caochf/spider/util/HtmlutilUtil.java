package com.caochf.spider.util;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;


public class HtmlutilUtil {
	/**
	 * 获取url对应的页面信息，返回结果中key有两个：content-页面html的字符串信息，contentType-页面编码规范.
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws FailingHttpStatusCodeException 
	 */
	public static Map<String,String> getStrByUrl(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		Map<String,String> resultMap = new HashMap<String,String>();
		WebClient mWebClient = new WebClient();//new WebClient();
		Page page = mWebClient.getPage(url);
		WebResponse response = page.getWebResponse();
		String contentType = response.getContentType();
		String content = response.getContentAsString();
		resultMap.put("content", content);
		resultMap.put("contentType", contentType);
		mWebClient.close();
		return resultMap;
	}

}
