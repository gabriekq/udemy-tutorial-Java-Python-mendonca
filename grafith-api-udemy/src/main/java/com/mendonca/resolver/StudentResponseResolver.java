package com.mendonca.resolver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


import com.mendonca.enums.SubjectNameFilter;
import com.mendonca.model.Subject;
import com.mendonca.response.StudentResponse;
import com.mendonca.response.SubjectResponse;

import graphql.kickstart.tools.GraphQLResolver;

@Service
public class StudentResponseResolver  implements GraphQLResolver<StudentResponse>{

	public List<SubjectResponse> getLearningSubjects (StudentResponse studentResponse,List<SubjectNameFilter> subjectNameFilter){
		
		   ArrayList<SubjectResponse> learningSubjects = new ArrayList<SubjectResponse>(); 
		if (studentResponse.getStudent().getLearningSubjects() != null) { 
			 
			  for (Subject subject: studentResponse.getStudent().getLearningSubjects()) {
				 
				    boolean isAll = subjectNameFilter.stream().filter(subject1 -> subject1.name().contentEquals(SubjectNameFilter.All.name())).findFirst().isPresent();
					if(isAll) {
						learningSubjects.add(new SubjectResponse(subject)); 
					}else {
						
						boolean containsAny = subjectNameFilter.stream().filter( subject2 -> subject2.name().contentEquals(subject.getSubjectName())).findFirst().isPresent();
						if(containsAny) {
							learningSubjects.add(new SubjectResponse(subject)); 
						}
						
					}
				    
			     } 
			  }
	
	return learningSubjects;
	}
	
	public String getFullName(StudentResponse studentResponse) {
		return studentResponse.getFirstName()+" "+studentResponse.getLastName();
	}
	

	
}
