package com.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class Jun9_CmdLineAndActionClass extends BaseClass{
	
	
	public static void main(String[] args) throws Exception {
		
		actionClassEx4();
		
		
		
	}
	
	
	public static void actionClassEx1() throws Exception
	{
		launchBrowser();
		
		WebElement userName = driver.findElement(By.id("username"));
		
		Actions actions = new Actions(driver);
		
		actions.contextClick(userName).build().perform();
		
	}
	
	
	public static void actionClassEx2() throws Exception
	{
		launchBrowser();
		
		WebElement userName = driver.findElement(By.id("username"));		
		userName.sendKeys("admin");
		
		
		Actions act = new Actions(driver);		
		act.sendKeys(Keys.TAB).build().perform();
		
		act.sendKeys("manager").build().perform();
		
		act.sendKeys(Keys.TAB).build().perform();
		
		act.sendKeys(Keys.SPACE).build().perform();
		
		act.sendKeys(Keys.TAB).build().perform();
		
		act.sendKeys(Keys.ENTER).build().perform();
		
	}
	
	public static void actionClassEx3() throws Exception
	{
		launchBrowser();
		
		Actions act = new Actions(driver);		
	
		act.keyDown(Keys.SHIFT).sendKeys("admin").build().perform();
		
		act.keyUp(Keys.SHIFT);
		
		
	}
	
	
	public static void actionClassEx4() throws Exception
	{
		launchBrowser();
		
		driver.get("https://www.flipkart.com");
		
		driver.findElement(By.xpath("//button[text()='✕']")).click();
		
		Thread.sleep(2000);
		
		WebElement menu = driver.findElement(By.xpath("//div[text()='Electronics']"));
		
		Actions act = new Actions(driver);		
		
		act.moveToElement(menu).build().perform();
	
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Gaming")).click();
			
		
	}
	
	public static void runTimeExample() throws IOException
	{
		//String[] cmd = {"cmd","/c","calc"};
		
		//String[] cmd = {"cmd","/c","D:\\demo.vbs"};
		
		String[] cmd = {"cmd.exe","/c","TASKKILL  /IM firefox.exe"};
		
		//Runtime rt = Runtime.getRuntime();
		//rt.exec(cmd);
		
		Runtime.getRuntime().exec(cmd);
		
	}
	
	

}
