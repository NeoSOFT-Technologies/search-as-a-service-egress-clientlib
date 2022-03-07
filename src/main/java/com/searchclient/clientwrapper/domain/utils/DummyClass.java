package com.searchclient.clientwrapper.domain.utils;

import java.io.IOException;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class DummyClass {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		DummyClass dummyClass = new DummyClass();
		
		String apiEndpoint = "http://localhost:8082/search/api/v1/911/partial6?queryField=title&searchTerm=David&startRecord=0&pageSize=5&orderBy=id&order=asc";
		HttpGet http = new HttpGet(apiEndpoint);
		CloseableHttpClient client = HttpClients.createDefault();
		
		dummyClass.extracted(http, client);
	}

	public JSONObject extracted(HttpGet http, CloseableHttpClient client) throws IOException, ClientProtocolException {

		CloseableHttpResponse response = client.execute(http);
		Optional<HttpEntity> entityResponse = Optional.ofNullable(response.getEntity());
		JSONObject jsonObjectResult = new JSONObject();
		
		String result = "{\"statusCode\":\"0\"}";
		if(entityResponse.isPresent()) {
			result = EntityUtils.toString(entityResponse.get());
			System.out.println("RESPONSE: " + result);
		}

		client.close();
		
		jsonObjectResult = new JSONObject(result);
		jsonObjectResult.put("statusCode", response.getStatusLine().getStatusCode());
		return jsonObjectResult;
	}
}
