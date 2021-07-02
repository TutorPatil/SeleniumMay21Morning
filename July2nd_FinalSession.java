package com.actitime.tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class July2nd_FinalSession {
	
	enum DAYS  {
		
		SUNDAY,
		MONDAY,
		TUESDAY,
		WEDNESDAY,
		THRUSDAY,
		FRIDAY,
		SATURDAY;
		
}

	
	enum DIRECTIONS {
		
		EAST,
		WEST,
		NORTH,
		SOUTH;
		
	}
	public static void main(String[] args) {
		
		/*
		
		System.out.println(DAYS.SUNDAY);
		
		DAYS d = DAYS.SUNDAY;
		
		switch (d)
		{
		case SUNDAY:
		{
			System.out.println("its holiday and pls relax..");
			break;
		}
		case MONDAY:
		{
			System.out.println("Please go to work...");
			break;
		}
		
		
		
		}
		
		for(DAYS m : DAYS.values())
		{
			System.out.println(m);
		}
		
		System.out.println(d);
		
		Student s = Student.getInstance();	
		s.submitFess();
		
		Student s1 = Student.getInstance();
		s1.submitFess();
		*/
		
		regxExample();
	}

	
	public static void regxExample()
	{
		
		Pattern p = Pattern.compile(".s");//. represents single character
		Matcher m = p.matcher("abcds");  
		
		boolean b = m.matches();
		
		System.out.println(b);
		
		
		//2nd way  
		boolean b2=Pattern.compile(".s").matcher("as").matches();  
		
		System.out.println(b2);
		
		boolean b3 = Pattern.matches(".s", "as");  
		System.out.println(b3);
		
		System.out.println(Pattern.matches("[amn]?", "x"));//true (a or m or n comes one time)
		
		System.out.println(Pattern.matches("[amn]?", "aaa"));//false (a comes more than one time)  
		System.out.println(Pattern.matches("[amn]+", "a"));//true (a or m or n once or more times)  
		System.out.println(Pattern.matches("[amn]+", "aaa"));//true (a comes more than one time) 
		
		System.out.println(Pattern.matches("[a-zA-Z0-9]", "8"));
		
		System.out.println(Pattern.matches("\\D*", "mak13"));//true (non-digit and may come 0 or more times)
		
		System.out.println(Pattern.matches("\\d*", "112ab"));//true (digit and comes once) 
		
		System.out.println(Pattern.matches("[a-zA-Z0-9]{6}", "arun32123"));//true
	}
}
