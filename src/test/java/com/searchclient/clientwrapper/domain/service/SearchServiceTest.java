package com.searchclient.clientwrapper.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.searchclient.clientwrapper.domain.dto.SearchResponse;
import com.searchclient.clientwrapper.domain.error.CustomException;
import com.searchclient.clientwrapper.domain.utils.HttpStatusCode;
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
	
	Logger logger = LoggerFactory.getLogger(SearchServiceTest.class);
	
	// Parameters required for service methods
	int tenantId = 101;
	String tableName = "testtable";
	
	String searchQuery = "custom_query";
	String queryField = "customField";
	String searchTerm = "customSearchTerm";
	
	String startRecord = "0";
	String pageSize = "10";
	String orderBy = "id";
	String order = "asc";
	
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
	private String successResponseString = 
			"{\r\n"
			+ "\"statusCode\":200,\r\n"
			+ "\"message\":\""+TEST_PASSED+"\",\r\n"
			+ "\"results\":{\r\n"
			+ "	\"numDocs\":1,\r\n"
			+ "	\"data\":[\r\n"
			+ "			{\r\n"
			+ "				\"id\":\"1\",\"title\":\"David White\",\"product_name\":\"Amazing title - David White\",\"category\":[\"Timothy Ponce\",\"Laura Schaefer\",\"Kimberly Sanchez\"],\"price\":77800,\"vendor_id\":[46,61,40],\"is_available\":true,\"_version_\":1725442278693011456\r\n"
			+ "			}]\r\n"
			+ "	}\r\n"
			+ "}";
	
	private String notAcceptableResponseString = "{\"statusCode\":406,\"message\":\"Not Acceptable\"}";
	
	private String badRequestResponseString = 
			"{\r\n"
			+ "\"statusCode\":400,\r\n"
			+ "\"message\":\"Could not execute query to fetch records. URL: %s\",\r\n"
			+ "\"results\":{}\r\n"
			+ "}";
	
	public void setMockitoSuccessResponseService() {
		jsonObject = new JSONObject(successResponseString);
		Mockito.when(microserviceHttpGateway.getRequest(Mockito.anyString())).thenReturn(jsonObject);
	}
	
	public void setMockitoNotAcceptableResponseService() {
		jsonObject = new JSONObject(notAcceptableResponseString);
		Mockito.when(microserviceHttpGateway.getRequest(Mockito.anyString())).thenReturn(jsonObject);
	}
	
	public void setMockitoBadRequestResponseService() {
		jsonObject = new JSONObject(badRequestResponseString);
		Mockito.when(microserviceHttpGateway.getRequest(Mockito.anyString())).thenReturn(jsonObject);
	}
	
	public void setMockitoExceptionService() {
		Mockito.when(microserviceHttpGateway.getRequest(Mockito.anyString())).thenThrow(
				new CustomException(HttpStatusCode.REQUEST_FORBIDDEN.getCode(), HttpStatusCode.REQUEST_FORBIDDEN,
						HttpStatusCode.REQUEST_FORBIDDEN.getMessage()));
	}
	
	
	public SearchResponse searchViaQueryField() {
		SearchResponse queryFieldResponse = searchService.setUpSelectQuerySearchViaQueryField(
				tenantId, tableName, 
				queryField, 
				searchTerm, 
				startRecord, pageSize, 
				orderBy, order, Mockito.anyString());
		return queryFieldResponse;
	}
	
	public SearchResponse searchViaQuery() {
		SearchResponse queryResponse = searchService.setUpSelectQuerySearchViaQuery(
				tenantId, tableName, 
				searchQuery,  
				startRecord, pageSize, 
				orderBy, order, Mockito.anyString());
		return queryResponse;
	}
	@BeforeEach
	public void init() {

	}
	
	@Test
	void searchViaQueryFieldTests() {
		
		setMockitoSuccessResponseService();
		assertEquals(200, searchViaQueryField().getStatusCode());
		
		setMockitoNotAcceptableResponseService();
		assertEquals(HttpStatusCode.NOT_ACCEPTABLE_ERROR.getCode(), searchViaQueryField().getStatusCode());
		
		setMockitoBadRequestResponseService();
		assertEquals(HttpStatusCode.BAD_REQUEST_EXCEPTION.getCode(), searchViaQueryField().getStatusCode());
		
		setMockitoExceptionService();
		assertEquals(HttpStatusCode.REQUEST_FORBIDDEN.getCode(), searchViaQueryField().getStatusCode()) ;
		
	}
	
	
	@Test
	void searchViaQueryTests() {
		
		setMockitoSuccessResponseService();
		assertEquals(200, searchViaQuery().getStatusCode());
		
		setMockitoNotAcceptableResponseService();
		assertEquals(HttpStatusCode.NOT_ACCEPTABLE_ERROR.getCode(), searchViaQuery().getStatusCode());
		
		setMockitoBadRequestResponseService();
		assertEquals(HttpStatusCode.BAD_REQUEST_EXCEPTION.getCode(), searchViaQuery().getStatusCode());
		
		setMockitoExceptionService();
		assertEquals(HttpStatusCode.REQUEST_FORBIDDEN.getCode(), searchViaQuery().getStatusCode());

		
	}
	
}
