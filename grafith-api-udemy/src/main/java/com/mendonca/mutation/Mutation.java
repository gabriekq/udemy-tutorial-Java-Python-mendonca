package com.mendonca.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mendonca.model.Student;
import com.mendonca.request.AlterStudentRequest;
import com.mendonca.request.CreateStudentRequest;
import com.mendonca.response.StudentResponse;
import com.mendonca.service.StudentService;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Service
public class Mutation implements GraphQLMutationResolver {

	
	@Autowired
	private StudentService studentService;
	
	
	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
		
		Student student = studentService.createStudent(createStudentRequest);
		StudentResponse studentResponse	 = new StudentResponse(student);
		return studentResponse;
	}
	// create the alter version 
	
	
	public StudentResponse alterStudent(AlterStudentRequest alterStudentRequest) {
	Student student	=studentService.alterStudent(alterStudentRequest);
	StudentResponse studentResponse	 = new StudentResponse(student);
	return studentResponse;
	}
	
	
	
}
