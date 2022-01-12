package com.solr.clientwrapper.domain.dto.solrsearch;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.common.SolrDocument;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.solr.clientwrapper.infrastructure.solrbean.SolrSearchResult;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class SolrSearchResponseDTO {
	private int statusCode;
	private String responseMessage;
	private Long numDocs;
	private  List<JSONObject> solrDocuments;
	

	public SolrSearchResponseDTO(int statusCode, String responseMessage, Long numDocs, List<JSONObject> solrDocuments) {
		super();
		this.statusCode = statusCode;
		this.responseMessage = responseMessage;
		this.numDocs = numDocs;
		this.solrDocuments =  solrDocuments;
	}
	
	@Override
	public String toString() {
		
		return "SolrSearchResponseDTO [statusCode=" + statusCode + ", "
						+ "responseMessage=" + responseMessage + ","
						+ "numDocs=" + numDocs + ","
								+ "solrDocuments=" + solrDocuments +"]";
		
	}

	
}
