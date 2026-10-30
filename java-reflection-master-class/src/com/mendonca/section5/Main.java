package com.mendonca.section5;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// test class
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	testGetters(Product.class);
	//	testSetter(Product.class);
		testSetter(ClothingProduct.class);
		

	}
	
	public static void testSetter(Class<?>dataClass) {
		List<Field> fields = getAllFields(dataClass);
		
		for(Field field : fields) {
			
			String setterName = "set"+capitalizeFirstLetter(field.getName());
			Method setterMethod=null;
			
			try {
				setterMethod = dataClass.getMethod(setterName, field.getType());
			}catch (NoSuchMethodException noSuchMethodException) {
				throw new IllegalStateException(String.format("Setter : %s has not found", setterName));
			}
			
			if(!setterMethod.getReturnType().equals(void.class)) {
				throw new IllegalStateException(String.format("Setter : %s has no return void", setterName));
				
			}
			
		}
		
	}
	
	
	private static List<Field> getAllFields(Class<?> clazz ){
		
		if(clazz==null || clazz.equals(Object.class) ) {
			return Collections.emptyList();
		}
		
		Field [] currentClassFields = clazz.getDeclaredFields();
		
		List<Field> inheritedFields= getAllFields(clazz.getSuperclass());
		List<Field> allFields = new LinkedList<>();
		
		allFields.addAll(Arrays.asList(currentClassFields));
		allFields.addAll(inheritedFields);
		return allFields;
		
	}
	
	
	
	public static void testGetters(Class<?> dataClass) {
		
		List<Field> fields = getAllFields(dataClass);
		
		Map<String,Method> mapMethodNameToMethod = mapMethodNameToMethod(dataClass);
		
		for(Field field: fields) {
			String getterName="get"+capitalizeFirstLetter(field.getName());
			
			if(!mapMethodNameToMethod.containsKey(getterName)) {
				throw new IllegalStateException(String.format("Field : %s does not have getter method", getterName));
			}
			
			Method getter = mapMethodNameToMethod.get(getterName);
			
			if(!getter.getReturnType().equals(field.getType())) {
				
				throw new IllegalStateException( String.format("Getter method %s : has return type %s but expected %s"
						,getter.getName(),
						getter.getReturnType().getTypeName(),
						field.getType().getTypeName()));
			}
			
			if(getter.getParameterCount() > 0) {
				throw new IllegalStateException( String.format("Getter method %s : has argsments ",getterName));
			}

		}
	}
	
	
	private static String capitalizeFirstLetter(String name) {
		
	  String partName = name.substring(1, name.length());
	  String finalName = name.substring(0, 1).toUpperCase()+partName;
		
		
		return finalName;
	}

	private static Map<String,Method> mapMethodNameToMethod(Class<?>dataClass){
		
		Method [] allMethod = dataClass.getMethods();
		Map<String,Method> nameToMethod = new HashMap<>(allMethod.length);
		
		for(Method method:allMethod) {
		
			nameToMethod.put(method.getName(), method);
		}
		
		
		return nameToMethod;
		
	}
	
	
	

}
