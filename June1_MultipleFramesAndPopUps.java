package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class June1_MultipleFramesAndPopUps {
	
	static WebDriver driver ;

	public static void main(String[] args) throws InterruptedException {
		
		

	}
	
	public static void handlingPopUps() throws InterruptedException
	{
		
		launchBrowser("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert");		
		driver.switchTo().frame("iframeResult");		
		driver.findElement(By.xpath("//button[text()='Try it']")).click();		
		Thread.sleep(2000);
		
		// To click on Ok/Yes Button of Alert
		driver.switchTo().alert().accept();
		
		// To click on Cancel / No button
		//driver.switchTo().alert().dismiss();		
		
		// To get the Text from the alert
		//String alertText = driver.switchTo().alert().getText();
		//System.out.println(alertText);
	}
	
	public static void handlingFrames()
	{
		

		launchBrowser("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select_multiple");
		
		//switching to frame using Name or ID
		//driver.switchTo().frame("iframeResult");
		
		// using the index
		//driver.switchTo().frame(0);
		
		// using WebElement for Frame
		WebElement iFrame = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
		
		driver.switchTo().frame(iFrame);
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		// switch back to the main  html Page from Frame
		driver.switchTo().defaultContent();
		
		closeBrowser();
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
