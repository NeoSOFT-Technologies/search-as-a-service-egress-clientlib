package com.searchclient.clientwrapper.domain.dto.logger;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Loggers {
	private String servicename;
	private String username;
	private String correlationid;
	private String ipaddress;
	private String timestamp;
	private String nameofmethod;
	
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCorrelationid() {
		return correlationid;
	}
	public void setCorrelationid(String correlationid) {
		this.correlationid = correlationid;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNameofmethod() {
		return nameofmethod;
	}
	public void setNameofmethod(String nameofmethod) {
		this.nameofmethod = nameofmethod;
	}
	
}
