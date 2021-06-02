package com.selenium;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;

public class June2_ScreenshotCSS {
	
	static WebDriver driver ;

	public static void main(String[] args) throws IOException {
		
		launchBrowser("https://demo.actitime.com/login.do");
		captureScreenShot("Login_001");
		
		
	}
	
	public static void captureScreenShot(String fileName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);		
		File dest = new File("./results/screenshots/"+fileName+".png");
		
		Files.copy(src, dest);
		
	}
	
	
	public static void takeScreenShotEx1() throws IOException
	{

		//byte b = (byte)150;
		
		launchBrowser("https://demo.actitime.com/login.do");
		
		
		// Down casting the driver reference to Take Screenshot level
		TakesScreenshot ts = (TakesScreenshot)driver;
		

		File src = ts.getScreenshotAs(OutputType.FILE);		
		File dest = new File("D:\\screenshot.png");		
		Files.copy(src, dest);
		
		
		//File f = new File("D:\\test.txt");		
		//f.createNewFile();
		
		// In one line
	   // File src = 	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
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
