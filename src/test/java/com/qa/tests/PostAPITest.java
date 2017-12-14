package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;
import com.qa.util.TestUtil;

public class PostAPITest extends TestBase {

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

	@Test(priority = 1)
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException, JSONException {

		restClinet = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		ObjectMapper mapper =new ObjectMapper();
		Users users = new Users("Laxman","qa");
		
		mapper.writeValue(new File("/Users/laxman_kouja/Documents/WorkSpace/restapi/src/main/java/com/qa/data/user.json"), users);
		String userString = mapper.writeValueAsString(users);
		
		System.out.println("userString  --> : " + userString);
		
		closebleHttpResponse = restClinet.post(url, userString, headerMap);
		
		int statusCode = closebleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("statusCode : " + statusCode);
		
		Assert.assertEquals(statusCode,RESPONE_STATUS_CODE_201,"Status Code worrong");
		
		String responseString = EntityUtils.toString(closebleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("responseJson API --> : " + responseJson);
	
		String perNameValue= TestUtil.getValueByJPath(responseJson, "/name");
		System.out.println("perNameValue --> : " + perNameValue);
		
		Users UserResObj = mapper.readValue(responseString, Users.class);
		System.out.println("UserResObj --> : " + UserResObj);
		
		Assert.assertTrue(users.getName().equals(UserResObj.getName()));
		Assert.assertTrue(users.getJob().equals(UserResObj.getJob()));
		
		
	}

}
