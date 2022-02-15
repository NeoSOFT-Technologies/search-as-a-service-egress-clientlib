package com.searchclient.clientwrapper.domain.dto.logger;

import java.util.UUID;

public class CorrelationID {
	
    public String generateUniqueCorrelationId() {
        return UUID.randomUUID().toString();
    }

}
