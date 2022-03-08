package com.searchclient.clientwrapper.domain.utils;

import java.util.Map;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.searchclient.clientwrapper.domain.port.api.MicroserviceHttpGatewayPort;

import lombok.Data;

@Data
@Service
@ComponentScan
public class MicroserviceHttpGateway implements MicroserviceHttpGatewayPort {

	private final Logger log = LoggerFactory.getLogger(MicroserviceHttpGateway.class);

    private String apiEndpoint;
    private Object requestBodyDTO;

    HttpGatewayUtil httpGatewayUtil = new HttpGatewayUtil();

	@Override
	public JSONObject getRequest() {
    	Map<String, Object> httpMap =  httpGatewayUtil.prepareHttpClient(apiEndpoint);
    	
        return httpGatewayUtil.extracted((HttpGet)httpMap.get("httpGet"), (CloseableHttpClient)httpMap.get("httpClient"));

	}
}
