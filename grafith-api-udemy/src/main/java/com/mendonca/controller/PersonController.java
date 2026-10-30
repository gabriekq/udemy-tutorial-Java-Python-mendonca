package com.mendonca.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mendonca.model.Person;

@RestController
public class PersonController {

	
	@RequestMapping(value = "/foo", method = RequestMethod.GET)
	public Person getPerson() {
		Person person = new Person("Gabriel", "Name", 23);
		
		return person;
	}
	
	
	
	
}
