package com.searchclient.clientwrapper.domain.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

class DummyClassTest {

	@MockBean
	CloseableHttpClient client;
	@InjectMocks
	DummyClass dummClass = new DummyClass();
	
	@Test
	void testExtracted() throws ClientProtocolException, IOException {
		String apiEndpoint = "http://localhost:8082/search/api/v1/911/partial6?queryField=title&searchTerm=David&startRecord=0&pageSize=5&orderBy=id&order=asc";
		HttpGet httpGet = new HttpGet(apiEndpoint);
		CloseableHttpResponse response = null;
		CloseableHttpClient client = HttpClients.createDefault();
		
		//response.getEntity();
		
		
		
		//////////////////
		
		CloseableHttpClient closeableHttpClient = mock(CloseableHttpClient.class);
		CloseableHttpResponse closeableHttpResponse = mock(CloseableHttpResponse.class);
		StatusLine statusLine = mock(StatusLine.class);
		Mockito.when(statusLine.getStatusCode()).thenReturn(200);
		Mockito.when(closeableHttpResponse.getStatusLine()).thenReturn(statusLine);
		Mockito.when(closeableHttpClient.execute(httpGet)).thenReturn(closeableHttpResponse);
		
		
		int responseStatus = closeableHttpResponse.getStatusLine().getStatusCode();
		
		JSONObject jsonObject = dummClass.extracted(httpGet, closeableHttpClient);
		
		assertEquals(responseStatus, jsonObject.get("statusCode"));
		
		/////////////////
		
		
		//fail("Not yet implemented");
	}

}
