package com.searchclient.clientwrapper.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
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
public class SolrSearchRecordsService implements SolrSearchRecordsServicePort {
    /*
     * Solr Search Records for given collection- Egress Service
     */
    private String apiEndpoint = "/solr-search-records";

    @Value("${base-microservice-url}")
    private String baseMicroserviceUrl;

    @Autowired
    MicroserviceHttpGateway microserviceHttpGateway;

    private final Logger logger = LoggerFactory.getLogger(SolrSearchRecordsService.class);

    // call for solr client

    @Override
    public SolrSearchResponseDTO setUpSelectQueryUnfiltered(String collection) {
        /* Egress API -- solr collection records -- UNFILTERED SEARCH */
        logger.debug("Performing UNFILTERED solr search for given collection");
        SolrSearchResponseDTO solrSearchResponseDTO = new SolrSearchResponseDTO();
        microserviceHttpGateway.setApiEndpoint(baseMicroserviceUrl + apiEndpoint + "/unfiltered");
        microserviceHttpGateway.setRequestBodyDTO(collection);
        JSONObject jsonObject = microserviceHttpGateway.getRequest();
        JSONObject jsonresult = (JSONObject) jsonObject.get("solrSearchResultResponse");
        Long jsonnumdocs = jsonresult.getLong("numDocs");

        JSONArray jsonsolrdocs = jsonresult.getJSONArray("solrDocuments");
        List<JSONObject> attributes = new ArrayList<>();

        for (int i = 0; i < jsonsolrdocs.length(); i++) {
            JSONObject childJsonObject = (JSONObject) jsonsolrdocs.get(i);
            attributes.add(childJsonObject);

        }
        logger.debug("attributes==" + attributes);
        getResponse(jsonObject, jsonnumdocs, attributes,solrSearchResponseDTO);

        logger.debug("solrSearchResponseDTO==" + solrSearchResponseDTO);
        return solrSearchResponseDTO;
    }

    @Override
    public SolrSearchResponseDTO setUpSelectQueryBasicSearch(String collection, String queryField, String searchTerm) {
        /*
         * Egress API -- solr collection records -- BASIC SEARCH (by QUERY
         * FIELD)
         */
        logger.debug("Performing BASIC solr search for given collection");
        SolrSearchResponseDTO solrSearchResponseDTO = new SolrSearchResponseDTO();

        microserviceHttpGateway.setApiEndpoint(baseMicroserviceUrl + apiEndpoint + "/basic");
        microserviceHttpGateway.setRequestBodyDTO(collection);
        microserviceHttpGateway.setRequestBodyDTO(queryField);
        microserviceHttpGateway.setRequestBodyDTO(searchTerm);
        JSONObject jsonObject = microserviceHttpGateway.getRequest();
        JSONObject jsonresult = (JSONObject) jsonObject.get("solrSearchResultResponse");

        Long jsonnumdocs = jsonresult.getLong("numDocs");

        JSONArray jsonsolrdocs = jsonresult.getJSONArray("solrDocuments");

        List<JSONObject> attributes = new ArrayList<>();

        for (int i = 0; i < jsonsolrdocs.length(); i++) {
            JSONObject childJsonObject = (JSONObject) jsonsolrdocs.get(i);
            attributes.add(childJsonObject);

        }

        getResponse(jsonObject, jsonnumdocs, attributes,solrSearchResponseDTO);

        return solrSearchResponseDTO;
    }

    private void getResponse(JSONObject jsonObject, Long jsonnumdocs, List<JSONObject> attributes, SolrSearchResponseDTO solrSearchResponseDTO) {
        solrSearchResponseDTO.setResponseMessage(jsonObject.get("responseMessage").toString());
        solrSearchResponseDTO.setStatusCode((int) jsonObject.get("statusCode"));
        solrSearchResponseDTO.setNumDocs(jsonnumdocs);
        solrSearchResponseDTO.setSolrDocuments(attributes);
    }

    @Override
    public SolrSearchResponseDTO setUpSelectQueryOrderedSearch(String collection, String queryField, String searchTerm, String tag, String order) {
        /* Egress API -- solr collection records -- ORDERED SEARCH */
        logger.debug("Performing ORDERED solr search for given collection");
        SolrSearchResponseDTO solrSearchResponseDTO = new SolrSearchResponseDTO();

        microserviceHttpGateway.setApiEndpoint(baseMicroserviceUrl + apiEndpoint + "/ordered");
        microserviceHttpGateway.setRequestBodyDTO(collection);
        microserviceHttpGateway.setRequestBodyDTO(queryField);
        microserviceHttpGateway.setRequestBodyDTO(searchTerm);
        microserviceHttpGateway.setRequestBodyDTO(tag);
        microserviceHttpGateway.setRequestBodyDTO(order);
        JSONObject jsonObject = microserviceHttpGateway.getRequest();
        JSONObject jsonresult = (JSONObject) jsonObject.get("solrSearchResultResponse");

        Long jsonnumdocs = jsonresult.getLong("numDocs");

        JSONArray jsonsolrdocs = jsonresult.getJSONArray("solrDocuments");

        List<JSONObject> attributes = new ArrayList<>();

        for (int i = 0; i < jsonsolrdocs.length(); i++) {
            JSONObject childJsonObject = (JSONObject) jsonsolrdocs.get(i);
            attributes.add(childJsonObject);

        }

        getResponse(jsonObject, jsonnumdocs, attributes,solrSearchResponseDTO);

        return solrSearchResponseDTO;
    }

    @Override
    public SolrSearchResponseDTO setUpSelectQueryAdvancedSearch(String collection, String queryField, String searchTerm, String startRecord, String pageSize, String tag, String order) {
        /* Egress API -- solr collection records -- ADVANCED SEARCH */
        logger.debug("Performing ADVANCED solr search for given collection");
        SolrSearchResponseDTO solrSearchResponseDTO = new SolrSearchResponseDTO();

        microserviceHttpGateway.setApiEndpoint(baseMicroserviceUrl + apiEndpoint + "/advanced");
        microserviceHttpGateway.setRequestBodyDTO(collection);
        microserviceHttpGateway.setRequestBodyDTO(queryField);
        microserviceHttpGateway.setRequestBodyDTO(searchTerm);
        microserviceHttpGateway.setRequestBodyDTO(startRecord);
        microserviceHttpGateway.setRequestBodyDTO(pageSize);
        microserviceHttpGateway.setRequestBodyDTO(tag);
        microserviceHttpGateway.setRequestBodyDTO(order);
        JSONObject jsonObject = microserviceHttpGateway.getRequest();
        JSONObject jsonresult = (JSONObject) jsonObject.get("solrSearchResultResponse");

        Long jsonnumdocs = jsonresult.getLong("numDocs");

        JSONArray jsonsolrdocs = jsonresult.getJSONArray("solrDocuments");

        List<JSONObject> attributes = new ArrayList<>();

        for (int i = 0; i < jsonsolrdocs.length(); i++) {
            JSONObject childJsonObject = (JSONObject) jsonsolrdocs.get(i);
            attributes.add(childJsonObject);

        }

        getResponse(jsonObject, jsonnumdocs, attributes,solrSearchResponseDTO);

        return solrSearchResponseDTO;
    }

    @Override
    public SolrSearchResponseDTO setUpSelectQueryAdvancedSearchWithPagination(String collection, String queryField, String searchTerm, String startRecord, String pageSize, String tag, String order,
            String startPage) {
        /* Egress API -- solr collection records -- PAGINATED SEARCH */
        logger.debug("Performing PAGINATED solr search for given collection");
        SolrSearchResponseDTO solrSearchResponseDTO = new SolrSearchResponseDTO();

        microserviceHttpGateway.setApiEndpoint(baseMicroserviceUrl + apiEndpoint + "/paginated");
        microserviceHttpGateway.setRequestBodyDTO(collection);
        microserviceHttpGateway.setRequestBodyDTO(queryField);
        microserviceHttpGateway.setRequestBodyDTO(searchTerm);
        microserviceHttpGateway.setRequestBodyDTO(startRecord);
        microserviceHttpGateway.setRequestBodyDTO(pageSize);
        microserviceHttpGateway.setRequestBodyDTO(tag);
        microserviceHttpGateway.setRequestBodyDTO(order);
        microserviceHttpGateway.setRequestBodyDTO(startPage);
        JSONObject jsonObject = microserviceHttpGateway.getRequest();
        JSONObject jsonresult = (JSONObject) jsonObject.get("solrSearchResultResponse");

        Long jsonnumdocs = jsonresult.getLong("numDocs");

        JSONArray jsonsolrdocs = jsonresult.getJSONArray("solrDocuments");

        List<JSONObject> attributes = new ArrayList<>();

        for (int i = 0; i < jsonsolrdocs.length(); i++) {
            JSONObject childJsonObject = (JSONObject) jsonsolrdocs.get(i);
            attributes.add(childJsonObject);

        }

        getResponse(jsonObject, jsonnumdocs, attributes,solrSearchResponseDTO);

        return solrSearchResponseDTO;
    }

}
