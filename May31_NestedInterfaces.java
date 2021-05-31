package com.selenium;



import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class May31_NestedInterfaces {
	
	static WebDriver driver ;
	
	

	public static void main(String[] args) throws InterruptedException {
		
		launchBrowser("https://demo.actitime.com/login.do");
		
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//a[text()='actiTIME Inc.']")).click();
		
		Set<String> wins = driver.getWindowHandles();
		
		Iterator<String> itr = wins.iterator();
		
		String firstWindow = itr.next();
		
		String secondWindow = itr.next();
		
		System.out.println(firstWindow);
		
		System.out.println(secondWindow);
		
		driver.switchTo().window(secondWindow);
		
		System.out.println(driver.getTitle());	
		
		driver.switchTo().window(firstWindow);
		
		System.out.println(driver.getTitle());
		
		//will close only the active window where the focus is switched
		driver.close();
		
		//driver.quit();   This will close all the windows/Tabs which are opend by Selenium programatically
		
		
		
		
		
	}
	
	public static void windowOperations() throws InterruptedException
	{
		launchBrowser("https://demo.actitime.com/login.do");
		
		WebDriver.Options wo = driver.manage();
		WebDriver.Window ww =  wo.window();		
		ww.maximize();
		
		Thread.sleep(2000);
		
		// all in one line
		//driver.manage().window().maximize();
		
		
		// setting the position of the window
		Point p = new Point(-2000,-2000);
		
		driver.manage().window().setPosition(p);
		
		Thread.sleep(2000);
		
		
		driver.manage().window().maximize();
		
		
		//Setting the size of the window
		
		Dimension d = new Dimension(300,300);
		
		Point p1 = new Point(200,200);
		
		driver.manage().window().setSize(d);
		
		Thread.sleep(2000);
		
		driver.manage().window().setPosition(p1);
		
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");		
		WebDriver driver1 = new ChromeDriver();
		
		driver1.get("https://google.com");
		
		driver1.manage().window().setSize(d);
		
		driver1.manage().window().setPosition(new Point(600,600));
		
		System.out.println(driver1.getTitle());
		
		System.out.println(driver.getTitle());

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
