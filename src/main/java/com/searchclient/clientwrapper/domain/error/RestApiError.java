package com.searchclient.clientwrapper.domain.error;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.searchclient.clientwrapper.domain.utils.HttpStatusCode;
import lombok.Data;

@Data
public class RestApiError {
	  private HttpStatus status;
	   private HttpStatusCode httpStatus;
	   private int statusCode;
	   private String message;
	   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	   private LocalDateTime timestamp;

	   private RestApiError() {
	       timestamp = LocalDateTime.now();
	   }
     static RestApiError getInstance() {
   	  return new RestApiError();
     }
	   RestApiError(HttpStatus status) {
	       this();
	       this.status = status;
	       this.message = "Unexpected Exception";
	   }
	   
	   
	   
	   RestApiError(HttpStatus status, int statusCode, String message) {
	       this();
	       this.status = status;
	       this.message = message;
	       this.statusCode = statusCode;
	   }
	   
	   RestApiError(HttpStatusCode httpStatus, int statusCode, String message) {
	       this();
	       this.httpStatus = httpStatus;
	       this.message = message;
	       this.statusCode = statusCode;
	   }

	   RestApiError(HttpStatus status, Throwable ex) {
	       this();
	       this.status = status;
	       this.message = ex.getLocalizedMessage();
	   }
	   
	   RestApiError(HttpStatus status, String message) {
	       this();
	       this.status = status;
	       this.message = message;
	   }
}