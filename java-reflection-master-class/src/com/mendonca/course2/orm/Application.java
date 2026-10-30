package com.mendonca.course2.orm;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Application {

	public static void main(String[] args) throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
	TransactionHistory sanggeta = new TransactionHistory( 15331,"Sangeeta" , "Credit", 1000);
	TransactionHistory gabriel = new TransactionHistory( 15331,"gabriel" , "Credit", 9000);
	TransactionHistory josh = new TransactionHistory( 15331,"josh" , "Credit", 7000);
	
	Hibernate<TransactionHistory> hibernate = Hibernate.getConnection();
	hibernate.write(sanggeta);
	hibernate.write(gabriel);
	hibernate.write(josh);
	
	TransactionHistory obj1= hibernate.read(TransactionHistory.class,1L);
	System.out.println(obj1);
		
		

	}

}
