package com.searchclient.clientwrapper.domain.utils;

public enum HttpStatusCode {

	INVALID_JSON_INPUT(105,"invalid json input or json format"),

	INVALID_TYPE(115, "{} must be of type {}"),
	
	INVALID_FIELD_VALUE(116, "Value for field : {} is not expected as : {}"),
	
	UNRECOGNIZED_FIELD(106,"Unrecognized Field : {}"),
	
	BAD_REQUEST_EXCEPTION(400,"Bad Request call made. "),
	
	INVALID_QUERY_FORMAT(117, "Couldn't parse the search query."),
	
	UNDER_DELETION_PROCESS(107,"under deletion process"),
	
	INVALID_QUERY_FIELD(118, "Query-field validation unsuccessful. Query-field entry can only be in alphanumeric format");
	
	private int code;
	private String message;
	
	HttpStatusCode(int code, String message) {
		this.code=code;
		this.message=message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	public static HttpStatusCode  getHttpStatus(int statusCode) {
		for( HttpStatusCode httpStatusCode: HttpStatusCode.values()) {
			if(httpStatusCode.getCode() == statusCode) {
				return httpStatusCode;
			}
		}
		return null;
	}

	
}
