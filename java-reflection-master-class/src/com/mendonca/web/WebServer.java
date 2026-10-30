package com.mendonca.web;

import java.io.IOException;
import java.io.OutputStream;

import com.mendonca.ServiceConfiguration;
import com.sun.net.httpserver.HttpServer;


public class WebServer {

	  public void startServer() throws IOException {
	        HttpServer httpServer = HttpServer.create(ServiceConfiguration.getInstance().getServerAddress(), 0);

	        httpServer.createContext("/oi").setHandler(exchange -> {

	            String responseMessage = ServiceConfiguration.getInstance().getGreetingMessage();

	            exchange.sendResponseHeaders(200, responseMessage.length());

	            OutputStream responseBody = exchange.getResponseBody();
	            responseBody.write(responseMessage.getBytes());
	            responseBody.flush();
	            responseBody.close();
	        });


	        System.out.println(String.format("Starting server on address %s:%d",
	        		ServiceConfiguration.getInstance().getServerAddress().getHostName(),
	        		ServiceConfiguration.getInstance().getServerAddress().getPort()));

	        httpServer.start();
	    }
	
	
	
	
	
}
