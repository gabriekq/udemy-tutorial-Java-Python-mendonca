package com.mendonca.course2.orm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hibernate<T> {

	private Connection con;
	
	private AtomicLong id = new AtomicLong(0L);
	
	public static <T> Hibernate getConnection() throws SQLException {
		return new Hibernate<T>();
	}
	
	private Hibernate() throws SQLException {
		
			this.con = DriverManager.getConnection("jdbc:h2:~/test","sa","");
	
	}

	public void write(T t) throws IllegalArgumentException, IllegalAccessException, SQLException {
		Class<? extends Object> cls = t.getClass();
		
		Field[] declaredFields = cls.getDeclaredFields();
		Field pkey=null;
		ArrayList<Field> colluns =  new ArrayList<>();
		StringJoiner joiner =  new StringJoiner(",");
		
		for(Field field:declaredFields ) {
			field.setAccessible(true);
			if(field.isAnnotationPresent(PrimaryKey.class)) {
				pkey=field;
				
				System.out.println("The PrimaryKey is"+field.getName()+" value"+field.get(t)+" and the colluns are :");
			}else {
				if(field.isAnnotationPresent(Column.class)) {
					joiner.add(field.getName());
					colluns.add(field);
					System.out.println("The Collun is "+field.getName()+"Value is -> "+field.get(t));
				}
			}
			
		}
		int number = joiner.toString().split(",").length+1;
		String qMarks= IntStream.range(0, number).mapToObj(obj-> "?").collect(Collectors.joining(",")  );
		String sql = "insert into "+cls.getSimpleName()+"( "+pkey.getName()+","+joiner.toString()+")"+"values ("+qMarks+")";
		PreparedStatement stament = con.prepareStatement(sql);
		
		if(pkey.getType()== long.class ) {
			stament.setLong(1, id.incrementAndGet());
		}
		
		int index=2;
		for(Field field: colluns ) {
			field.setAccessible(true);
			
			if(field.getType()==int.class) {
			int intValue =	 field.getInt(t);	
				stament.setInt(index++, intValue);
			}else {
				
				if(field.getType()==String.class) {
					String stringValue =	 (String) field.get(t);
					stament.setString(index++, stringValue);
				}
				
			}
			
		}
				
		stament.executeUpdate();
	
	}

	public T read(Class<T> class1, long l) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		Field fieldPk = null;
		
		Field []declaredFields = class1.getDeclaredFields();
		
		for(Field field : declaredFields) {
			field.setAccessible(true);
			
			if(field.isAnnotationPresent(PrimaryKey.class)) {
				fieldPk = field;
				break;
			}
			
		}
		
		String sql = "select * from "+class1.getSimpleName()+" where "+fieldPk.getName()+" ="+l;
		
		PreparedStatement stament = con.prepareStatement(sql);
		ResultSet resultSet = stament.executeQuery();
		resultSet.next();
		
		T t=class1.getConstructor().newInstance();
		long transactionId = resultSet.getInt(fieldPk.getName());
		fieldPk.setAccessible(true);
		fieldPk.set(t,transactionId);
		
		for(Field field: declaredFields) {
			   
			if(field.isAnnotationPresent(Column.class)) {
			   field.setAccessible(true);
			   if(field.getType()== int.class) {
				   field.set(t, resultSet.getInt(field.getName()));
			   }else {
				   
				   if(field.getType()== String.class) {
					   String value = resultSet.getString(field.getName());
					   field.set(t, value);
				   }
				   
			   }
				
			}
		}
		return t;
	}
	
	
	
	
	
}
