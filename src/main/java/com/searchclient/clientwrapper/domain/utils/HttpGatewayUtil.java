package com.searchclient.clientwrapper.domain.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HttpGatewayUtil {
    private static final String CONTENT_TYPE = "Content-type";
	private static final String ACCEPT = "Accept";
	private static final String APPLICATION_JSON = "application/json";
	
	private final Logger log = LoggerFactory.getLogger(HttpGatewayUtil.class);
	
	public JSONObject extracted(HttpGet http, CloseableHttpClient client) {
		String result = "{\"statusCode\":\"0\"}";
		CloseableHttpResponse response = null;
        try {
            http.setHeader(ACCEPT, APPLICATION_JSON);
            http.setHeader(CONTENT_TYPE, APPLICATION_JSON);

            response = client.execute(http);
    		Optional<HttpEntity> entityResponse = Optional.ofNullable(response.getEntity());
    		
    		if(entityResponse.isPresent()) {
    			result = EntityUtils.toString(entityResponse.get());
    		}
    		log.debug("RESPONSE: {}", result);

            client.close();
        } catch (Exception e) {
            log.error(e.toString());
            result = "{\"statusCode\":\"400\"}";
        }
        JSONObject jsonObject = new JSONObject(result);
        if(response != null)
        	jsonObject.put("statusCode", response.getStatusLine().getStatusCode());
        
        return jsonObject;
	}
	
	
    public Map<String, Object> prepareHttpClient(String apiEndpoint) {
		HttpGet http = new HttpGet(apiEndpoint);
		CloseableHttpClient client = HttpClients.createDefault();
		
		System.out.println("Inside prepHttpCl #####");
		System.out.println("httpGet @@@@@ "+http);
		System.out.println("httpClient @@@@@ "+client);
		
		Map<String, Object> map = new HashMap<>();
		map.put("httpGet", http);
		map.put("httpClient", client);
		
		return map;
    }
}