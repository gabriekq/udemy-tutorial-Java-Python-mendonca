package com.mendonca;

import java.lang.reflect.Array;

public class Main6 {

	public static void main(String[] args) {
		// Arrays
		
		int[] oneDimensionalArray= {1,2};
		
		double [] [] towDimentionalArrays = {{1.5,2.5},{1.5,2.5} };
		
		//inspectArrayObject(oneDimensionalArray);
		
		//inspectArrayObject(towDimentionalArrays);
		
	//	inspectArrayValues(oneDimensionalArray);
		inspectArrayValues(towDimentionalArrays);

	}
	
	
	public static void inspectArrayValues(Object arrayObject) {
		
		int arrayLength = Array.getLength(arrayObject);
		
		System.out.print("[");
		
		for(int index=0;index<arrayLength;index++) {
			
			Object element = Array.get(arrayObject, index);
			
			if(element.getClass().isArray()) {
				inspectArrayValues(element);
			}else {
				System.out.print(element);
			}
			
			
			
			if(index != arrayLength -1) {
				System.out.print(" , ");
			}
				
		}
		System.out.print("]");
		
	}
	
	
	
	
	public static void inspectArrayObject(Object arrayObject) {
		
		Class<?> clazz = arrayObject.getClass();
		
		System.out.println(String.format("Is Array : %s", clazz.isArray()));
		
		Class<?> arrayComponentType = clazz.getComponentType();
		
		System.out.println(String.format("This is a Array of type : %s", arrayComponentType.getTypeName()));
		
	}
	

}
