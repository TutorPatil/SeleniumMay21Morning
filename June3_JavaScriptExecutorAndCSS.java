package com.selenium;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;

public class June3_JavaScriptExecutorAndCSS {
	
	static WebDriver driver ;

	public static void main(String[] args) throws IOException {
		
		launchBrowser("https://demo.actitime.com/login.do");
		
		JavascriptExecutor jsEx = 	(JavascriptExecutor)driver;
		String title = (String) jsEx.executeScript("return document.title"); 
				
		System.out.println(title);
		
		long links = (Long) jsEx.executeScript("return document.getElementsByTagName('a').length");
		
		System.out.println(links);
		
		
		WebElement username = driver.findElement(By.id("username"));
		WebElement pwd = driver.findElement(By.name("pwd"));
		WebElement loginbtn = driver.findElement(By.id("loginButton"));
		
		jsEx.executeScript("arguments[0].value='admin'", username);	
		jsEx.executeScript("arguments[0].value='manager'", pwd);		
		jsEx.executeScript("arguments[0].click()", loginbtn);
		
		//driver.close();
		
	}
	
	public static void cssEx()
	{
		/*
		 * input[id='username']
		input#username
		input#username.textField
		input[id='username'][class='textField']
		table[class='footer']
		table.footer
		#loginButton
		.footer tbody tr td:nth-child(2)
		.footer>tbody>tr>td:nth-child(2)
		.footer>tbody>tr>td:first-child
		.footer>tbody>tr>td:last-child
		a[id*='login']  // contains 


		 */
		launchBrowser("https://demo.actitime.com/login.do");		
		driver.findElement(By.cssSelector("#username.textField")).sendKeys("admin");
		driver.findElement(By.cssSelector("input[name='pwd']")).sendKeys("manager");
	}
	
	
	public static void launchBrowser(String url)
	{
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");		
		driver = new ChromeDriver();		
		driver.get(url);		
	}
	
	public static void closeBrowser()
	{
		driver.close();
	}


}
