package com.searchclient.clientwrapper.domain.error;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Component
public class JwtAuthenticationFailureException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private int exceptionCode;
	private final String exceptionMessage;
	private final HttpStatus status; 
}
