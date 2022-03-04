package com.searchclient.clientwrapper.domain.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchResponse {

	private int statusCode;
	private String message;
	private  Object solrDocuments;
	

	public SearchResponse( Object solrDocuments) {
		this.solrDocuments =  solrDocuments;
	}
	
}
