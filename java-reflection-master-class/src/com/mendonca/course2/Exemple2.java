package com.mendonca.course2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Exemple2 {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Entity entity =  new Entity(254, "id");
		
		Class<? extends Entity> clss = entity.getClass();
		Method[] methods =clss.getMethods();
		for(Method method : methods) {
			//System.out.println(method.getName());
		}
		
		Method[] methodsDeclare =clss.getDeclaredMethods();
		for(Method method : methodsDeclare) {
			System.out.println(method.getName());
			
		}
		Method method = clss.getMethod("setVal", int.class);
		method.invoke(entity, 15);
		
		Method method2 = clss.getDeclaredMethod("getVal", null);
		method2.setAccessible(true);
		int value = (int) method2.invoke(entity, null);
		System.out.println(value);
		
		
	}

}
