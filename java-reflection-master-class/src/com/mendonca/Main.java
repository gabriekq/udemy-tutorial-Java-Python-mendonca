package com.mendonca;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Main {
	
	public static void main(String [] args) throws ClassNotFoundException {
		
		Class<String> stringClass = String.class;
		
		Map<String,Integer> mapObject = new HashMap<>();
		
		Class<?> hashMapClass = mapObject.getClass();
		Class<?> squareClass = Class.forName("com.mendonca.Main$Square");
		
		//printClassInfo(stringClass,hashMapClass,squareClass);
		

		
		var circleObject =new Drawable() {
			
			@Override
			public int getNumberOfCorners() {
				return 0;
			}
		};
		

		printClassInfo(Collection.class ,Boolean.class, int [][].class,Color.class,circleObject.getClass());
	}
	


	private static void printClassInfo(Class<?> ... classes) {
		
		for(Class<?> clazz : classes  ) {
			
			System.out.println(String.format("class name: %s, class package name: %s ",clazz.getSimpleName(),clazz.getPackageName()));
			
			Class<?> [] implementedInterfaces = clazz.getInterfaces();
			
			for(Class<?>  implementedInterface: implementedInterfaces ) {
				
				System.out.println(String.format("class name: %s, class package name: %s ",clazz.getSimpleName(),implementedInterface.getSimpleName()));
				
			}
			
			System.out.println("is array : "+clazz.isArray());
			System.out.println("is primitive : "+clazz.isPrimitive());
			System.out.println("Is enum : "+clazz.isRecord());
			System.out.println("is anonymous : "+clazz.isAnonymousClass());
			System.out.println("is interface : "+clazz.isInterface());
			
			System.out.println();
			System.out.println();
		}
	}
	

	private static class Square implements Drawable{

		@Override
		public int getNumberOfCorners() {
			return 4;
		}
		
	}
	
	
	private static interface Drawable{
		int getNumberOfCorners();
	}
	
	private enum Color{
		BLUE,
		RED,
		GREEN
	}
	
}
