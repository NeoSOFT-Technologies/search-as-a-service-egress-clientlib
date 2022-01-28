package com.searchclient.clientwrapper.service;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.solr.clientwrapper.domain.dto.solrsearch.SolrSearchResponseDTO;
import com.solr.clientwrapper.domain.port.api.SolrSearchRecordsServicePort;
import com.solr.clientwrapper.domain.utils.MicroserviceHttpGateway;

@Service 
@Transactional
public class SolrSearchRecordsService implements SolrSearchRecordsServicePort {
	/*
	 * Solr Search Records for given collection- Egress Service
	 */
	private String apiEndpoint = "/solr-search-records";

	@Value("${base-microservice-url}")
	private String baseMicroserviceUrl;

	private final Logger logger = LoggerFactory.getLogger(SolrSearchRecordsService.class);

	@Autowired
	SolrSearchResponseDTO solrSearchResponseDTO ;
	// call for solr client

	private SolrSearchResponseDTO extracted(String collection, String queryField, String searchTerm, String startRecord,
			String pageSize, String tag, String order, String startPage,
			MicroserviceHttpGateway microserviceHttpGateway) {
		microserviceHttpGateway.setRequestBodyDTO(collection);
		microserviceHttpGateway.setRequestBodyDTO(queryField);
		microserviceHttpGateway.setRequestBodyDTO(searchTerm);
		microserviceHttpGateway.setRequestBodyDTO(startRecord);
		microserviceHttpGateway.setRequestBodyDTO(pageSize);
		microserviceHttpGateway.setRequestBodyDTO(tag);
		microserviceHttpGateway.setRequestBodyDTO(order);
		microserviceHttpGateway.setRequestBodyDTO(startPage);
		JSONObject jsonObject = microserviceHttpGateway.getRequest();

		solrSearchResponseDTO.setSolrDocuments(jsonObject);
		;

		return solrSearchResponseDTO;
	}

	private SolrSearchResponseDTO extracted(String collection, String queryField, String searchTerm, String startRecord,
			String pageSize, String tag, String order, MicroserviceHttpGateway microserviceHttpGateway) {
		microserviceHttpGateway.setRequestBodyDTO(collection);
		microserviceHttpGateway.setRequestBodyDTO(queryField);
		microserviceHttpGateway.setRequestBodyDTO(searchTerm);
		microserviceHttpGateway.setRequestBodyDTO(startRecord);
		microserviceHttpGateway.setRequestBodyDTO(pageSize);
		microserviceHttpGateway.setRequestBodyDTO(tag);
		microserviceHttpGateway.setRequestBodyDTO(order);
		JSONObject jsonObject = microserviceHttpGateway.getRequest();

		solrSearchResponseDTO.setSolrDocuments(jsonObject);

		return solrSearchResponseDTO;
	}

	private SolrSearchResponseDTO extracted(String collection, String queryField, String searchTerm, String tag,
			String order, MicroserviceHttpGateway microserviceHttpGateway) {
		microserviceHttpGateway.setRequestBodyDTO(collection);
		microserviceHttpGateway.setRequestBodyDTO(queryField);
		microserviceHttpGateway.setRequestBodyDTO(searchTerm);
		microserviceHttpGateway.setRequestBodyDTO(tag);
		microserviceHttpGateway.setRequestBodyDTO(order);
		JSONObject jsonObject = microserviceHttpGateway.getRequest();

		solrSearchResponseDTO.setSolrDocuments(jsonObject);

		return solrSearchResponseDTO;
	}

	private SolrSearchResponseDTO extracted(String collection, String queryField, String searchTerm,
			MicroserviceHttpGateway microserviceHttpGateway) {
		microserviceHttpGateway.setRequestBodyDTO(collection);
		microserviceHttpGateway.setRequestBodyDTO(queryField);
		microserviceHttpGateway.setRequestBodyDTO(searchTerm);
		JSONObject jsonObject = microserviceHttpGateway.getRequest();

		solrSearchResponseDTO.setSolrDocuments(jsonObject);

		return solrSearchResponseDTO;
	}

	private SolrSearchResponseDTO extracted(String collection, MicroserviceHttpGateway microserviceHttpGateway) {
		microserviceHttpGateway.setRequestBodyDTO(collection);
		JSONObject jsonObject = microserviceHttpGateway.getRequest();		
		solrSearchResponseDTO.setSolrDocuments(jsonObject);
		return solrSearchResponseDTO;
	}

	@Override
	public SolrSearchResponseDTO setUpSelectQueryUnfiltered(String collection) {
		/* Egress API -- solr collection records -- UNFILTERED SEARCH */
		logger.debug("Performing UNFILTERED solr search for given collection");

		MicroserviceHttpGateway microserviceHttpGateway = new MicroserviceHttpGateway();
		microserviceHttpGateway.setApiEndpoint(baseMicroserviceUrl + apiEndpoint + "/unfiltered");
		return extracted(collection, microserviceHttpGateway);
	}

	@Override
	public SolrSearchResponseDTO setUpSelectQueryBasicSearch(String collection, String queryField, String searchTerm) {
		/* Egress API -- solr collection records -- BASIC SEARCH (by QUERY FIELD) */
		logger.debug("Performing BASIC solr search for given collection");

		MicroserviceHttpGateway microserviceHttpGateway = new MicroserviceHttpGateway();
		microserviceHttpGateway.setApiEndpoint(baseMicroserviceUrl + apiEndpoint + "/basic");
		return extracted(collection, queryField, searchTerm, microserviceHttpGateway);
	}

	@Override
	public SolrSearchResponseDTO setUpSelectQueryOrderedSearch(String collection, String queryField, String searchTerm,
			String tag, String order) {
		/* Egress API -- solr collection records -- ORDERED SEARCH */
		logger.debug("Performing ORDERED solr search for given collection");
		MicroserviceHttpGateway microserviceHttpGateway = new MicroserviceHttpGateway();
		microserviceHttpGateway.setApiEndpoint(baseMicroserviceUrl + apiEndpoint + "/ordered");
		return extracted(collection, queryField, searchTerm, tag, order, microserviceHttpGateway);
	}

	@Override
	public SolrSearchResponseDTO setUpSelectQueryAdvancedSearch(String collection, String queryField, String searchTerm,
			String startRecord, String pageSize, String tag, String order) {
		/* Egress API -- solr collection records -- ADVANCED SEARCH */
		logger.debug("Performing ADVANCED solr search for given collection");
		MicroserviceHttpGateway microserviceHttpGateway = new MicroserviceHttpGateway();
		microserviceHttpGateway.setApiEndpoint(baseMicroserviceUrl + apiEndpoint + "/advanced");
		return extracted(collection, queryField, searchTerm, startRecord, pageSize, tag, order,
				microserviceHttpGateway);
	}

	@Override
	public SolrSearchResponseDTO setUpSelectQueryAdvancedSearchWithPagination(String collection, String queryField,
			String searchTerm, String startRecord, String pageSize, String tag, String order, String startPage) {
		/* Egress API -- solr collection records -- PAGINATED SEARCH */
		logger.debug("Performing PAGINATED solr search for given collection");
		MicroserviceHttpGateway microserviceHttpGateway = new MicroserviceHttpGateway();
		microserviceHttpGateway.setApiEndpoint(baseMicroserviceUrl + apiEndpoint + "/paginated");
		return extracted(collection, queryField, searchTerm, startRecord, pageSize, tag, order, startPage,
				microserviceHttpGateway);
	}

}
