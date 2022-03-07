/**
 * 
 */
package com.searchclient.clientwrapper.domain.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author user
 *
 */
class MicroserviceHttpGatewayTest {

	@MockBean
	Logger logger;
	@InjectMocks
	MicroserviceHttpGateway gateway = new MicroserviceHttpGateway();
	
	String apiEndpoint = "http://localhost:8082/search/api/v1/911/partial6?queryField=title&searchTerm=David&startRecord=0&pageSize=5&orderBy=id&order=asc";
	HttpGet httpGet = new HttpGet(apiEndpoint);
	

	/**
	 * Test method for {@link com.searchclient.clientwrapper.domain.utils.MicroserviceHttpGateway#getRequest()}.
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@Test
	void testGetRequest() throws ClientProtocolException, IOException {
		
		CloseableHttpClient closeableHttpClient = mock(CloseableHttpClient.class);
		CloseableHttpResponse closeableHttpResponse = mock(CloseableHttpResponse.class);
		StatusLine statusLine = mock(StatusLine.class);
		Mockito.when(statusLine.getStatusCode()).thenReturn(200);
		Mockito.when(closeableHttpResponse.getStatusLine()).thenReturn(statusLine);
		Mockito.when(closeableHttpClient.execute(httpGet)).thenReturn(closeableHttpResponse);
		
		
		int responseStatus = closeableHttpResponse.getStatusLine().getStatusCode();
		
		JSONObject jsonObject = gateway.extracted(httpGet, closeableHttpClient);
		
		assertEquals(responseStatus, jsonObject.get("statusCode"));
		
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.searchclient.clientwrapper.domain.utils.MicroserviceHttpGateway#getLog()}.
	 */
	@Test
	void testGetLog() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.searchclient.clientwrapper.domain.utils.MicroserviceHttpGateway#getApiEndpoint()}.
	 */
	@Test
	void testGetApiEndpoint() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.searchclient.clientwrapper.domain.utils.MicroserviceHttpGateway#getRequestBodyDTO()}.
	 */
	@Test
	void testGetRequestBodyDTO() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.searchclient.clientwrapper.domain.utils.MicroserviceHttpGateway#setApiEndpoint(String)}.
	 */
	@Test
	void testSetApiEndpoint() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.searchclient.clientwrapper.domain.utils.MicroserviceHttpGateway#setRequestBodyDTO(Object)}.
	 */
	@Test
	void testSetRequestBodyDTO() {
		fail("Not yet implemented");
	}

	
	
	
	
	
	
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}
}
