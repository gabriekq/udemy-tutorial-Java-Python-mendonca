package com.mendonca.course2;


public class Utility {

	void doStuff() {
		System.out.println("");
	}
	
	@MostUsed("Python")
	void doStuff(String args) {
		System.out.println("Operating on :"+args);
	}
	
	void doStuff(int args) {
		System.out.println("Operating on :"+args);
	}
	
}

class SubUtility extends Utility{
	
}
