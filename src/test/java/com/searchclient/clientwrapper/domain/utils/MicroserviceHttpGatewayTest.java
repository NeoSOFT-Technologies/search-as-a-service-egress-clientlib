package com.searchclient.clientwrapper.domain.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class MicroserviceHttpGatewayTest {

	HttpGatewayUtil gatewayUtil = new HttpGatewayUtil();
	MicroserviceHttpGateway gateway = new MicroserviceHttpGateway();
		
	String apiEndpoint = "http://localhost:8082/search/api/v1/911/partial6?queryField=title&searchTerm=David&startRecord=0&pageSize=5&orderBy=id&order=asc";
	HttpGet httpGet = new HttpGet(apiEndpoint);
	HttpGet httpGetSetUp = new HttpGet();
	CloseableHttpClient httpClientSetUp = HttpClients.createDefault();

	public void setUpExtracted(HttpGet httpGetPrepared, CloseableHttpClient httpClientPrepared) {
		httpGetSetUp = httpGetPrepared;
		httpClientSetUp = httpClientPrepared;
	}

	/**
	 * Test method for {@link com.searchclient.clientwrapper.domain.utils.MicroserviceHttpGateway#getApiEndpoint()}.
	 */
	@Test
	void testGetSetApiEndpoint() {
		String dummyEndpoint = "my/endpoint";
		gateway.setApiEndpoint(dummyEndpoint);
		String receivedEndpoint = gateway.getApiEndpoint();
		assertEquals(dummyEndpoint, receivedEndpoint);
	}

	/**
	 * Test method for {@link com.searchclient.clientwrapper.domain.utils.MicroserviceHttpGateway#getRequestBodyDTO()}.
	 */
	@Test
	void testGetSetRequestBodyDTO() {
		Object dummyReqBody = "{\"reqBody\":\"SKD\"}";
		gateway.setRequestBodyDTO(dummyReqBody);
		Object receivedReqBody = gateway.getRequestBodyDTO();
		assertEquals(dummyReqBody, receivedReqBody);
	}

}
