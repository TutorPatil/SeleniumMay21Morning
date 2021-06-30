package com.actitime.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogLevelMapping;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.io.Files;

public class BaseClass implements ITestListener{
	
	public static WebDriver driver ;
	public static Map<String,String> locatorMap = new HashMap<String, String>();
	public static Map<String,String> testdataMap = new HashMap<String, String>();
	public static Logger logger = Logger.getLogger("BaseClass");
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest  exLogger;
	
	/*
	 * Author = Patil
	 * Arguments = N/A 
	 * Returns = void
	 * Throws = IOException
	 *  Use = This method is used to launch the browser
	 * 
	 */
	
	public static void writeLogs(String msg)
	{
		logger.info(msg);
		exLogger.log(Status.INFO, msg);
	}
	
	public static void writeErrorLogs(Throwable t)
	{
		String s = Arrays.toString(t.getStackTrace());		
		String s1 = s.replaceAll(",", "\n");
		logger.error(s1);
		exLogger.log(Status.ERROR, s1);
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public static void launchBrowser() throws IOException
	{
		writeLogs("This method will run before every Test case of @Test");
		
		
		String browser = "";
		
		if (System.getenv("BROWSER") != null)
			{
				browser =System.getenv("BROWSER");
			}else
				{
				browser = getConfigData("browser");
				}
		
		
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./src/test/utilities/chromedriver.exe");		
			driver = new ChromeDriver();		
			
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./src/test/utilities/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		String timeout = getConfigData("implicitwaittimeout");
		long time = Long.parseLong(timeout);
		
		// Implicit wait applicable through out the life cycle of the driver..
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);  // polling interval of 500 MS
		
		String url = getConfigData("url");
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	public static String getConfigData(String key) throws IOException
	{
		String value = "";
		
		File f = new File("./src/test/data/config.properties");	
		
		// Creating the object of FileInputStream to load the file as stream
		FileInputStream fis = new FileInputStream(f);
		
		// Creating the object of properties file
		Properties prop = new Properties();
		
		// Loading the stream
		prop.load(fis);
		
		
		value = prop.getProperty(key);
	
		
		
		return value;
	}
	
	public static String getLocatorDataFromMap(String pageName, String elementName)
	{
		String locator = "";		
		locator = locatorMap.get(pageName+"$"+elementName);	
		return	locator;
	}
	

	public static String getLocatorData(String pageName, String elementName) throws IOException
	{
		String locator="";
		
		File f = new File("./src/test/data/locatordata.xlsx");
		
		FileInputStream fio = new FileInputStream(f);
		
		XSSFWorkbook wb = new XSSFWorkbook(fio);
		
		XSSFSheet ws = wb.getSheet("Sheet1");
		
		int rows = ws.getLastRowNum();
		
		//writeLogs(rows);
		
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
	

		public static void getAndStoreLocatorData() throws IOException {
			String xpath = "";

			File f = new File("./src/test/data/locatordata.xlsx");
			FileInputStream fio = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(fio);
			XSSFSheet ws = wb.getSheet("Sheet1");

			int rows = ws.getLastRowNum();		

			for (int x = 1; x <= rows; x++) {

				String page = ws.getRow(x).getCell(0).getStringCellValue();
				String element = ws.getRow(x).getCell(1).getStringCellValue();	
				xpath = ws.getRow(x).getCell(2).getStringCellValue();
				locatorMap.put(page+"$"+element, xpath);
					
				}	
			writeLogs("Locator hash Map ===" + locatorMap);

			wb.close();
			
		}
		
		public static void getAndStoreTestData() throws IOException {
			String xpath = "";

			File f = new File("./src/test/data/testdata.xlsx");
			FileInputStream fio = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(fio);
			XSSFSheet ws = wb.getSheet("Sheet1");

			int rows = ws.getLastRowNum();		

			for (int x = 1; x <= rows; x++) {

				String page = ws.getRow(x).getCell(0).getStringCellValue();
				String element = ws.getRow(x).getCell(1).getStringCellValue();	
				xpath = ws.getRow(x).getCell(2).getStringCellValue();
				testdataMap.put(page+"$"+element, xpath);
				
				writeLogs(page+"$"+element+xpath);
					
				}	
			writeLogs("testData hash Map ===" + testdataMap);

			wb.close();
			
		}

		
		
	
	
	public static String getTestData(String pageName, String elementName) throws IOException
	{
		String testData="";
		
		File f = new File("./src/test/data/testdata.xlsx");
		
		FileInputStream fio = new FileInputStream(f);
		
		XSSFWorkbook wb = new XSSFWorkbook(fio);
		
		XSSFSheet ws = wb.getSheet("Sheet1");
		
		int rows = ws.getLastRowNum();
		
		//writeLogs(rows);
		
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
	
	
	
	@AfterMethod(alwaysRun = true)
	public static void closeBrowser()
	{
		writeLogs("This method will run after every Test case of @Test");
		driver.close();
	}
	
	
	//@BeforeSuite(alwaysRun = true)
	public static void beforeSuite()
	{
		writeLogs("Inside Before suite ,,,,,This method runs first once the suite starts...");
	}


	//@AfterSuite(alwaysRun = true)
	public static void afterSuite()
	{
		writeLogs("Inside After suite....This method runs at the end once all the suite ends...");
	}

	@BeforeClass(alwaysRun = true)
	public static void beforeCLass()
	{
		writeLogs("Inside before Class Method...This method runs before every class starts ..");
	}

	@AfterClass(alwaysRun = true)
	public static void afterClass()
	{
		writeLogs("After the After class method.....This method runsafter every class  ends...");
	}
	
	
	@BeforeTest(alwaysRun = true)
	public static void beforetest()
	{
		writeLogs("Inside beforeTest Method...This method runs before every tesng test from suite  ..");
	}

	@AfterTest(alwaysRun = true)
	public static void aftertest()
	{
		writeLogs("After the AfterTest Method....This method runsafter every testng test from suite.....");
	}
	

	public static void writeResultsToFile(String testCaseName, String status) throws IOException
	{
		File f = new File("./src/test/results/results.txt");
		
		FileWriter fw = new FileWriter(f,true);
		fw.write(testCaseName+" ---- "+status+"\n");
		fw.flush();
		fw.close();
		
		
	}
	
	
	public static void captureScreenShot(String fileName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);		
		File dest = new File("./src/test/results/screenshots/"+fileName+".png");		
		Files.copy(src, dest);
		
	}
	
	
	// TestNG Listener Methods

	
	public void onFinish(ITestContext arg0) {
		writeLogs("############ The suite execution completed ###############");
		
	}

	
	public void onStart(ITestContext arg0) {
		
		
		File f1 = new File("./src/test/results/report.html");
		reporter = new ExtentHtmlReporter(f1);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		exLogger = extent.createTest("Initialization Steps");
		
		writeLogs("############ The suite execution is getting started ###############");
		
		try {
			getAndStoreLocatorData();
			getAndStoreTestData();
		} catch (IOException e1) {			
			e1.printStackTrace();
		}
		
		
		File f = new File("./src/test/results/results.txt");
		
		
		try {
			FileWriter fw = new FileWriter(f);
			fw.write(" Starting the fresh Execution "+"\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		
		
	}

	
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		writeLogs("");
		
	}

	
	public void onTestFailure(ITestResult arg0) {
		String testCaseName = arg0.getName();
		
		try {
			writeResultsToFile(testCaseName, "Fail");
			captureScreenShot(testCaseName);
			writeLogs(" The test case ny name "+ testCaseName + " is Failed!!");
			writeErrorLogs(arg0.getThrowable());
			
			exLogger.log(Status.FAIL, "The test case ny name "+ arg0.getName()+" Failed!!");
			extent.flush();
			
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
		
	}

	
	public void onTestSkipped(ITestResult arg0) {
		writeLogs(" The test case ny name "+ arg0.getName() + " is skipped!!");
		
	}

	
	public void onTestStart(ITestResult arg0) {
		extent.flush();
		exLogger = extent.createTest(arg0.getName());
		
		writeLogs(" ****************** The test case ny name "+ arg0.getName() + " is getting started ************");
		
		
	}


	public void onTestSuccess(ITestResult arg0) {
		writeLogs(" ************ The test case ny name "+ arg0.getName()  + " Passed **********");
		try {
			writeResultsToFile(arg0.getName() , "Pass");
			exLogger.log(Status.PASS, "The test case ny name "+ arg0.getName()+" is Passed");
			extent.flush();
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		
		
	}
}
