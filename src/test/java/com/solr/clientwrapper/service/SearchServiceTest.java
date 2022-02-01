package com.solr.clientwrapper.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class SearchServiceTest {

	Logger logger = LoggerFactory.getLogger(SearchServiceTest.class);

	
	@Test
	void testSearch() {
		String expectedResponse = "Success";

		// Get Status of Existing Core
	
	}
}
