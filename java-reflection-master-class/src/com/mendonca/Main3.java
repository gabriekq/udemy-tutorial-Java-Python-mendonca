package com.mendonca;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.mendonca.web.WebServer;

public class Main3 {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		
		initConfiguration();
		WebServer webServer = new WebServer();
		webServer.startServer();
		
		

	}

	
	public static void initConfiguration() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<ServiceConfiguration> constructor =
				ServiceConfiguration.class.getDeclaredConstructor(int.class,String.class);
		
		constructor.setAccessible(true);
		constructor.newInstance(8080,"goodDay");
	}
	
	
	
	
}
