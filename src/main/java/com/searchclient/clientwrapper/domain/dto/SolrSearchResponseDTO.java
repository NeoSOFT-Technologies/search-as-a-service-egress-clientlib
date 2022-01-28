package com.searchclient.clientwrapper.domain.dto;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class SolrSearchResponseDTO {

	private  Object solrDocuments;
	

	public SolrSearchResponseDTO( Object solrDocuments) {
		super();
		
		this.solrDocuments =  solrDocuments;
	}
	
	@Override
	public String toString() {
		
		return "Response " + solrDocuments ;
		
	}

	
}
