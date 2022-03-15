package com.searchclient.clientwrapper.domain.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.Map;

import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class HttpGatewayUtilTest {

	HttpGatewayUtil gatewayUtil = new HttpGatewayUtil();
		
	String apiEndpoint = "http://localhost:8082/search/api/v1/911/partial6?queryField=title&searchTerm=David&startRecord=0&pageSize=5&orderBy=id&order=asc";
	HttpGet httpGet = new HttpGet(apiEndpoint);
	HttpGet httpGetSetUp = new HttpGet();
	CloseableHttpClient httpClientSetUp = HttpClients.createDefault();

	public void setUpExtracted(HttpGet httpGetPrepared, CloseableHttpClient httpClientPrepared) {
		httpGetSetUp = httpGetPrepared;
		httpClientSetUp = httpClientPrepared;
	}
	
	/**
	 * Test method for {@link com.searchclient.clientwrapper.domain.utils.MicroserviceHttpGateway#getRequest()}.
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@Test
	void testExtracted() throws ClientProtocolException, IOException {
		
		CloseableHttpClient closeableHttpClient = mock(CloseableHttpClient.class);
		CloseableHttpResponse closeableHttpResponse = mock(CloseableHttpResponse.class);
		StatusLine statusLine = mock(StatusLine.class);
		Mockito.when(statusLine.getStatusCode()).thenReturn(200);
		Mockito.when(closeableHttpResponse.getStatusLine()).thenReturn(statusLine);
		Mockito.when(closeableHttpClient.execute(httpGet)).thenReturn(closeableHttpResponse);
		
		int responseStatus = closeableHttpResponse.getStatusLine().getStatusCode();
		JSONObject jsonObject = gatewayUtil.extracted(httpGet, closeableHttpClient, Mockito.anyString());
		
		assertEquals(responseStatus, jsonObject.get("statusCode"));
	}


	@Test
	void testPrepareHttpClient() {
		Map<String, Object> httpMap = gatewayUtil.prepareHttpClient(apiEndpoint);
		HttpGet actualHttpGet = (HttpGet)httpMap.get("httpGet");
		assertEquals(httpGet.getMethod(), actualHttpGet.getMethod());
	}

}
