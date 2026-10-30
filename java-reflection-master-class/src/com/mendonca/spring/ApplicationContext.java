package com.mendonca.spring;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;

import com.mendonca.spring.annotarion.Autowired;
import com.mendonca.spring.annotarion.Component;
import com.mendonca.spring.annotarion.ComponentScan;
import com.mendonca.spring.annotarion.Configuration;

public class ApplicationContext {
     private static HashMap<Class<?>, Object> map = new HashMap<>();
	
     public ApplicationContext(Class<?> class1 ) { //AppConfig
		
		Spring.initalizeSpringContext(class1);
	}
	
	
	
	
	
	private static class Spring{
		private static void initalizeSpringContext(Class<?> cls)  {
			if(!cls.isAnnotationPresent(Configuration.class)) {
				throw new RuntimeException("The file is not a Config file");
			}else {
				ComponentScan annotation =cls.getAnnotation(ComponentScan.class);
				String value = annotation.value();
				String packageStructure = "bin/"+value.replace(".","/");
				File[] files=findClasses(new File(packageStructure));
				
				for(File file:files) {
					try {
					String nameClass = value+"."+file.getName().replace(".class","");
					Class<?> loadingClass = Class.forName(nameClass);
					if(loadingClass.isAnnotationPresent(Component.class)) {
						Constructor<?> constructor = loadingClass.getConstructor();
						Object newInstance=constructor.newInstance();
						map.put(loadingClass, newInstance);
					}
					}catch (Exception e) {
						System.out.println(e.toString());
					}
				}
				
				
			}
		}

		private static File[] findClasses(File file) {
			
			if(!file.exists()) {
				throw new RuntimeException("Pakage "+file+"does not exist.");
			}else {
				
			File[] listFiles=file.listFiles(e -> e.getName().endsWith(".class") );
			return listFiles;
			}
			
			
		
		}
	}





	public <T> T getBean(Class<T> class1) {
		
		T object = (T)map.get(class1);
		
		Field[] declaredFilds= class1.getDeclaredFields();
		
		injectBean(object,declaredFilds);
		
		return object ;
	}





	private <T> void injectBean(T object, Field[] declaredFilds) {
	
		for(Field field:declaredFilds ) {
			
			if(field.isAnnotationPresent(Autowired.class)) {
				field.setAccessible(true);
				Class<?> type = field.getType();
				Object innerObject=map.get(type);
				try {
					field.set(object, innerObject);
					Field[] declaredFilds2=  type.getDeclaredFields();
					injectBean(innerObject,declaredFilds2);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
		
		
	}

}
