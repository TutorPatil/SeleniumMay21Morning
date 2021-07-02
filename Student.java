package com.actitime.tests;

public class Student {

	private static final Student student = new Student();
	
	// The constructor of this class is private to block object creation
	private Student()
	{
		
	}
	
	public static Student getInstance()
	{
		return student;
	}
	
	public void submitFess()
	{
		System.out.println("The student should submit his dues");
	}

}
