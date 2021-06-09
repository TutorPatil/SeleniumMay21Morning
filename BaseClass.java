package com.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {
	
	static WebDriver driver ;
	
	public static void launchBrowser() throws IOException
	{
		
		String browser = getConfigData("browser");
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./utilities/chromedriver.exe");		
			driver = new ChromeDriver();		
			
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./utilities/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		String timeout = getConfigData("implicitwaittimeout");
		long time = Long.parseLong(timeout);
		
		// Implicit wait applicable through out the life cycle of the driver..
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);  // polling interval of 500 MS
		
		String url = getConfigData("url");
		driver.get(url);
	}
	
	public static String getConfigData(String key) throws IOException
	{
		String value = "";
		
		File f = new File("./data/config.properties");	
		
		// Creating the object of FileInputStream to load the file as stream
		FileInputStream fis = new FileInputStream(f);
		
		// Creating the object of properties file
		Properties prop = new Properties();
		
		// Loading the stream
		prop.load(fis);
		
		
		value = prop.getProperty(key);
	
		
		
		return value;
	}
	

	public static String getLocatorData(String pageName, String elementName) throws IOException
	{
		String locator="";
		
		File f = new File("./data/locatordata.xlsx");
		
		FileInputStream fio = new FileInputStream(f);
		
		XSSFWorkbook wb = new XSSFWorkbook(fio);
		
		XSSFSheet ws = wb.getSheet("Sheet1");
		
		int rows = ws.getLastRowNum();
		
		System.out.println(rows);
		
		for(int x=1;x<=rows;x++)
		{
			String page = ws.getRow(x).getCell(0).getStringCellValue();
			String element = ws.getRow(x).getCell(1).getStringCellValue();
			
			if((pageName.equalsIgnoreCase(page)) && (elementName.equalsIgnoreCase(element)))
			{
				locator = ws.getRow(x).getCell(2).getStringCellValue();
				break;
			}
			
		}		
		wb.close();
		return locator;
	}
	
	public static String getTestData(String pageName, String elementName) throws IOException
	{
		String testData="";
		
		File f = new File("./data/testdata.xlsx");
		
		FileInputStream fio = new FileInputStream(f);
		
		XSSFWorkbook wb = new XSSFWorkbook(fio);
		
		XSSFSheet ws = wb.getSheet("Sheet1");
		
		int rows = ws.getLastRowNum();
		
		System.out.println(rows);
		
		for(int x=1;x<=rows;x++)
		{
			String page = ws.getRow(x).getCell(0).getStringCellValue();
			String element = ws.getRow(x).getCell(1).getStringCellValue();
			
			if((pageName.equalsIgnoreCase(page)) && (elementName.equalsIgnoreCase(element)))
			{
				testData = ws.getRow(x).getCell(2).getStringCellValue();
				break;
			}
			
		}		
		wb.close();
		return testData;
	}
	
	
	
	
	public static void closeBrowser()
	{
		driver.close();
	}

}
