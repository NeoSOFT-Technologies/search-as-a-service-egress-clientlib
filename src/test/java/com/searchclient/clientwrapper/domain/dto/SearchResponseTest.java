package com.searchclient.clientwrapper.domain.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SearchResponseTest {

	SearchResponse searchResponse = new SearchResponse();

	@Test
	void testGetSetStatusCode() {
		int preparedStatusCode = 101;
		searchResponse.setStatusCode(preparedStatusCode);
		int receivedStatusCode = searchResponse.getStatusCode();
		
		assertEquals(preparedStatusCode, receivedStatusCode);
	}

	@Test
	void testGetSetMessage() {
		String preparedMessage = "I am Batman";
		searchResponse.setMessage(preparedMessage);
		String receivedMessage = searchResponse.getMessage();
		
		assertEquals(preparedMessage, receivedMessage);
	}

	@Test
	void testGetSetSolrDocuments() {
		Object preparedObject = "IAmBatman101";
		searchResponse.setSolrDocuments(preparedObject);
		Object receivedObject = searchResponse.getSolrDocuments();
		
		assertEquals(preparedObject, receivedObject);
	}

}
