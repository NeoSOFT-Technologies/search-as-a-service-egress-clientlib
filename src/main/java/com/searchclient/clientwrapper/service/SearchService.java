package com.searchclient.clientwrapper.service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.searchclient.clientwrapper.domain.dto.SolrSearchResponseDTO;
import com.searchclient.clientwrapper.domain.port.api.SolrSearchRecordsServicePort;
import com.searchclient.clientwrapper.domain.utils.MicroserviceHttpGateway;

@Service
public class SearchService implements SolrSearchRecordsServicePort {
    /*
     * Solr Search Records for given collection- Egress Service
     */
    private String apiEndpoint = "/solr-search-records";

    @Value("${base-microservice-url}")
    private String baseMicroserviceUrl;

    private final Logger logger = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    SolrSearchResponseDTO solrSearchResponseDTO;
    // call for solr client

    @Override
    public SolrSearchResponseDTO setUpSelectQueryAdvancedSearch(String tableName, String queryField, String searchTerm, String startRecord, String pageSize, String orderBy, String order) {
        /* Egress API -- solr collection records -- ADVANCED SEARCH */
        logger.debug("Performing ADVANCED solr search for given collection");
        MicroserviceHttpGateway microserviceHttpGateway = new MicroserviceHttpGateway();
        microserviceHttpGateway.setApiEndpoint(baseMicroserviceUrl + apiEndpoint + "/search/" + tableName + "?queryField=" + queryField + "&searchTerm=" + searchTerm + "&startRecord=" + startRecord
                + "&pageSize=" + pageSize + "&orderBy=" + orderBy + "&order=" + order);
        JSONObject jsonObject = microserviceHttpGateway.getRequest();

        solrSearchResponseDTO.setSolrDocuments(jsonObject);
        logger.debug("completed service run");

        return solrSearchResponseDTO;

    }

}
