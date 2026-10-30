package com.mendonca.reflectionPerformance;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;

public class MethodHandlesDemo {

	public static void main(String[] args) throws Throwable {
		
		Lookup lookup = MethodHandles.lookup();
		Class<?> clss=lookup.findClass(Student.class.getName());
		
		MethodType methodType = MethodType.methodType(String.class);
		
	   MethodHandle getCourseHandle = lookup.findVirtual(Student.class, "getCourse", methodType);

	   Student student = new Student();
	   student.setCourse("Test");
			 
	   String valueReturn = (String) getCourseHandle.invoke(student);
	   System.out.println(valueReturn);
	 
	   MethodType methodType1 = MethodType.methodType(void.class); 
	  MethodHandle noArg = lookup.findConstructor(clss, methodType1);
	   
	  
	  Student student2=  (Student) noArg.invoke();
	  student2.setCourse("Test1234");
	  System.out.println(student2);
	  
	  MethodType type1 = MethodType.methodType(void.class,String.class,String.class);
	  MethodHandle paranConstructor =lookup.findConstructor(clss, type1);
	  Student student3=  (Student) paranConstructor.invoke("bom dia","Java8");
	  System.out.println(student3);
	  
	  MethodType type2 = MethodType.methodType(void.class,String.class);
	  MethodHandle setName = lookup.findVirtual(clss, "setName", type2);
	  setName.invoke(student3,"Gabriel");
	  
	  
	  MethodType type3 = MethodType.methodType(void.class,int.class);
	  MethodHandle setNameStatic = lookup.findStatic(clss, "setNumOfStudents", type3);
	  setNameStatic.invoke(54);
	  System.out.println(student3.getNumOfStudents());
	  
	  
	 Lookup privateLookup = MethodHandles.privateLookupIn(clss, lookup);
	  
	 MethodHandle findGetter = privateLookup.findGetter(clss, "name", String.class);
	 MethodHandle findSetter = privateLookup.findSetter(clss, "name", String.class);
	 findSetter.invoke(student3,"Mario");
	 findGetter.invoke(student3);
	  
	 System.out.println(student3);
	 // VarHandle
	 
	 VarHandle courseVarHandle = privateLookup.findVarHandle(clss, "course", String.class);
	 courseVarHandle.set(student3,"Java 32");
	 String value =  (String) courseVarHandle.get(student3);
	 System.out.println("VarHandle -> "+value);
	 
	  
	}

}
