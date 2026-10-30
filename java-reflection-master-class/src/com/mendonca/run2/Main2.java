package com.mendonca.run2;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Scanner;

import com.mendonca.data2.GameConfig;
import com.mendonca.data2.UserInterfaceConfig;

public class Main2 {

	private static final Path GAME_CONFIG_PATH = Path.of("resources/game-properties.cfg");
	  private static final Path UI_CONFIG_PATH = Path.of("resources/user-interface.cfg");
	
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		
		GameConfig config = createConfigObject(GameConfig.class, GAME_CONFIG_PATH);
		
		UserInterfaceConfig config2 = createConfigObject(UserInterfaceConfig.class, UI_CONFIG_PATH);
		
		System.out.println(config);;
		System.out.println(config2);;
		

	}
	
	
	public static <T> T createConfigObject(Class<T> clazz,Path filePath) throws IOException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Scanner scanner = new Scanner(filePath);
		
		Constructor<?> constructor = clazz.getDeclaredConstructor();
		constructor.setAccessible(true);
		
		T configInstance = (T) constructor.newInstance();
		
		while(scanner.hasNextLine()) {
			String configLine = scanner.nextLine();
			
			String[] nameValuePair = configLine.split("=");
			
			if(nameValuePair.length!=2) {
				continue;
			}
			
			
			String propertyName=nameValuePair[0];
			String propertyValue=nameValuePair[1];
			
			Field field;
			try {
				field=clazz.getDeclaredField(propertyName);
			}catch (NoSuchFieldException noSuchFieldException) {
				System.out.println(String.format("Property name : %s is not suported.", nameValuePair));
			    continue;
			}
			field.setAccessible(true);
			
			Object parsedValue;
			
			if(field.getType().isArray()) {
				parsedValue=parseArray(field.getType().getComponentType(), propertyValue);
			}else {
				parsedValue=parseValue(field.getType(),propertyValue);
			}
			
			
			
			
			field.set(configInstance, parsedValue);

		}
		return configInstance;
		
	}

   private static Object parseArray(Class<?> arrayElementType, String value ) {
	   String [] elementValues = value.split(",");
	   Object arrayObject = Array.newInstance(arrayElementType, elementValues.length);
	   
	   for(int index=0;index< elementValues.length;index++) {
		   Array.set(arrayObject, index, parseValue(arrayElementType, elementValues[index]));
	   }
	   
	   return arrayObject;
   }
	
	
	
	private static Object parseValue(Class<?> type, String value) {
		
		if(type.equals(int.class)) {
			return Integer.parseInt(value);
		}else {
			
			if(type.equals(short.class)) {
				return Short.parseShort(value);
			}else {
				
				if(type.equals(long.class)) {
					return Long.parseLong(value);
				}else {
					
					if(type.equals(double.class)) {
						return Double.parseDouble(value);
					}else {
						
						if(type.equals(float.class)) {
							return Float.parseFloat(value);
						}else {
							
							if(type.equals(String.class)) {
							   return value;	
							}
							
							
						}
						
					}
					
				}
					
			}
				
		}
		
		throw new RuntimeException(String.format("Type not suported -> %s", type.getTypeName()));
		
	}
	
	
	

}
