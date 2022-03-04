package com.solr.clientwrapper.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.searchclient.clientwrapper.domain.dto.SearchResponse;
import com.searchclient.clientwrapper.domain.service.SearchService;
import com.searchclient.clientwrapper.domain.utils.MicroserviceHttpGateway;
import com.searchclient.clientwrapper.domain.utils.SearchUtil;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class SearchServiceTest {

	Logger logger = LoggerFactory.getLogger(SearchServiceTest.class);

	@Value("${base-microservice-url}")
	private String baseMicroserviceUrl;
	String collectionName = "demo1";

	Map<String, Map<String, Object>> schemaKeyValuePair = new HashMap();

	Map<String, Object> map = new HashMap();
	Map<String, Object> map2 = new HashMap();
	@InjectMocks
	SearchService solrDocumentService;

	@MockBean
	MicroserviceHttpGateway microserviceHttpGateway;
	@MockBean
	SearchUtil documentParser;

	JSONObject JsonObject;

	@BeforeEach
	public void init() {

		map.put("name", "color");
		map.put("type", "string");
		map.put("multivalued", false);
		map.put("required", true);

		map2.put("name", "id");
		map2.put("type", "string");
		map2.put("multivalued", false);
		map.put("required", true);

	}

	public void setMockitoSuccessResponseForService() {
		SearchResponse solrResponseDTO = new SearchResponse();
//		solrResponseDTO.setCode(200);
//		solrResponseDTO.setMessage("Testing");

		schemaKeyValuePair.put("id", map2);
		schemaKeyValuePair.put("color", map);
		JsonObject = new JSONObject(solrResponseDTO);
		String jsonString = JsonObject.toString();

		

//		Mockito.when(documentParser.getSchemaOfCollection(baseMicroserviceUrl, collectionName))
//				.thenReturn(schemaKeyValuePair);

		
		Mockito.when(microserviceHttpGateway.postRequestWithStringBody()).thenReturn(jsonString);

	}

	public void setMockitoBadResponseForService() {
		SearchResponse solrResponseDTO = new SearchResponse();
//		solrResponseDTO.setCode(400);
//		solrResponseDTO.setMessage("Testing");
		JsonObject = new JSONObject(solrResponseDTO);
		String jsonString = JsonObject.toString();

//		Mockito.when(documentParser.getSchemaOfCollection(baseMicroserviceUrl, collectionName))
//				.thenReturn(schemaKeyValuePair);
		
		Mockito.when(microserviceHttpGateway.postRequestWithStringBody()).thenReturn(jsonString);
	}
}
