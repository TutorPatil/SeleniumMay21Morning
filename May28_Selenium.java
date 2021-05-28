package com.selenium;

import java.util.List;

import javax.security.auth.callback.LanguageCallback;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class May28_Selenium {
	
	static WebDriver driver ;
	
	
	public static void main(String[] args) throws InterruptedException {
		
		
		launchBrowser("file:///C:/Users/sudhapat/Desktop/tempauto/temp/May_Selenium_Morning/LoginPage.html");
		
		driver.get("https://demo.actitime.com/login.do");
		
		// in one line
		//driver.navigate().refresh();
		
		
		WebDriver.Navigation wn = driver.navigate();
		
		wn.back();
		
		Thread.sleep(2000);
		
		wn.forward();
		
		
		Thread.sleep(2000);
		
		wn.refresh();
		

		Thread.sleep(2000);
		
	}
	
	
	public static void multiSelectEx1() throws InterruptedException
	{

		launchBrowser("file:///C:/Users/sudhapat/Desktop/tempauto/temp/May_Selenium_Morning/LoginPage.html");
		
		//Creating WebElement for the dropdown
		WebElement cars = driver.findElement(By.id("cars1"));
		
		// Creating the object of the select class and passing the webElement as constructor argument
		Select carSelect = new Select(cars);
		
		// To check whether its single Selection or multi Selection
		System.out.println(carSelect.isMultiple());
		
		carSelect.selectByVisibleText("Opel");
		
		Thread.sleep(2000);
		
		carSelect.selectByValue("audi");
		
		Thread.sleep(2000);
		
		carSelect.selectByIndex(0);
		
		Thread.sleep(2000);
		
		List<WebElement> carList = carSelect.getAllSelectedOptions();
		
		for(WebElement m : carList)
		{
			System.out.println(m.getText());
		}
		
		carSelect.deselectByVisibleText("Opel");
		
		// To deselect all options which were selected...
		carSelect.deselectAll();
		
		//To get all options
		carSelect.getOptions();
		
		
		closeBrowser();
		
		
	}
	
	
	public static void dropDownExample1() throws InterruptedException
	{

		launchBrowser("file:///C:/Users/sudhapat/Desktop/tempauto/temp/May_Selenium_Morning/LoginPage.html");
		
		//Create the object  of Dropdown which you want to work on...
		WebElement dropDown  = driver.findElement(By.id("state"));
		
		//Create the object of Select class and pass the webElement ( dropdown ) as constructor argument
		Select comboSelect = new Select(dropDown);
		
		comboSelect.selectByVisibleText("Andhra");
		
		Thread.sleep(3000);
		
		comboSelect.selectByIndex(2);
		
		Thread.sleep(3000);
		
		
		comboSelect.selectByValue("KA");
		
		//To get the selected option
		WebElement item = comboSelect.getFirstSelectedOption();
		
		System.out.println(item.getText());
		
		// To get all the options of dropdown
		List<WebElement> items = comboSelect.getOptions();
		
		
		System.out.println("No of items in dropdown  "+items.size());
		
		for(WebElement m : items)
		{
			System.out.println(m.getText());
		}
		
		System.out.println("============");
		
		for(int x=0; x<items.size();x++)
		{
			System.out.println(items.get(x).getText());
			
		}
		
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
