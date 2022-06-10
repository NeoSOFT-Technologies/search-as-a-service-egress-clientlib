package com.searchclient.clientwrapper.domain.service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.searchclient.clientwrapper.domain.dto.SearchResponse;
import com.searchclient.clientwrapper.domain.error.CustomException;
import com.searchclient.clientwrapper.domain.port.api.SearchServicePort;
import com.searchclient.clientwrapper.domain.utils.HttpStatusCode;
import com.searchclient.clientwrapper.domain.utils.MicroserviceHttpGateway;

@Service
public class SearchService implements SearchServicePort {
    private static final String RESULTS = "results";
	private static final String STATUS_CODE = "statusCode";
	/*
     * Solr Search Records for given collection- Egress Service
     */
    @Value("${microservice.base-url}")
    private String baseMicroserviceUrl;
    @Value("${microservice.version}")
    private String microserviceVersion;
    private String apiEndpoint = "";
    private final Logger logger = LoggerFactory.getLogger(SearchService.class);


    @Autowired
    SearchResponse searchResponse = new SearchResponse();
    @Autowired
    MicroserviceHttpGateway microserviceHttpGateway;
   
    @Override
    public SearchResponse setUpSelectQuerySearchViaQueryField(
    		int tenantId, 
    		String tableName, 
    		String queryField, String searchTerm, 
    		String startRecord, 
    		String pageSize, 
    		String orderBy, String order, 
    		String jwtToken) {
        /* Egress API -- table records -- SEARCH VIA QUERY FIELD */
        logger.debug("Performing search-records VIA QUERY FIELD for given table");
//		space in between search term words replaces with '+'	
		searchTerm = searchTerm.replace(" ","+");
		
		
        microserviceHttpGateway.setApiEndpoint(
        		baseMicroserviceUrl + microserviceVersion + apiEndpoint
        		+ "/" + tableName
        		+ "?tenantId="+tenantId
        		+ "&queryField=" + queryField + "&searchTerm=" + searchTerm
        		+ "&startRecord=" + startRecord
                + "&pageSize=" + pageSize
                + "&orderBy=" + orderBy + "&order=" + order);

        try {
            JSONObject jsonObject = microserviceHttpGateway.getRequest(jwtToken);
            int statusCode = Integer.parseInt(jsonObject.get(STATUS_CODE).toString());
            searchResponse.setStatusCode(statusCode);
            searchResponse.setMessage(jsonObject.get("message").toString());
            if(200 == searchResponse.getStatusCode()) {
            	searchResponse.setStatus(HttpStatusCode.OK);
            	searchResponse.setSolrDocuments(jsonObject.get(RESULTS));
            }else if(406 == searchResponse.getStatusCode()){
            	searchResponse.setStatus(HttpStatusCode.NOT_ACCEPTABLE_ERROR);
            }else {
            	searchResponse.setStatus(HttpStatusCode.getHttpStatus(statusCode));
            }
            logger.debug("completed service run");
        } catch (Exception e) {
        	logger.error("Exception occurred while performing getRequest operation via Http Gateway: {}", e.getMessage());
        	if(e instanceof CustomException) {
        		CustomException ex = (CustomException)e;
        		searchResponse.setStatusCode(ex.getExceptionCode());
        		searchResponse.setMessage(ex.getExceptionMessage());
        		searchResponse.setStatus(ex.getStatus());
        	}
        }

        return searchResponse;
    }

	@Override
	public SearchResponse setUpSelectQuerySearchViaQuery(
			int tenantId, String tableName, 
			String searchQuery, 
			String startRecord, String pageSize, String orderBy, String order, String jwtToken) {
        /* Egress API -- table records -- SEARCH VIA QUERY BUILDER */
        logger.debug("Performing search-records VIA QUERY BUILDER for given table");
		searchQuery = searchQuery.replaceAll(" ","+");
		
		apiEndpoint = "/query";
        microserviceHttpGateway.setApiEndpoint(
        		baseMicroserviceUrl + microserviceVersion + apiEndpoint
        		+ "/" + tableName
        		+ "?tenantId=" + tenantId
        		+ "&searchQuery=" + searchQuery
        		+ "&startRecord=" + startRecord
                + "&pageSize=" + pageSize
                + "&orderBy=" + orderBy + "&order=" + order);
        
        try {
            JSONObject jsonObject = microserviceHttpGateway.getRequest(jwtToken);
            int statusCode = Integer.parseInt(jsonObject.get(STATUS_CODE).toString());
            searchResponse.setStatusCode(statusCode);
            searchResponse.setMessage(jsonObject.get("message").toString());
            if(200 == searchResponse.getStatusCode()) {
            	searchResponse.setStatus(HttpStatusCode.OK);
            	searchResponse.setSolrDocuments(jsonObject.get(RESULTS));
            }else if(406 == searchResponse.getStatusCode()){
            	searchResponse.setStatus(HttpStatusCode.NOT_ACCEPTABLE_ERROR);
            }else {
            	searchResponse.setStatus(HttpStatusCode.getHttpStatus(statusCode));
            }
            logger.debug("completed service run");
            
        } catch (Exception e) {
        	logger.error("Exception occurred while performing getRequest operation via Http Gateway: {}", e.getMessage());
        	if(e instanceof CustomException) {
        		CustomException ex = (CustomException)e;
        		searchResponse.setStatusCode(ex.getExceptionCode());
        		searchResponse.setMessage(ex.getExceptionMessage());
        		searchResponse.setStatus(ex.getStatus());
        	}
        }
      
        logger.info("searchResponse ######## {}", searchResponse);
        return searchResponse;
	}

}
