package com.jan23salesforceUtility;



import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import com.jan23Salesforce.base.SalesForceBaseTest;

public class TestEventListnersUtility implements ITestListener{
	protected static ExtentReportsUtility extentreport=null;
	protected WebDriver driver;
	@Override
	public void onTestStart(ITestResult result) {
		
		extentreport.startSingleTestReport(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentreport.logTestpassed(result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentreport.logTestFailed(result.getMethod().getMethodName());
		//BaseTest ob=new BaseTest();
		SalesForceBaseTest ob = new SalesForceBaseTest();
		try {
			ob.getScreenshotOfThePage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//driver=ob.returnDriverInstance();
		String path=ob.getScreenshotOfThePage(driver);
		extentreport.logTestScreenshot(path);
	}
	//for making the singleton class we have to make constructer private

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//extentreport =new ExtentReportsUtility();
		extentreport=ExtentReportsUtility.getInstance();
		extentreport.startExtentReport();
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extentreport.endReport();
	}

}

