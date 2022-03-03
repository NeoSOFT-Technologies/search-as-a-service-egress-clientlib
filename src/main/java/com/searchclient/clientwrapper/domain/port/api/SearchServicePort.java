package com.searchclient.clientwrapper.domain.port.api;

import com.searchclient.clientwrapper.domain.dto.SearchResponseDTO;
import com.searchclient.clientwrapper.domain.dto.logger.LoggersDTO;

public interface SearchServicePort {
		
	SearchResponseDTO setUpSelectQueryAdvancedSearch(
										int clientId, 
										String collection, 
										String queryField, 
										String searchTerm, 
										String startRecord, 
										String pageSize, 
										String tag, 
										String order,
										LoggersDTO loggersDTO);
	
	SearchResponseDTO setUpSelectQuerySearchViaQuery(
			int clientId, 
			String tableName, 
			String searchQuery, 
			String startRecord, 
			String pageSize, 
			String orderBy, 
			String order,
			LoggersDTO loggersDTO);
	
}
