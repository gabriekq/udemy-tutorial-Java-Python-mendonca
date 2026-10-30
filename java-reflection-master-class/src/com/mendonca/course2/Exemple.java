package com.mendonca.course2;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Exemple {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Entity entity = new Entity(524, "id");
		Class<? extends Entity> clss = entity.getClass();
		int modifierInt = clss.getModifiers();
	//	int resultModfier =  modifierInt & Modifier.PUBLIC;
		boolean resultModfier =  Modifier.isPublic(modifierInt);
		System.out.println(resultModfier);
		
		Method method = clss.getMethod("getVal");
		int methodModifiers=	method.getModifiers();
		int methodModifiersInt =  methodModifiers & Modifier.PRIVATE;
		System.out.println(methodModifiersInt);

	}

}
