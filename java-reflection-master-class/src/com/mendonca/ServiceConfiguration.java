package com.mendonca;

import java.net.InetSocketAddress;

public class ServiceConfiguration {

	private static ServiceConfiguration serviceConfigurationInstance;
	
	private final InetSocketAddress serverAddress;
	private final String greetingMessage;
	
	private ServiceConfiguration(int port,String greetingMessage) {
		this.greetingMessage=greetingMessage;
		this.serverAddress= new InetSocketAddress("localhost", port);
		
		if(serviceConfigurationInstance==null) {
			serviceConfigurationInstance = this;
		}
		
	}
	
	public static ServiceConfiguration getInstance() {
		return serviceConfigurationInstance;
	}
	

	public InetSocketAddress getServerAddress() {
		return serverAddress;
	}

	public String getGreetingMessage() {
		return greetingMessage;
	}
	
	
}
