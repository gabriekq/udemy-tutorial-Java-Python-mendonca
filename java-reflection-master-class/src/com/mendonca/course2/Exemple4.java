package com.mendonca.course2;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Exemple4 {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
	Class<?> clss=Class.forName("com.mendonca.course2.Utility");
	Constructor<?> constructor=clss.getConstructor();
	Utility utility = (Utility)  constructor.newInstance();
	
	 Method[] methods  = clss.getDeclaredMethods();
	 
	 for(Method method :methods  ) {
		 if(method.isAnnotationPresent(MostUsed.class) && !method.isSynthetic()) {
			
			MostUsed annotation = method.getAnnotation(MostUsed.class);
			 String annotationValue = annotation.value(); 
			 method.invoke(utility, annotationValue);
		 }
	 }
	
	}

}
