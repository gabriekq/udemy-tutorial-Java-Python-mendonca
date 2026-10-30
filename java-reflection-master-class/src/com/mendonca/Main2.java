package com.mendonca;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main2 {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		//printConstructor(Person.class);
		//printConstructor(Address.class);
		
	   Address address=createInstanceWithArguments(Address.class,"First street",10);
	   Person person =createInstanceWithArguments(Person.class,address,"Gabriel",25);
	   System.out.println(person);

	}
	
	public static  <T> T createInstanceWithArguments(Class<T> clazz,Object ... args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		for(Constructor<?> constructor: clazz.getDeclaredConstructors() ) {
			
			if(constructor.getParameterTypes().length==args.length) {
				return (T)constructor.newInstance(args);
			}
			
		}
	
		System.out.println("constructor was not found");
		return null;
		
		
	}
	

	public static void printConstructor(Class<?> clazz ) {
		
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();
		
		System.out.println(String.format("class %s has %d declared constructor", clazz.getSimpleName(),constructors.length)  );
		
		for(int index=0; index < constructors.length;index++) {
			
			Class<?> [] parameterTypes = constructors[index].getParameterTypes();
			List<String> parameterTypeNames = Arrays.stream(parameterTypes)
					.map(type-> type.getSimpleName())
					.collect(Collectors.toList());
			
			System.out.println(parameterTypeNames);
			
		}
		
		
		
		
	}
	

}
