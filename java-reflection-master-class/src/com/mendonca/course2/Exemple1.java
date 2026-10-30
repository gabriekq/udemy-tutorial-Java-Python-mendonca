package com.mendonca.course2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


class MyClass {
	
	private MyClass() {
		System.out.println("My class object");
	}
	
}

public class Exemple1 {

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchFieldException, SecurityException {
		
		String className = MyClass.class.getName();
		
		Class<?> myClass = Class.forName(className);
		Constructor<?>[] constructor = myClass.getDeclaredConstructors();
		constructor[0].setAccessible(true);
		Object object = constructor[0].newInstance();
		System.out.println(object);
		
		Class<?> class1 = Class.forName("java.lang.String");
		Class<?> class2 = Class.forName("java.lang.String");
		
		System.out.println(class1==class2);
		
		// new episode
		Entity emEntity = new Entity(10, "id");
		Class<? extends Entity> clss = emEntity.getClass();
		
		/*
		 * Field[] fields = clss.getFields();
		 * 
		 * for(Field field: fields) { System.out.println(field.getName()); }
		 */
		
		Field[] decleradFields = clss.getDeclaredFields();
		
		for(Field field:decleradFields ) {
			System.out.println(field.getName());
		}
		
		Field field = clss.getField("type");
		field.set(emEntity, "rollNo.");
		System.out.println(emEntity.getType());
		
		Field fieldVal = clss.getDeclaredField("val");
		fieldVal.setAccessible(true);
		fieldVal.set(emEntity, 32);
		System.out.println(emEntity.getVal());
		
		
	}

}
