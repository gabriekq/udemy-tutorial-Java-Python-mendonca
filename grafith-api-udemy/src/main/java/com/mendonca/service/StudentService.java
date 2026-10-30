package com.mendonca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mendonca.model.Address;
import com.mendonca.model.Student;
import com.mendonca.model.Subject;
import com.mendonca.repository.AddressRepository;
import com.mendonca.repository.StudentRepository;
import com.mendonca.repository.SubjectRepository;
import com.mendonca.request.AlterStudentRequest;
import com.mendonca.request.CreateStudentRequest;
import com.mendonca.request.CreateSubjectRequest;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	public Student getStudentById (long id) {
		return studentRepository.findById(id).get();
	}
	
	
	public Student createStudent(CreateStudentRequest createStudentRequest) {
		
		Student student = new Student(createStudentRequest);
		
		Address address = new Address();
		address.setStreet(createStudentRequest.getStreet());
		address.setCity(createStudentRequest.getCity());
		
		address = addressRepository.save(address);
		
		student.setAddress(address);
		student = studentRepository.save(student);
		
		List<Subject> subjectList = new ArrayList<Subject>();
		
		if(createStudentRequest.getSubjectLearning() != null) {
			for(CreateSubjectRequest createSubjectRequest : createStudentRequest.getSubjectLearning()) {
				
				Subject subject = new Subject();
				subject.setSubjectName(createSubjectRequest.getSubjectName());
				subject.setMarksObtained(createSubjectRequest.getMarksObtained());
				subject.setStudent(student);
				
				subjectList.add(subject);
				
			}
			subjectRepository.saveAll(subjectList);
		}
		
		student.setLearningSubjects(subjectList);
		
		return student;
	}
	
	public Student alterStudent(AlterStudentRequest alterStudentRequest) {
		
		Student student =studentRepository.getOne(alterStudentRequest.getId());
		
		student.setFirstName(alterStudentRequest.getFirstName());
		student.setLastName(alterStudentRequest.getLastName());
		
		student.setEmail(alterStudentRequest.getEmail());
		
		  Address address =student.getAddress();
		
		  address.setCity(alterStudentRequest.getCity());
		  address.setStreet(alterStudentRequest.getStreet());
		  
		  addressRepository.save(address);
		  
		  student.setAddress(address);
		  
		  student =studentRepository.save(student);
		  return student;
		
	}
	
	
	
	
}
