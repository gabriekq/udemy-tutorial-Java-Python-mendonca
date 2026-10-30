package com.mendonca;

public class Person {

	private String name;
	private int age;
	private Address address;
	
	public Person() {
		this.name="anonymous";
		this.age=0;
		this.address=null;
	}
	
	public Person(String name) {
		this.name=name;
		this.age=0;
		this.address=null;
	}
	
	public Person(String name,int age) {
		this.name=name;
		this.age=age;
		this.address=null;
	}
	
	public Person(Address address,String name,int age) {
		this.name=name;
		this.age=age;
		this.address=address;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", address=" + address + "]";
	}
	
	
}
