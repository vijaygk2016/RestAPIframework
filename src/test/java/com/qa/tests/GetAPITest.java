package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;



public class GetAPITest extends TestBase {
	TestBase testBase;
	RestClient restClinet;

	String serviceUrl;
	String apiUrl;
	String url;
	
	CloseableHttpResponse closebleHttpResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException, JSONException {
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");

		url = serviceUrl + apiUrl;

	}

	@Test(priority=1)
	public void getTestWithOutHeaders() throws ClientProtocolException, IOException, JSONException {

		restClinet = new RestClient();
		closebleHttpResponse = restClinet.get(url);
		
		int statusCode = closebleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("statusCode : " + statusCode);
		
		Assert.assertEquals(statusCode,RESPONE_STATUS_CODE_200,"Status Code worrong");

		String responseString = EntityUtils.toString(closebleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("responseJson API --> : " + responseJson);
		
		
		String perPageValue= TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("perPageValue --> : " + perPageValue);
		
		Assert.assertEquals(Integer.parseInt(perPageValue), 3," perPageValue missmatch");
		
		String perTotalValue= TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("perTotalValue --> : " + perTotalValue);

		Assert.assertEquals(Integer.parseInt(perTotalValue), 12," perPageValue missmatch");
		
		String id= TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String last_name= TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String avatar= TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String first_name= TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println("id --> : " + id);
		System.out.println("last_name --> : " + last_name);
		System.out.println("avatar --> : " + avatar);
		System.out.println("first_name --> : " + first_name);
		
		Header[] headersArray = closebleHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		
		System.out.println("Headers Array  --> : " + allHeaders);

	}
	
	@Test(priority=2)
	public void getTestWithHeader() throws ClientProtocolException, IOException, JSONException {

		restClinet = new RestClient();
		
		HashMap<String,String> headerMap = new HashMap<String,String>();
		
		headerMap.put("Content-Type", "application/json");
		
		closebleHttpResponse = restClinet.get(url,headerMap);
		
		int statusCode = closebleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("statusCode : " + statusCode);
		
		Assert.assertEquals(statusCode,RESPONE_STATUS_CODE_200,"Status Code worrong");

		String responseString = EntityUtils.toString(closebleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("responseJson API --> : " + responseJson);
		
		
		String perPageValue= TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("perPageValue --> : " + perPageValue);
		
		Assert.assertEquals(Integer.parseInt(perPageValue), 3," perPageValue missmatch");
		
		String perTotalValue= TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("perTotalValue --> : " + perTotalValue);

		Assert.assertEquals(Integer.parseInt(perTotalValue), 12," perPageValue missmatch");
		
		String id= TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String last_name= TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String avatar= TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String first_name= TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println("id --> : " + id);
		System.out.println("last_name --> : " + last_name);
		System.out.println("avatar --> : " + avatar);
		System.out.println("first_name --> : " + first_name);
		
		Header[] headersArray = closebleHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		
		System.out.println("Headers Array  --> : " + allHeaders);

	}
	
	

}
