package com.searchclient.clientwrapper.domain.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.searchclient.clientwrapper.domain.error.CustomException;

@Service
public class HttpGatewayUtil {
    private static final String CONTENT_TYPE = "Content-type";
	private static final String ACCEPT = "Accept";
	private static final String APPLICATION_JSON = "application/json";
	private static final String UNAUTHORIZED = "Unauthorized";
	private final Logger log = LoggerFactory.getLogger(HttpGatewayUtil.class);
	
	public JSONObject extracted(HttpGet http, CloseableHttpClient client, String jwtToken) {
		String result = "{\"statusCode\":\"0\"}";
		CloseableHttpResponse response = null;
		isTokenNullOrValid(jwtToken);
        try {
            http.setHeader(ACCEPT, APPLICATION_JSON);
            http.setHeader(CONTENT_TYPE, APPLICATION_JSON);
            http.setHeader("Authorization", jwtToken);
            response = client.execute(http);
    		Optional<HttpEntity> entityResponse = Optional.ofNullable(response.getEntity());
    		
    		if(entityResponse.isPresent()) {
    			result = EntityUtils.toString(entityResponse.get());
    		}
    		log.debug("RESPONSE: {}", result);

            client.close();
        } catch (Exception e) {
        	handleException(e);
            log.error(e.toString());
            return new JSONObject(result);
        }
        isJwtAuthenticationError(result);
        return new JSONObject(result);
	}
	
	
    public Map<String, Object> prepareHttpClient(String apiEndpoint) {
		HttpGet http = new HttpGet(apiEndpoint);
		CloseableHttpClient client = HttpClients.createDefault();
		Map<String, Object> map = new HashMap<>();
		map.put("httpGet", http);
		map.put("httpClient", client);
		
		return map;
    }
    
    public void isJwtAuthenticationError(String jsonString) {
    	JSONObject obj = new JSONObject(jsonString);
    	if((obj.has(UNAUTHORIZED))&&obj.getString(UNAUTHORIZED).contains("Invalid token"))
    		throw new CustomException(HttpStatusCode.REQUEST_FORBIDEN.getCode(), 
    				HttpStatusCode.REQUEST_FORBIDEN, "Invalid token");
    }
    
    private void handleException(Exception exception) {
		if(exception instanceof HttpHostConnectException) {
			throw new CustomException(HttpStatusCode.SERVER_UNAVAILABLE.getCode(),
					HttpStatusCode.SERVER_UNAVAILABLE,"Unable to connect Microservice");
		}
	}
    
    public void isTokenNullOrValid(String jwtToken) {
    	if((null == jwtToken || jwtToken.isEmpty() || jwtToken.isBlank() || !jwtToken.startsWith("Bearer ")))
    		throw new CustomException(HttpStatusCode.REQUEST_FORBIDEN.getCode(), 
    				HttpStatusCode.REQUEST_FORBIDEN, "Provide valid token");
    }
}
