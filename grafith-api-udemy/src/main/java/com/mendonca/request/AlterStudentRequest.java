package com.mendonca.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlterStudentRequest {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String street;
	private String city;
}
