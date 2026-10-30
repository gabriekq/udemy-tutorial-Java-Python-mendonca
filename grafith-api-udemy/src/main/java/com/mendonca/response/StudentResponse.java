package com.mendonca.response;

import java.util.ArrayList;
import java.util.List;

import com.mendonca.model.Student;
import com.mendonca.model.Subject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
	
	private long id;

	private String firstName;

	private String lastName;

	private String email;
	
	private String street;

	private String city;
	
	private List<SubjectResponse> learningSubjects;
	
	private String fullName;
	
	
	
	//this for internal use not in schema
	private Student student;
	
	public StudentResponse (Student student) {
		this.student = student;
		this.id = student.getId();
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		this.email = student.getEmail();
		
		this.street = student.getAddress().getStreet();
		this.city = student.getAddress().getCity();
	}
	
	
}
