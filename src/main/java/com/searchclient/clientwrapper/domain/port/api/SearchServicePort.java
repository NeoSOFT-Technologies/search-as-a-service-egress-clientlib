package com.searchclient.clientwrapper.domain.port.api;

import com.searchclient.clientwrapper.domain.dto.SearchResponse;

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
										String jwtToken);
	
	SearchResponse setUpSelectQuerySearchViaQuery(
										int clientId, 
										String tableName, 
										String searchQuery, 
										String startRecord, 
										String pageSize, 
										String orderBy, 
										String order,
										String jwtToken);
	
}
