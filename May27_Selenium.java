package com.selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class May27_Selenium {
	
	static WebDriver driver ;
	
	
	
	
	public static void differentlocators()
	{
		//driver.get("https://demo.actitime.com/login.do");
		
		launchBrowser();
		driver.findElement(By.id("username")).sendKeys("admin");  // Using id
		driver.findElement(By.name("pwd")).sendKeys("manager"); // UsingName
		
		//driver.findElement(By.linkText("actiTIME Inc.")).click();		// LinkText
		//driver.findElement(By.partialLinkText("actiTIME")).click();	// Partial Link Text (like contains)	
		//driver.findElement(By.className("textField")).sendKeys("Test"); // classname
		//driver.findElement(By.tagName("//a")).click();  // TageName
		//driver.findElement(By.xpath("//input[@id='username']")).sendKeys("admin"); // Xpath
		
		
		
	}
	
	
	
	
	
	public static void findElements2(String[] args) throws InterruptedException {
		
		//driver.get("https://www.google.com/");
		launchBrowser();
		
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Download selenium");
		
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//input[@name='btnK'])[1]")).click();
		Thread.sleep(4000);
		
		List<WebElement> links = driver.findElements(By.xpath("//h3"));
		
		for(WebElement m : links)
		{
			System.out.println(m.getText());
		}
		
		
	}
	
	
	
	public static void findElements1(String[] args) {
		
		//URL = file:///C:/Users/sudhapat/Desktop/tempauto/temp/May_Selenium_Morning/LoginPage.html
		
		launchBrowser();	
		
		/*
		List<String> str = new ArrayList<String>();		
		str.add("selenium");
		str.add("Automation");
		str.add("testing");
		
		for ( int x=0; x<str.size();x++)
		{
			System.out.println(str.get(x));
		}
		*/
		
		List<WebElement> emails = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[4]"));		
		System.out.println("Total email ids are "+emails.size());		
		
		for(int x=0; x<emails.size();x++)
		{
			System.out.println(emails.get(x).getText());
		}	
		
		
		closeBrowser();

		
	}
	

	
	
	
	
	public static void launchBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");		
		driver = new ChromeDriver();		
		driver.get("https://demo.actitime.com/login.do");
		//driver.get("https://www.google.com/");
	}
	
	public static void closeBrowser()
	{
		driver.close();
	}
}
