package com.jan23salesforceUtility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsUtility {
	public static ExtentReports report;
	public static ExtentSparkReporter spartReporter;
	public static ExtentTest testLogger;
	
	private static ExtentReportsUtility extentObject;//singleton class 
	public ExtentReportsUtility() {
		
	}
	public static ExtentReportsUtility getInstance() {
		if(extentObject==null) {
			System.out.println("creating textent utility object");
			extentObject=new ExtentReportsUtility();
		}
		return extentObject;
	}
	//the method will take care of starting report
	public void startExtentReport() {
		spartReporter=new ExtentSparkReporter(Constants.SPARKS_HTML_REPORT_PATH);//new repoter create
		report=new ExtentReports();
		report.attachReporter(spartReporter);//attach reporter to extent spark report
		
		report.setSystemInfo("Host Name", "Salesforce");
		report.setSystemInfo("Environment", "Automation Testing");
		report.setSystemInfo("User Name", "suchi");

		spartReporter.config().setDocumentTitle("Test Execution Report");//attach all document information
		spartReporter.config().setReportName("salesforce regression tests");
		spartReporter.config().setTheme(Theme.DARK);
	}
	
	public void startSingleTestReport(String testScript_Name) {
		testLogger=report.createTest(testScript_Name);
	}
	//make the reusable method
	public void logTestInfo(String text) {
		testLogger.info(text);
	}
	public void logTestpassed(String testcaseName) {
		testLogger.pass(MarkupHelper.createLabel(testcaseName + "is passTest", ExtentColor.GREEN));
	}
	public void logTestFailed(String testcaseName) {
		testLogger.fail(MarkupHelper.createLabel(testcaseName + "is FailTest", ExtentColor.RED));
	}
	public void logTestFailedWithException(Exception e) {
		testLogger.log(Status.FAIL, e);
	}
	public void logTestScreenshot(String path) { // reusable method for attach the screenshot with the report
		testLogger.addScreenCaptureFromPath(path);
		}
	public void endReport() {
		report.flush();
	}

	
}
