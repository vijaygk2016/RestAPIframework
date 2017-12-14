package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RestClient {

	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException, JSONException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);

		CloseableHttpResponse closebleHttpResponse = httpClient.execute(httpget);
		return closebleHttpResponse;

	}
	
	public CloseableHttpResponse get(String url, HashMap<String,String> headerMap) throws ClientProtocolException, IOException, JSONException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		
		for(Map.Entry<String,String> entry : headerMap.entrySet()){
			httpget.addHeader(entry.getKey(),entry.getValue());
		}

		CloseableHttpResponse closebleHttpResponse = httpClient.execute(httpget);
		return closebleHttpResponse;

	}

	public CloseableHttpResponse post(String url, String entityString, HashMap<String,String> headerMap) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(new StringEntity(entityString));
		
		for(Map.Entry<String,String> entry : headerMap.entrySet()){
			httppost.addHeader(entry.getKey(),entry.getValue());
		}
		
		CloseableHttpResponse closebleHttpResponse = httpClient.execute(httppost);
		
		return closebleHttpResponse;
		
	}
	
}
