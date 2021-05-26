package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class May26_Selenium {

	public static void main(String[] args) {
	
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://demo.actitime.com/login.do");
		
		System.out.println(driver.getCurrentUrl());
		
		System.out.println(driver.getTitle());
		
		//System.out.println(driver.getPageSource());
		
		//driver.close();
		
		By x = By.xpath("//input[@id='username']");
		
		WebElement userName = driver.findElement(x);
		
		userName.sendKeys("admin");
		
		driver.findElement(By.xpath("//input[@name='pwd']")).sendKeys("manager");
		
		driver.findElement(By.xpath("//input[@id='keepLoggedInCheckBox']")).click();
		
		boolean isSelected = driver.findElement(By.xpath("//input[@id='keepLoggedInCheckBox']")).isSelected();
		
		System.out.println(isSelected);
		
		
		System.out.println(driver.findElement(By.xpath("//a[@id='loginButton']")).isDisplayed());
		
		
		//Get Text to fetch the text from element
		String text = driver.findElement(By.xpath("//a[@href='http://www.actitime.com']")).getText();
		
		System.out.println(text);
		
		// to fetch any attribute value for an element
		
		String attVal = driver.findElement(By.xpath("//input[@id='username']")).getAttribute("class");
		
		System.out.println(attVal);
		
		

	}

}
