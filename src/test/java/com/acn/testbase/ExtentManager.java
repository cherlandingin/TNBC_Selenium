package com.acn.testbase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {
	
	private static ExtentReports extent;
	private static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;
	private static String filePath = "C:/Workspace/practice/target/extentreport.html";
	
	
	public static ExtentReports GetExtent(String folderName){
		if (extent != null)
                    return extent; //avoid creating new instance of html file
                extent = new ExtentReports();		
		extent.attachReporter(getHtmlReporter(folderName));
		return extent;
	}

	/*private static ExtentHtmlReporter getHtmlReporter(String folderName) {
	
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\target\\" + folderName + "\\Report.html");
		
	// make the charts visible on report open
        htmlReporter.config().setChartVisibilityOnOpen(true);
		
        htmlReporter.config().setDocumentTitle("QAV automation report");
        htmlReporter.config().setReportName("Regression cycle");
        return htmlReporter;
	}*/

	private static ExtentHtmlReporter getHtmlReporter(String folderName) {
	       htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\target\\" + folderName + "\\Report.html");
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "\\src\\test\\java\\com\\acn\\config\\config.xml");
		return htmlReporter;
	}

	public static ExtentTest createTest(String name, String description){
		test = extent.createTest(name, description);
		return test;
	}
}
