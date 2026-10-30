package com.mendonca.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.mendonca.model.Person;
import com.mendonca.response.StudentResponse;
import com.mendonca.service.StudentService;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {

	@Autowired
	private StudentService studentService;
	
	
	public String firstQuery() {
		return "First Query";
	}
	
	public String secondQuery() {
		return "Second Query";
	}
	
	public String fullName(String firstName, String lastName) {
		return firstName+" "+lastName;
	}
	
	public String fullName2(Person person) {
		return person.getFirstName()+" "+person.getLastName()+" "+person.getAge();
	}
	
	public StudentResponse student(long id) {
		return new StudentResponse(studentService.getStudentById(id));
	}
	
	
}
