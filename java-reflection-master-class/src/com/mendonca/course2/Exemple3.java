package com.mendonca.course2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Exemple3 {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
	Class<?> clas=	Class.forName("com.mendonca.course2.Entity");
	//Constructor<?>[] constructors = clas.getConstructors();
	Constructor<?>[] constructors = clas.getDeclaredConstructors();
				
	for(Constructor<?> constructor :  constructors) {
		System.out.println(constructor);
	}
	Constructor<?> publicConstructor = clas.getConstructor(int.class,String.class);
	Entity entity1 =(Entity)publicConstructor.newInstance(10,"value to me");
	System.out.println(entity1.getVal()+"  "+entity1.getType());
	
	Constructor<?> privateConstructor =clas.getDeclaredConstructor();
	privateConstructor.setAccessible(true);
	Entity entity2 =(Entity) privateConstructor.newInstance();
	System.out.println(entity2.getType()+"  "+entity2.getVal());

	}

}
