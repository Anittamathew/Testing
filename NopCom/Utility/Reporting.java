package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
	public ExtentHtmlReporter htmlReport;
	public ExtentReports xReport;
	public ExtentTest xTest;

// LoC to configure the extent report in the html format.
// Create the new HTML report for every test execution in the html format
// HTML file should be stored under Reports folder in the project directory
	
	public void onStart(ITestContext testContext) {
		
		String dateStamp =  new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());		
		String repName = "NopCommerce-"+ dateStamp +".html";
		
		htmlReport = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/" + repName);
		
		htmlReport.config().setDocumentTitle("Test Automation Report");
		htmlReport.config().setReportName("Functional Report");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setAutoCreateRelativePathMedia(true);
		
		xReport = new ExtentReports();
		xReport.attachReporter(htmlReport);
		xReport.setSystemInfo("QA Name", "Anitta Mathew");
		xReport.setSystemInfo("OS", "Windows10");
		xReport.setSystemInfo("hostname", "localhost");			
	}

	
	public void onFinish(ITestContext testContext) {
		xReport.flush();		
	}

	public void onTestSuccess(ITestResult tr) {
		xTest = xReport.createTest(tr.getName());
		xTest.log(Status.PASS, "Test is passed");
		xTest.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));

	}

	public void onTestFailure(ITestResult tr) {

		xTest = xReport.createTest(tr.getName());
		xTest.log(Status.FAIL, "Test is failed");
		xTest.log(Status.FAIL, tr.getThrowable());
		xTest.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		// read the path of screenshot with the name of the test that is failed.
		
		String ssPath = System.getProperty("user.dir")+"/Screenshots/"+tr.getName()+".png";
		File file = new File(ssPath);
		
		if(file.exists()) {
			try {
				xTest.fail("Screenshot of the test failed is : " + xTest.addScreenCaptureFromPath(ssPath));
			} 
			catch (IOException e) {				
				e.printStackTrace();
			}
		}		
	}


	public void onTestSkipped(ITestResult tr) {

		xTest = xReport.createTest(tr.getName());
		xTest.log(Status.SKIP, "Test is skipped");
		xTest.log(Status.SKIP, tr.getThrowable());
		xTest.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.AMBER));
	}

}
