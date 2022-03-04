package com.searchclient.clientwrapper.domain.port.api;

import com.searchclient.clientwrapper.domain.dto.SearchResponse;
import com.searchclient.clientwrapper.domain.dto.logger.LoggersDTO;

public interface SearchServicePort {
		
	SearchResponse setUpSelectQuerySearchViaQueryField(
										int clientId, 
										String collection, 
										String queryField, 
										String searchTerm, 
										String startRecord, 
										String pageSize, 
										String tag, 
										String order,
										LoggersDTO loggersDTO);
	
	SearchResponse setUpSelectQuerySearchViaQuery(
										int clientId, 
										String tableName, 
										String searchQuery, 
										String startRecord, 
										String pageSize, 
										String orderBy, 
										String order,
										LoggersDTO loggersDTO);
	
}
