package com.searchclient.clientwrapper.domain.port.api;

import com.searchclient.clientwrapper.domain.dto.SolrSearchResponseDTO;
import com.searchclient.clientwrapper.domain.dto.logger.LoggersDTO;

public interface SolrSearchServicePort {
		
	SolrSearchResponseDTO setUpSelectQueryAdvancedSearch(
										int clientId, 
										String collection, 
										String queryField, 
										String searchTerm, 
										String startRecord, 
										String pageSize, 
										String tag, 
										String order,
										LoggersDTO loggersDTO);
	
	SolrSearchResponseDTO setUpSelectQuerySearchViaQuery(
			int clientId, 
			String tableName, 
			String searchQuery, 
			String startRecord, 
			String pageSize, 
			String orderBy, 
			String order,
			LoggersDTO loggersDTO);
	
}
