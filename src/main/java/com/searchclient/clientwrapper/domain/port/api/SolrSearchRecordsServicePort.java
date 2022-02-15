package com.searchclient.clientwrapper.domain.port.api;

import com.searchclient.clientwrapper.domain.dto.SolrSearchResponseDTO;
import com.searchclient.clientwrapper.domain.dto.logger.LoggersDTO;

public interface SolrSearchRecordsServicePort {
		
	SolrSearchResponseDTO setUpSelectQueryAdvancedSearch(	
										String collection, 
										String queryField, 
										String searchTerm, 
										String startRecord, 
										String pageSize, 
										String tag, 
										String order,
										LoggersDTO loggersDTO);
	
}
