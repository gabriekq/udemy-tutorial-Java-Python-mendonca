package com.mendonca.model;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {


	private String firstName;
	private String lastName;
	private int age;
	

	/*
	 * public Person(String firstName, String lastName, int age) { super();
	 * this.firstName = firstName; this.lastName = lastName; this.age = age; }
	 */
	

	
}
