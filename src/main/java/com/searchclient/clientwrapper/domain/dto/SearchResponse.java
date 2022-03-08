package com.searchclient.clientwrapper.domain.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchResponse {

	private int statusCode;
	private String message;
	private  Object solrDocuments;
	
	
	public SearchResponse() {}
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getSolrDocuments() {
		return solrDocuments;
	}
	public void setSolrDocuments(Object solrDocuments) {
		this.solrDocuments = solrDocuments;
	}
	
}
