package com.searchclient.clientwrapper.domain.error;

import org.springframework.http.HttpStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MicroserviceConnectionException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private final int exceptionCode;
	private final String exceptionMessage;
	private final HttpStatus status;
}
