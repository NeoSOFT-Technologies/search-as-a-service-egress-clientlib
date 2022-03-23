package com.searchclient.clientwrapper.domain.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.searchclient.clientwrapper.domain.dto.SearchResponse;
import com.searchclient.clientwrapper.domain.dto.logger.Loggers;
import com.searchclient.clientwrapper.domain.utils.MicroserviceHttpGateway;
import com.searchclient.clientwrapper.domain.utils.SearchUtil;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@TestPropertySource(
		properties = {
				"microservice.base-url: http://localhost:8082", 
				"microservice.version: /search/api/v1"
		})
class SearchServiceTest {

	private static final String TEST_PASSED = "Test passed";
	private static final String QUERY_PROCESS_ERROR = "Could not execute query to fetch records. URL: %s";
	
	Logger logger = LoggerFactory.getLogger(SearchServiceTest.class);
	
	// Parameters required for service methods
	int clientId = 101;
	String tableName = "testtable";
	
	String searchQuery = "custom_query";
	String queryField = "customField";
	String searchTerm = "customSearchTerm";
	
	String startRecord = "0";
	String pageSize = "10";
	String orderBy = "id";
	String order = "asc";
	Loggers loggersDTO = new Loggers();
	
	@Value("${microservice.base-url}")
	private String baseMicroserviceUrl;
    @Value("${microservice.version}")
    private String microserviceVersion;
    private String endpoint = "";
    String apiEndpoint = "api-not-set-yet";
	

	@MockBean
	MicroserviceHttpGateway microserviceHttpGateway;
	@MockBean
	SearchUtil searchUtil;
	@InjectMocks
	SearchService searchService;

	private JSONObject jsonObject;
	private SearchResponse expectedResponse;
	private SearchResponse receivedResponse;
	private String successResponseString = 
			"{\r\n"
			+ "\"statusCode\":200,\r\n"
			+ "\"responseMessage\":\""+TEST_PASSED+"\",\r\n"
			+ "\"results\":{\r\n"
			+ "	\"numDocs\":1,\r\n"
			+ "	\"data\":[\r\n"
			+ "			{\r\n"
			+ "				\"id\":\"1\",\"title\":\"David White\",\"product_name\":\"Amazing title - David White\",\"category\":[\"Timothy Ponce\",\"Laura Schaefer\",\"Kimberly Sanchez\"],\"price\":77800,\"vendor_id\":[46,61,40],\"is_available\":true,\"_version_\":1725442278693011456\r\n"
			+ "			}]\r\n"
			+ "	}\r\n"
			+ "}";
	private String failureResponseString = 
			"{\r\n"
			+ "\"statusCode\":400,\r\n"
			+ "\"responseMessage\":\"Could not execute query to fetch records. URL: %s\",\r\n"
			+ "\"results\":{}\r\n"
			+ "}";
	
	@BeforeEach
	public void init() {

	}
	
	
	public void setUpEndpointSearchViaQueryField() {
		
		apiEndpoint = 
        		baseMicroserviceUrl + microserviceVersion + endpoint
        		+ "/" + clientId
        		+ "/" + tableName
        		+ "?queryField=" + queryField + "&searchTerm=" + searchTerm
        		+ "&startRecord=" + startRecord
                + "&pageSize=" + pageSize
                + "&orderBy=" + orderBy + "&order=" + order;

	}
	
	
	public void setUpEndpointSearchViaQuery() {
		endpoint = "/query";
		apiEndpoint = "null&null" + endpoint
        		+ "/" + clientId
        		+ "/" + tableName
        		+ "?searchQuery=" + searchQuery
        		+ "&startRecord=" + startRecord
                + "&pageSize=" + pageSize
                + "&orderBy=" + orderBy + "&order=" + order;
	}

	
	public void setUpSearchViaQueryFieldResponse() {
		receivedResponse = searchService.setUpSelectQuerySearchViaQueryField(
				clientId, tableName, 
				queryField, 
				searchTerm, 
				startRecord, pageSize, 
				orderBy, order, 
				
				Mockito.anyString());
	}
	
	public void setUpSearchViaQueryResponse() {
		receivedResponse = searchService.setUpSelectQuerySearchViaQuery(
				clientId, tableName, 
				searchQuery, 
				startRecord, pageSize, 
				orderBy, order, 
				Mockito.anyString());
	}
	

	public void setMockitoSuccessResponseForService() {
    	jsonObject = new JSONObject(successResponseString);
    	Mockito.when(microserviceHttpGateway.getRequest(Mockito.anyString())).thenReturn(jsonObject);
		
		expectedResponse = new SearchResponse();
    	expectedResponse.setStatusCode(200);
    	expectedResponse.setMessage(TEST_PASSED);
    	expectedResponse.setSolrDocuments(jsonObject.get("results"));
	}
	
	public void setMockitoHalfSuccessResponseForService() {
    	jsonObject = new JSONObject(successResponseString);
    	Mockito.when(microserviceHttpGateway.getRequest(Mockito.anyString())).thenReturn(jsonObject);
		
		expectedResponse = new SearchResponse();
    	expectedResponse.setStatusCode(406);
    	expectedResponse.setMessage("Query-field validation unsuccessful. Query-field entry can only be in alphanumeric format");
    	expectedResponse.setSolrDocuments(null);
	}

	public void setMockitoFailureResponseForService() {
		failureResponseString = String.format(failureResponseString, apiEndpoint);
    	jsonObject = new JSONObject(failureResponseString);
    	Mockito.when(microserviceHttpGateway.getRequest(Mockito.anyString())).thenReturn(jsonObject);
		
		expectedResponse = new SearchResponse();
    	expectedResponse.setStatusCode(400);
    	expectedResponse.setMessage(
    			String.format(
    					QUERY_PROCESS_ERROR, 
    					"null"));
    	expectedResponse.setSolrDocuments(null);
	}
	
	public void setMockitoExceptionResponseForService() {
		failureResponseString = String.format(failureResponseString, apiEndpoint);
    	jsonObject = new JSONObject(failureResponseString);
    	//Mockito.when(microserviceHttpGateway.getRequest()).thenReturn(jsonObject);
		
		expectedResponse = new SearchResponse();
    	expectedResponse.setStatusCode(400);
    	expectedResponse.setMessage(
    			String.format(
    					QUERY_PROCESS_ERROR, 
    					"null"));
    	expectedResponse.setSolrDocuments(null);
	}
	
	
	@Test
	@DisplayName("Testing SetUpSelectQuerySearchViaQueryField")
	void testSetUpSelectQuerySearchViaQueryField() {
		logger.info("Test case for setUpSelectQuerySearchViaQueryField service method is getting executed..");

		//==================================== Test Exception caught in service method ====================================
		setUpEndpointSearchViaQueryField();
		apiEndpoint = null;
		setMockitoExceptionResponseForService();
		setUpSearchViaQueryFieldResponse();
		
		assertEquals(expectedResponse.getStatusCode(), receivedResponse.getStatusCode());
		assertEquals(expectedResponse.getMessage(), receivedResponse.getMessage());
		assertThat(
				"Expected SolrDocs should be equal to received SolrDocs", 
				expectedResponse.getSolrDocuments() == null && receivedResponse.getSolrDocuments() == null, 
				is(true));
		
		//==================================== Test Failure ====================================
		setUpEndpointSearchViaQueryField();
		setMockitoFailureResponseForService();
		setUpSearchViaQueryFieldResponse();
		
		assertEquals(expectedResponse.getStatusCode(), receivedResponse.getStatusCode());
		assertEquals(expectedResponse.getMessage(), receivedResponse.getMessage());
		assertThat(
				"Expected SolrDocs should be equal to received SolrDocs", 
				expectedResponse.getSolrDocuments() == null && receivedResponse.getSolrDocuments() == null, 
				is(true));
		
		//==================================== Test SUCCESS ====================================
		// Query-field & search-term validated scenario
		setUpEndpointSearchViaQueryField();
		setMockitoSuccessResponseForService();
		setUpSearchViaQueryFieldResponse();
		
		assertEquals(expectedResponse.getStatusCode(), receivedResponse.getStatusCode());
		assertEquals(expectedResponse.getMessage(), receivedResponse.getMessage());
		assertThat(
				"Expected SolrDocs should be equal to received SolrDocs", 
				expectedResponse.getSolrDocuments().equals(receivedResponse.getSolrDocuments()), 
				is(true));
		
		// Query-field & search-term NOT validated scenario
		setUpEndpointSearchViaQueryField();
		setMockitoHalfSuccessResponseForService();
		queryField = "non_alpha-numeric_field";
		searchTerm = "non_alpha-numeric_search-term";
		setUpSearchViaQueryFieldResponse();
		
		assertEquals(expectedResponse.getStatusCode(), receivedResponse.getStatusCode());
		assertEquals(expectedResponse.getMessage(), receivedResponse.getMessage());
		assertThat(
				"Expected SolrDocs should be equal to received SolrDocs", 
				expectedResponse.getSolrDocuments() == null && receivedResponse.getSolrDocuments() == null, 
				is(true));
	}
	
	
	@Test
	@DisplayName("Testing SetUpSelectQuerySearchViaQuery")
	void testSetUpSelectQuerySearchViaQuery() {
		logger.info("Test case for setUpSelectQuerySearchViaQuery service method is getting executed..");
		
		//==================================== Test Exception thrown ====================================
		setUpEndpointSearchViaQuery();
		apiEndpoint = null;
		setMockitoExceptionResponseForService();
		setUpSearchViaQueryResponse();

		assertEquals(expectedResponse.getStatusCode(), receivedResponse.getStatusCode());
		assertEquals(expectedResponse.getMessage(), receivedResponse.getMessage());
		assertThat(
				"Expected SolrDocs should be equal to received SolrDocs", 
				expectedResponse.getSolrDocuments() == null && receivedResponse.getSolrDocuments() == null, 
				is(true));
		
		
		//==================================== Test Failure ====================================
		setUpEndpointSearchViaQuery();
		apiEndpoint = null;
		setMockitoFailureResponseForService();
		setUpSearchViaQueryResponse();
		
		assertEquals(expectedResponse.getStatusCode(), receivedResponse.getStatusCode());
		assertEquals(expectedResponse.getMessage(), receivedResponse.getMessage());
		assertThat(
				"Expected SolrDocs should be equal to received SolrDocs", 
				expectedResponse.getSolrDocuments() == null && receivedResponse.getSolrDocuments() == null, 
				is(true));
		
		//==================================== Test SUCCESS ====================================
		setUpEndpointSearchViaQuery();
		setMockitoSuccessResponseForService();
		setUpSearchViaQueryResponse();
		
		assertEquals(expectedResponse.getStatusCode(), receivedResponse.getStatusCode());
		assertEquals(expectedResponse.getMessage(), receivedResponse.getMessage());
		assertThat(
				"Expected SolrDocs should be equal to received SolrDocs", 
				expectedResponse.getSolrDocuments().equals(receivedResponse.getSolrDocuments()), 
				is(true));
	}
	
}
