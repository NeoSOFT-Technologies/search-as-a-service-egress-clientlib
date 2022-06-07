package com.searchclient.clientwrapper.domain.dto;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.searchclient.clientwrapper.domain.utils.HttpStatusCode;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchResponse {

	private int statusCode;
	private HttpStatusCode status;
	private String message;
	private  Object solrDocuments;
	
	
	public SearchResponse() {
		//Default Constructor
	}
	 
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

	public HttpStatusCode getStatus() {
		return status;
	}

	public void setStatus(HttpStatusCode status) {
		this.status = status;
	}

	
}
