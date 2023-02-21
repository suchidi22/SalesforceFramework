package com.jan23Salesforce.base;

import java.io.File;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.jan23salesforceUtility.PropertiesUtility;
import com.jan23salesforceUtility.Constants;
import com.jan23salesforceUtility.ExtentReportsUtility;
import com.jan23Salesforce.base.SalesForceBaseTest;

import io.github.bonigarcia.wdm.WebDriverManager;


	public class SalesForceBaseTest {
		
		protected static Logger logger =null;
    protected static WebDriver driver =null;
    protected static WebDriverWait wait=null;
    protected static ExtentReportsUtility extentreport = null;
    @BeforeTest
	public void setUpBeforeTest() {
		
    	//extentreport.logTestInfo("beforeTest method has started");
    	//System.out.println("inside @BeforeTest method");
    	//we have to created a logger
		logger =LogManager.getLogger(SalesForceBaseTest.class.getName());
		extentreport = new ExtentReportsUtility();
		extentreport.startExtentReport(); 
    }
    @BeforeMethod
    @Parameters("browsername")
	public void setUpBeforeTestMethod(@Optional("chrome") String browsername,Method method) {
    	
		extentreport.startSingleTestReport("testcase");
		logger.info("started testscript name "+method.getName());
		PropertiesUtility propertiesUtility =new PropertiesUtility();
		propertiesUtility.loadFile("testDataProperties");
		String url = propertiesUtility.getPropertyValue("url");
		GetDriverInstance(browsername);
		goToUrl1(url);
    }
    @AfterMethod
	public void tearDownAfterTestMethod() {
		driver.close();
	}
	
    public static void enterText(WebElement element, String text,String name){
			if (element.isDisplayed()) {
				element.clear(); //clear the field so that user can enter value
				element.sendKeys(text); //we need enter the value
				logger.info("text entered in " + name + " field");
				}
				else {
					logger.info(name + "web element is not displayed");
				} 
			driver.getTitle();
		}
    @AfterTest
    public void tearDownAfterTest() {
    	extentreport.endReport();
    }

    public static void clearElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.clear();
			logger.info("pass:" + objName + "  element cleared");

		} else {
			logger.info("fail:" + objName + " element not displayed");
		}
	}
    public static void clickElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.click();
			logger.info("pass:" + objName + " element clicked");
		} else {
			logger.info("fail:" + objName + "  element not displayed");

		}
	}
    
    public static void goToUrl1(String url) {
		logger.info("going to the url=" +  url);
		driver.get(url);
	}
	
	
    public static void closeBrowser() {
		logger.info("The browser is closed");
		driver.close();
	}
	public static String getPageTitle() {
		return driver.getTitle();
	}

	public static void refreshPage() {
		driver.navigate().refresh();

	}
	public static void  waitUntilPageLoads() {
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
	}

			public static void GetDriverInstance(String browserName) {
		
		switch (browserName){
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver();
			 driver.manage().window().maximize();
			 break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver =new ChromeDriver();
			driver.manage().window().maximize();
			break;
		default:
			System.out.println("Not entered proper browser name");
		 }
		}
			public static String getTextFromWebElement(WebElement element, String name) {
				if (element.isDisplayed()) {
					return element.getText();
				} else {
					System.out.println(name + " web element is not displayed");
					return null;
				}
				

			}
		
		public static void goToUrl(String url) {
			driver.get(url);
		}
		
		public static String GetTextFromWebElement(WebElement element) {
			if (element.isDisplayed())
					return element.getText();
			else
				System.out.println( "web element is not displayed");
			return null;
		}
		public static void WaitUntilElementIsVisible(WebElement ele, String objName) {
			
			System.out.println("waiting for an web element"+objName+"for its visibility");
			wait =new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOf(ele));
		}
		public static void WaitUntilElementToBeClickable(By locator,String objName) {
			System.out.println("waiting for an web element"+objName+"to be clickable");
			wait =new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		}

		public static void getScreenshotOfThePage() throws IOException {
			String date = new SimpleDateFormat("yyyy__MM__dd__hh_mm_ss").format(new java.util.Date());
			String curDir = System.getProperty("user.dir");
			TakesScreenshot screenShot=(TakesScreenshot)driver;
			File imgFile =screenShot.getScreenshotAs(OutputType.FILE);
			File destFile = new File(Constants.SCREENSHOTS_DIRECTORY_PATH +date+".png");
			try{
				FileHandler.copy(imgFile, destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public static String getScreenshotOfThePage(WebDriver driver) {
			String date = new SimpleDateFormat("yyyy__MM__dd__hh_mm_ss").format(new java.util.Date());
			String curDir = System.getProperty("user.dir");
			TakesScreenshot screenShot=(TakesScreenshot)driver;
			File imgFile =screenShot.getScreenshotAs(OutputType.FILE);
			File destFile = new File(Constants.SCREENSHOTS_DIRECTORY_PATH+date+".png");
			try{
				FileHandler.copy(imgFile, destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return destFile.getAbsolutePath();
		}
		}



