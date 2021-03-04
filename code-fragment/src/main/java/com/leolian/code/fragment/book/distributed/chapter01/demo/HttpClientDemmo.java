package com.leolian.code.fragment.book.distributed.chapter01.demo;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

public class HttpClientDemmo {

	public static void main(String[] args) throws Exception {
		String url = "https://www.baidu.com";
		
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			HttpGet request = new HttpGet(url);
			CloseableHttpResponse response = null;
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(120 * 1000)
					.setConnectTimeout(120 * 1000)
					.build();
			request.setConfig(requestConfig);
			request.addHeader("Content-Type", "application/json;charset=utf-8");
			
			response = httpClient.execute(request, new BasicHttpContext());
			if (response.getStatusLine().getStatusCode() != 200) {
				System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode() + ", url=" + url+", entity="+response.getEntity());
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String resultStr = EntityUtils.toString(entity, "UTF-8");
				System.out.println(resultStr);
			}
		} catch (Exception e) {
			System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
			e.printStackTrace();
		}
	}

}
