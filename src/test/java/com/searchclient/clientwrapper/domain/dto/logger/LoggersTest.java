package com.searchclient.clientwrapper.domain.dto.logger;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LoggersTest {

	Loggers loggers = new Loggers();
	
	@Test
	void testGetSetServicename() {
		String preparedMessage = "I am Batman";
		loggers.setServicename(preparedMessage);
		String receivedMessage = loggers.getServicename();
		
		assertEquals(preparedMessage, receivedMessage);
	}

	@Test
	void testGetSetUsername() {
		String preparedMessage = "I am Batman";
		loggers.setUsername(preparedMessage);
		String receivedMessage = loggers.getUsername();
		
		assertEquals(preparedMessage, receivedMessage);
	}

	@Test
	void testGetSetCorrelationid() {
		String preparedMessage = "I am Batman";
		loggers.setCorrelationid(preparedMessage);
		String receivedMessage = loggers.getCorrelationid();
		
		assertEquals(preparedMessage, receivedMessage);
	}

	@Test
	void testGetSetIpaddress() {
		String preparedMessage = "I am Batman";
		loggers.setIpaddress(preparedMessage);
		String receivedMessage = loggers.getIpaddress();
		
		assertEquals(preparedMessage, receivedMessage);
	}

	@Test
	void testGetSetTimestamp() {
		String preparedMessage = "I am Batman";
		loggers.setTimestamp(preparedMessage);
		String receivedMessage = loggers.getTimestamp();
		
		assertEquals(preparedMessage, receivedMessage);
	}

	@Test
	void testGetSetNameofmethod() {
		String preparedMessage = "I am Batman";
		loggers.setNameofmethod(preparedMessage);
		String receivedMessage = loggers.getNameofmethod();
		
		assertEquals(preparedMessage, receivedMessage);
	}

}
