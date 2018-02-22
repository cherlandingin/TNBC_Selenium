package com.acn.testbase;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
//import com.relevantcodes.extentreports.*;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;


//import auto.framework.WebManager;

public class TestBase {
	// declare all variables in the fields
	public static WebDriver driver = null;
	public static Properties Config = null;
	public static WebDriverWait wait30 = null;
	public static ExtentReports rpt = null;
	public static ExtentTest rpt_test = null;
	public static String FolderName = null;
	public static Logger logger = null;
	public static boolean isBrowserOpen = false;
	public static String BrowserType = null;;
	public static Actions act = null;
	public static String TCName = null;
	public static String tempName = null;
	public static int iResult = 0;
	public Map<String, String> valueList = new HashMap<String, String>();
	private static final Pattern VALID_EMAIL_ADDRESS = Pattern.compile(
			"(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)");
	private static final Pattern VALID_MOBILE_NUMBER = Pattern.compile("^(\\+63{1,3}[- ]?)?\\d{10}$");
	private static final Pattern VALID_PHONE_NUMBER = Pattern.compile("^(\\+63{1,3}[- ]?)?(\\d{1,2})\\d{7}$");
	public void initialize() throws Exception {

		driver = null;

		Config = new Properties();
		FileInputStream ipEnv = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\acn\\config\\Environment.properties");
		Config.load(ipEnv);

		SimpleDateFormat screenshotFormat = new SimpleDateFormat("MMM-dd-HH-mm");
		Date date = new Date();

		FolderName = "Run_Results_" + screenshotFormat.format(date);
		// File reportFolder = new File(System.getProperty("user.dir") + "\\target\\" +
		// FolderName);
		// if(reportFolder.exists())
		// FolderName = FolderName + "_Run_" + 1;
		if (tempName == null)
			tempName = FolderName;
		else
			FolderName = tempName;

		rpt = ExtentManager.GetExtent(FolderName);
		// rpt = new
		// ExtentReports(System.getProperty("user.dir")+"/target/"+FolderName+"/Report.html");
		// rpt = new ExtentReports(System.getProperty("user.dir") + "\\target\\" +
		// FolderName + "\\Report.html");
		rpt_test = rpt.createTest(TCName);
		// rpt_test = rpt.startTest(this.getClass().getSimpleName() + ".feature", "");
		PropertyConfigurator
				.configure(System.getProperty("user.dir") + "\\src\\test\\java\\com\\acn\\config\\log4j.properties");
		logger = Logger.getLogger("logger");

		isBrowserOpen = true;

	}

	/******************************************************************************
	 * @author EF9749
	 * @param None
	 * @return Description: This is global variable in opening the browser
	 * @throws Exception
	 *****************************************************************************/
	public void openBrowser(String sBrowserType) throws Exception {

		initialize();

		this.BrowserType = sBrowserType;

		try {

			if (sBrowserType.equals("")) {
				sBrowserType = this.Config.getProperty("env_browserType");
			}

			if (sBrowserType.contains("IE")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "/webdriver/IEDriverServer_64.exe");
				driver = new InternetExplorerDriver();
				// System.out.println("hello there");
				ReportResults("PASS", "Browser used is Internet Explorer", true);
			} else if (sBrowserType.contains("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/webdriver/chromedriver.exe");

				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				options.addArguments("chrome.switches", "--disable-extensions");
				options.addArguments("start-maximized");

				driver = new ChromeDriver(options);

				act = new Actions(driver);
				ReportResults("PASS", "Browser used is Google Chrome", true);

			} else if (sBrowserType.contains("Mozilla")) {

				// FirefoxDriverManager.getInstance().setup();
				// System.out.println("here");
				// Thread.sleep(30000);
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/webdriver/geckodriver.exe");
				driver = new FirefoxDriver();
				ReportResults("PASS", "Browser used is Mozilla Firefox", true);
			} else {
				System.out.println("Error Browser Type: " + sBrowserType + "");
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			wait30 = new WebDriverWait(driver, 30);

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured: " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

	}

	/******************************************************************************
	 * @author EF9749
	 * @param None
	 * @return Description: Closes the browser
	 * @throws Exception
	 *****************************************************************************/
	public void closeBrowser() throws Exception {
		try {
			
			driver.quit();
			System.out.println("Browser closed");

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in closeBrowser(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	/******************************************************************************
	 * @author EF9749
	 * @param None
	 * @return Description: Standard class of reporting Pass or Fail status of every
	 *         step
	 * @throws Exception
	 *****************************************************************************/
	public void ReportResults(String sResult, String sMessage, boolean bTakeScreenshot) throws Exception {

		DateFormat screenshotFormat = new SimpleDateFormat("MM-dd-HH-mm-ss");
		Date date = new Date();

		String sScreenshot = this.getClass().getSimpleName() + screenshotFormat.format(date);

		try {

			if (sResult.equalsIgnoreCase("PASS")) {
				iResult = 1;
			} else if (sResult.equalsIgnoreCase("FAIL")) {
				iResult = 2;
			} else if (sResult.equalsIgnoreCase("DONE")) {
				iResult = 3;
			} else if (sResult.equalsIgnoreCase("TESTNAME")) {
				iResult = 4;
			}

			// rpt_test.setDescription(TCName);

			switch (iResult) {

			case 1:

				Reporter.log("PASS: " + sMessage + ";\n", true);

				System.out.println(("PASS: " + sMessage));

				if (bTakeScreenshot) {
					this.takeScreenshot(sScreenshot, FolderName);
					rpt_test.pass(sMessage,
							MediaEntityBuilder.createScreenCaptureFromPath(sScreenshot + ".png").build());
					// rpt_test.addScreenCaptureFromPath(sScreenshot + ".png");

				} else {
					rpt_test.pass(sMessage);
				}

				break;
			case 2:
				Reporter.log("FAIL: " + sMessage + ";\n", true);
				System.out.println("FAIL: " + sMessage);

				if (bTakeScreenshot) {
					this.takeScreenshot(sScreenshot, FolderName);

					rpt_test.fail(sMessage,
							MediaEntityBuilder.createScreenCaptureFromPath(sScreenshot + ".png").build());
				} else {
					rpt_test.fail(sMessage);
				}

				// rpt.endTest(rpt_test);
				rpt.flush();
				Assert.fail("FAIL: " + sMessage);

				break;

			case 3:
				Reporter.log("DONE:" + sMessage + ";\n", true);
				System.out.println("DONE: " + sMessage);
				// rpt.endTest(rpt_test);
				rpt.flush();
				break;
			case 4:
				Reporter.log("TestName: " + this.getClass().getSimpleName() + "\n", true);
				System.out.println("TestName: " + this.getClass().getSimpleName());
				break;

			default:
				Reporter.log("Please enter correct values to the parameters");

			}

			rpt.flush();

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in ReportResults(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	/******************************************************************************
	 * 
	 * @author EF9749
	 * @param None
	 * @return Description: Check if elements exist in the screen
	 * @throws Exception
	 *****************************************************************************/
	public boolean isElementExist(By by) {
		try {
			this.driver.findElement(by);
			System.out.println("Elements Exist");
			return true;
		} catch (Exception e) {
			return false;
		}
	}// for update

	/*******************************************************************************
	 * @author EF9749
	 * @param fileName
	 *            - name of the image file
	 * @param folderName
	 *            - folder where the image file will be saved
	 * @throws Exception
	 */
	public void takeScreenshot(String fileName, String folderName) throws Exception {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File(System.getProperty("user.dir") + "/target/" + folderName + "/" + fileName + ".png"));
		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in takeScreenshot(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

	}

	/*******************************************************************************
	 * @author EF9749
	 * @Description - This gets the title of the current web page
	 * @throws Exception
	 */
	public String getPageTitle() throws Exception {

		List<WebElement> sPage = driver.findElements(By.xpath("//title"));

		String sTitle = null;

		try {

			if (sPage.size() > 0) {
				sTitle = sPage.get(0).getText();
			}

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in takeScreenshot(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

		return sTitle;
	}

	/*******************************************************************************
	 * @author EF9749
	 * @Description - This is the generic script for Entering text to a text field
	 * @param sValueObjectId
	 *            - this contains the Value to be filled up in the textbox and the
	 *            object id/xpath of the textfield; e.g.
	 *            "Gabriel;//input[@id='firstName']"
	 * @throws Exception
	 *******************************************************************************/
	public void enterText(String pValueObjectId) throws Exception {

		String sValueObject = pValueObjectId;
		String sValue = null;
		String ObjId = null;
		String frame = null;

		String locType = null;
		String temp = null;
		try {

			if (sValueObject.contains(";")) {

				String[] parts = sValueObject.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ReportResults("FAIL", "Enter Text - The parameter " + sValueObject + "", true);
			}

			switchTo(pValueObjectId);

			// wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));

			List<WebElement> oList = findElement(ObjId, locType);

			// TimeUnit.SECONDS.sleep(2);

			if (oList.size() > 0) {
				oList.get(0).click();
				oList.get(0).clear();
				oList.get(0).sendKeys(sValue);
				ReportResults("PASS", "Successfully entered text: " + sValue + " on this object: " + ObjId, true);
			} else {
				ReportResults("FAIL", "Enter Text - The Object: " + ObjId + " does not exist", true);
			}

			driver.switchTo().defaultContent();

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in enterText(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	/**********************************************************************************
	 * @author EF9749
	 * @Description - This is the generic script for clicking an object (button,
	 *              links, images, labels etc.)
	 * @param sValueObjectId
	 *            - this contains the object id/xpath of the
	 *            button/links/images/labels etc; e.g. "//button[@id='Submit']"
	 * @throws Exception
	 *******************************************************************************/
	public boolean clickButton(String sObjId) throws Exception {

		String sValue = null;
		String locType = null;
		String ObjId = null;
		String frame = null;
		String temp = null;
		boolean bOutcome = false;

		try {

			if (sObjId.contains(";")) {

				String[] parts = sObjId.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				System.out.println(sValue);
				System.out.println(locType);
				System.out.println(ObjId);

			} else {
				ObjId = sObjId;
			}

			// Check if needed to switch to a frame or a window
			switchTo(sObjId);
			// updating
			// findElement(ObjId, "try");
			// wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));
			// wait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjId)));
			List<WebElement> oList = findElement(ObjId, locType);
			// List<WebElement> oList =
			// this.driver.findElements(By.xpath(ObjId));

			if (oList.size() > 0) {
				// before clicking it, ensure that it is displayed in the screen
				// Actions actions = new Actions(driver);
				// actions.moveToElement(oList.get(0)).click();

				// actions.perform();

				// scrollToElement(oList.get(0));

				// this.scrollAndClick(oList.get(0));
				this.winHandleBefore = this.driver.getWindowHandle();
				// this.changeWindow();
				// scrollToElement(oList.get(0));
				Actions action = new Actions(driver);
				

				oList.get(0).click();

				bOutcome = true;
			} else {
				ReportResults("FAIL", "Object - The Object: " + ObjId + " does not exist", true);
			}

			driver.switchTo().defaultContent();

			// TimeUnit.SECONDS.sleep(20);

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in clickButton(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

		return bOutcome;

	}

	/**********************************************************************************
	 * @author EF9749 updated by RJDG
	 * @Description - This is the generic script for checking if element exists
	 * @param sValueObjectId
	 *            - this contains the object id/xpath of the
	 *            button/links/images/labels etc; e.g. "//button[@id='Submit']"
	 * @throws Exception
	 *             Updated last 07/05/17
	 *******************************************************************************/
	public void isElementVisible(String sObjId) throws Exception {

		String sValue = null;
		String ObjId = null;
		String frame = null;

		String locType = null;
		String temp = null;

		boolean bOutcome = false;

		try {

			// count number of semicolons in the parameter
			int iCnt = StringUtils.countMatches(sObjId, ";");
			System.out.println("Number of delimiter: " + iCnt + "");

			if (sObjId.contains(";")) {

				String[] parts = sObjId.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];

				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ObjId = sObjId;
			}

			switchTo(sObjId);

			// wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));
			// List<WebElement> oList =null;
			if (sValue.equalsIgnoreCase("true")) {
				List<WebElement> oList = findElement(ObjId, locType);
				if (oList.size() > 0) {
					// scrollToElement(oList.get(0));
					bOutcome = true;
				}
				if (bOutcome)
					ReportResults("PASS", "The element is visible " + ObjId, true);
				else
					ReportResults("FAIL", "The element is not visible " + ObjId, true);

			} else {
				if (locType.equalsIgnoreCase("id"))
					bOutcome = wait30.until(ExpectedConditions.invisibilityOfElementLocated(By.id(ObjId)));
				else if (locType.equalsIgnoreCase("name"))
					bOutcome = wait30.until(ExpectedConditions.invisibilityOfElementLocated(By.name(ObjId)));
				else if (locType.equalsIgnoreCase("cssSelector"))
					bOutcome = wait30.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(ObjId)));
				else if (locType.equalsIgnoreCase("className"))
					bOutcome = wait30.until(ExpectedConditions.invisibilityOfElementLocated(By.className(ObjId)));
				else if (locType.equalsIgnoreCase("xpath"))
					bOutcome = wait30.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ObjId)));

				if (bOutcome)
					ReportResults("PASS", "The element is not visible " + ObjId, true);
				else
					ReportResults("FAIL", "The element is visible " + ObjId, true);
			}

			driver.switchTo().defaultContent();
		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in isElementVisible(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	/*******************************************************************************
	 * @author EF9749
	 * @Description - This is the generic method for verifying a text in the screen
	 * @param sValueObjectId
	 *            - this contains the object id/xpath of the
	 *            button/links/images/labels etc; e.g. "//button[@id='Submit']"
	 * @throws Exception
	 *******************************************************************************/
	public boolean verifyText(String sValueObjectId) throws Exception {

		TimeUnit.SECONDS.sleep(5);
		boolean bResult = false;

		String string = sValueObjectId;
		String sValue = null;
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;

		// count number of semicolons in the parameter
		int iCnt = StringUtils.countMatches(sValueObjectId, ";");
		System.out.println("Number of delimiter: " + iCnt + "");

		try {

			if (string.contains(";")) {
				String[] parts = string.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];

				System.out.println(sValue);
				System.out.println(ObjId);

			} else {

				ObjId = sValueObjectId;

			}

			switchTo(sValueObjectId);

			// wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));
			List<WebElement> oList = findElement(ObjId, locType);
			// List<WebElement> oList = driver.findElements(By.xpath(ObjId));

			if (oList.size() > 0) {
				// added by justin
				// scrollToElement(oList.get(0));
				String errMsg = oList.get(0).getText();
				String s = "\n";
				if (errMsg.contains(s)) {
					errMsg = errMsg.replace("\n", " ");
				}
				System.out.println("Actual msg: " + errMsg);
				System.out.println("Expected msg: " + sValue);

				// try to change .contains to .equals
				if (errMsg.equals(sValue)) {
					bResult = true;
				} else {
					bResult = false;
				}

			} else {
				ReportResults("FAIL", "Object does not exist", true);
			}

			driver.switchTo().defaultContent();
		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in verifyText(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

		return bResult;

	}

	/*******************************************************************************
	 * @author EF9749
	 * @Description - This is the generic method for selecting a radio button
	 * @param sValueObjectId
	 *            - this contains the object id/xpath of the radio button
	 * @throws Exception
	 *******************************************************************************/
	public void selectRadioBtn(String sValueObjectId) throws Exception {

		String string = sValueObjectId;
		String sValue = null;
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;
		// count number of semicolons in the parameter
		int iCnt = StringUtils.countMatches(sValueObjectId, ";");
		System.out.println("Number of delimiter: " + iCnt + "");

		try {

			if (string.contains(";")) {
				String[] parts = string.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ObjId = sValueObjectId;
			}

			switchTo(sValueObjectId);

			// wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));
			//
			// List<WebElement> oList =
			// this.driver.findElements(By.xpath(ObjId));
			List<WebElement> oList = findElement(ObjId, locType);
			if (oList.size() > 0) {
				oList.get(0).click();
			} else {
				ReportResults("FAIL", "Object does not exist", true);
			}

			driver.switchTo().defaultContent();

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in selectRadioBtn(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

	}

	/*******************************************************************************
	 * @author EF9749
	 * @Description - This is the generic method for choosing an item in the
	 *              dropdown
	 * @param sValueObjectId
	 *            - this contains the object id/xpath of the dropdown
	 * @throws Exception
	 *******************************************************************************/

	public void chooseDropDown(String sValueObjectId) throws Exception {

		String string = sValueObjectId;
		String sValue = null;
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;

		// count number of semicolons in the parameter
		int iCnt = StringUtils.countMatches(sValueObjectId, ";");
		System.out.println("Number of delimiter: " + iCnt + "");

		try {

			if (string.contains(";")) {
				String[] parts = string.split(";");
				sValue = parts[0];
				temp = parts[1];

				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				// if(sValue.contains("\uFFFD"))
				// sValue = sValue.replace("\uFFFD","-");

				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				// do nothing
			}

			switchTo(sValueObjectId);

			// wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));
			//
			// List<WebElement> oList =
			// this.driver.findElements(By.xpath(ObjId));
			List<WebElement> oList = findElement(ObjId, locType);
			if (oList.size() > 0) {
				Select select = new Select(oList.get(0));
				select.selectByVisibleText(sValue);
			} else {
				ReportResults("FAIL", "Object does not exist", true);
			}

			driver.switchTo().defaultContent();
		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in chooseDropdown(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

	}

	/*******************************************************************************
	 * @author EF9749
	 * @Description - This is the generic method for checking an item in the
	 *              checkbox
	 * @param sValueObjectId
	 *            - this contains the object id/xpath of the checkbox
	 * @throws Exception
	 *******************************************************************************/
	public void checkBox(String sValueObjectId) throws Exception {

		String string = sValueObjectId;
		String sValue = null;
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;
		// count number of semicolons in the parameter
		int iCnt = StringUtils.countMatches(sValueObjectId, ";");
		System.out.println("Number of delimiter: " + iCnt + "");

		try {

			if (string.contains(";")) {
				String[] parts = string.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ObjId = sValueObjectId;
			}

			switchTo(sValueObjectId);

			// wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));
			//
			// List<WebElement> oList =
			// this.driver.findElements(By.xpath(ObjId));
			List<WebElement> oList = findElement(ObjId, locType);
			if (oList.size() > 0) {
				if (!isChecked(oList.get(0))) {
					oList.get(0).click();
				}

			} else {
				ReportResults("FAIL", "Object does not exist", true);
			}

			driver.switchTo().defaultContent();

			oList = null;

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in checkBox(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	/*******************************************************************************
	 * @author 

	 * @Description - Check if a specific object is "Checked"
	 * @param oChck
	 *            - the WebElement object
	 * @throws Exception
	 *******************************************************************************/
	public boolean isChecked(WebElement oChck) throws Exception {

		String isChecked = oChck.getAttribute("value");

		boolean bState = false;

		try {

			if (isChecked.toLowerCase().equals("true")) {
				bState = true;

			} else {
				bState = false;
			}
		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in isChecked(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

		return bState;
	}

	/*******************************************************************************
	 * @author EF9749
	 * @Description - Check if the application has Navigated to the expected URL.
	 * @param oChck
	 *            - the WebElement object
	 * @throws Exception
	 *******************************************************************************/
	public boolean validateUrl(String sValueObjectId) throws Exception {

		String sValue = null;
		String ObjId = null;

		boolean bState = false;

		try {

			if (sValueObjectId.contains(";")) {
				String[] parts = sValueObjectId.split(";");
				sValue = parts[0];
				ObjId = parts[1];
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ObjId = sValueObjectId;
			}

			TimeUnit.SECONDS.sleep(25);

			String sUrl = driver.getCurrentUrl();
			System.out.println("Actual: " + sUrl);
			System.out.println("Expected: " + sValue);

			if (sUrl.contains(sValue)) {
				bState = true;
			} else {
				bState = false;
			}

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in isChecked(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

		return bState;
	}

	/*******************************************************************************
	 * @author EF9749
	 * @Description - Scrolls to the element (pagedown) until it becomes visible
	 * @param oElement
	 *            - the WebElement object
	 * @throws Exception
	 *******************************************************************************/
	public void scrollToElement(WebElement oElement) throws Exception {

		boolean oVisible = oElement.isDisplayed();

		try {

			// while (oVisible=false){
			// action.sendKeys(Keys.PAGE_DOWN);
			// }

			TimeUnit.SECONDS.sleep(1);
			Actions actions = new Actions(driver);
			actions.moveToElement(oElement);
			actions.perform();

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in scrollToElement(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

	}
	
	

	/*******************************************************************************
	 * @author EF9749
	 * @Description - Scrolls to the element and clicks on it
	 * @param oElement
	 *            - the WebElement object
	 * @throws InterruptedException
	 * @throws Exception
	 *******************************************************************************/

	public void scrollAndClick(WebElement oElement) throws Exception {

		int elementPosition = oElement.getLocation().getY();

		try {

			String js = String.format("window.scroll(0, %s)", elementPosition);
			((JavascriptExecutor) driver).executeScript(js);
			oElement.click();

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in isChecked(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	/*******************************************************************************
	 * @author EF9749
	 * @Description - this switches to a frame, a window or an alert
	 * @param sParam
	 *            - string containing the window or alert or a frame
	 * @throws InterruptedException
	 * @throws Exception
	 *******************************************************************************/

	public void switchTo(String sValueObjectId) throws Exception {

		String string = sValueObjectId;
		String sValue = null;
		String ObjId = null;
		String winframealert = null;
		String temp = null;
		String locType = null;

		// count number of semicolons in the parameter
		int iCnt = StringUtils.countMatches(sValueObjectId, ";");
		System.out.println("Number of delimiter: " + iCnt + "");

		try {

			if (string.contains(";")) {
				String[] parts = string.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];

				System.out.println(sValue);
				System.out.println(ObjId);

				// why 3?? --> because there's an added logic to include the
				// parameter name in the test data in
				// in the feature file. if there are 3 semicolon delimitere it
				// means it has a frame or window
				if (iCnt == 3) {

					winframealert = parts[2];
					System.out.println(winframealert);

					System.out.println("Object has Window/Frame/Alert: " + ObjId);

					if (winframealert.toLowerCase().contains("frame")) {
						if(winframealert.toLowerCase().contains("//iframe["))
							this.driver.switchTo().frame(driver.findElement(By.xpath(winframealert)));
						else
							this.driver.switchTo().frame(winframealert);
						System.out.println("Switch Frame");
					} else if (winframealert.toLowerCase().contains("window")) {

						// Switch to new window opened
						for (String winHandle : driver.getWindowHandles()) {
							driver.switchTo().window(winHandle);
						}
						// winHandleBefore = this.driver.getWindowHandle();
						// try{
						// this.changeWindow();
						// }catch(Exception e){}

						System.out.println("Switch Windows");

					} else {
						System.out.println("Nothing to switch to");
					}

				}

			} else {
				// do nothing
			}

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in switchTo(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	// Added by Justin
	public String winHandleBefore = null;

	public void changeWindow() throws Exception {

		Thread.sleep(5000);
		Set<String> handle = driver.getWindowHandles();

		System.out.println("Parent Window Handle --> " + winHandleBefore);

		try {

			if (handle.size() > 1) {
				System.out.println("Set handle values: " + handle);
				System.out.println("Size of handle: " + handle.size());
				System.out.println("");
				String strArray[] = new String[handle.size()];
				// handle.remove(winHandleBefore);
				String strWindow = null;
				int iCtr = 0;
				for (String window : handle) {
					System.out.println("Window Handle --> " + window);
					strArray[iCtr] = window;
					iCtr += 1;
					// if(iCtr==handle.size()-1){
					// driver.switchTo().window(window);
					// }
				} // for
				System.out.println("Last Window Open: " + strArray[handle.size() - 1]);
				Thread.sleep(3000);

				// driver.close();
				// driver.switchTo().window(winHandleBefore);

			} // if

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in changedWindow(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

	}

	public boolean navigateMenu(String sValueObjectId) throws Exception {

		String string = sValueObjectId;
		String sValue = null;
		String ObjId = null;
		String frame = null;
		boolean blnReturn = false;

		// count number of semicolons in the parameter
		int iCnt = StringUtils.countMatches(sValueObjectId, ";");
		System.out.println("Number of delimiter: " + iCnt + "");

		try {

			if (string.contains(";")) {
				String[] parts = string.split(";");
				sValue = parts[0];
				ObjId = parts[1];
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ObjId = sValueObjectId;

			}

			switchTo(sValueObjectId);

			if (ObjId.contains(">>")) {

				String[] strObj = ObjId.split(">>");

				for (int i = 0; i < strObj.length; i++) {

					wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(strObj[i])));
					wait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(strObj[i])));

					List<WebElement> oList = this.driver.findElements(By.xpath(strObj[i]));

					if (oList.size() > 0) {

						if (i == strObj.length - 1) {
							Actions act = new Actions(driver);

							// act.moveToElement(oList.get(0)).click().perform();
							oList.get(0).click();
							// act.moveToElement(oList.get(0));
							// act.clickAndHold();
							// TimeUnit.SECONDS.sleep(1);
							// act.release();
							// act.perform();

							blnReturn = true;

							TimeUnit.SECONDS.sleep(1);
						} else {
							Actions builder = new Actions(driver);
							// Move cursor to the Main Menu Element
							builder.moveToElement(oList.get(0));
							// builder.click();
							// builder.clickAndHold();
							// builder.release();
							builder.perform();
							// blnReturn = true;
							// TimeUnit.SECONDS.sleep(1);
						}

					} else {
						ReportResults("FAIL", "Object does not exist", true);
					}
				} // for(int i

			} // if(ObjId

			else {
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));
				wait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjId)));

				List<WebElement> oList = this.driver.findElements(By.xpath(ObjId));
				if (oList.size() > 0) {
					Actions builder = new Actions(driver);
					// Move cursor to the Main Menu Element
					// builder.moveToElement(oList.get(0),10,10);
					builder.moveToElement(oList.get(0), 10, 10);
					builder.clickAndHold();
					builder.release();
					builder.perform();
					// builder.click(oList.get(0)).perform();
					// System.out.println("This is oList.get(0) value: " +
					// oList.get(0));
					blnReturn = true;
				} else {
					ReportResults("FAIL", "Object does not exist", true);
				}
			}
			driver.switchTo().defaultContent();

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in navigateMenu(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
		return blnReturn;
	}// navigate

	/*******************************************************************************
	 * @author EE7658
	 * @Description - This is the generic method for verifying an item in the
	 *              dropdown
	 * @param sValueObjectId
	 *            - this contains the object id/xpath of the dropdown
	 * @throws Exception
	 *******************************************************************************//*
																						 * 
																						 * public boolean
																						 * verifyDrowdownContents(String
																						 * sValueObjectId) throws
																						 * Exception { String string =
																						 * sValueObjectId; String sValue
																						 * = null; String ObjId = null;
																						 * String frame = null; String
																						 * locType = null; String temp =
																						 * null; boolean blnCheckValue =
																						 * false; // count number of
																						 * semicolons in the parameter
																						 * int iCnt =
																						 * StringUtils.countMatches(
																						 * sValueObjectId, ";");
																						 * System.out.
																						 * println("Number of delimiter: "
																						 * + iCnt + "");
																						 * 
																						 * try {
																						 * 
																						 * if (string.contains(";")) {
																						 * String[] parts =
																						 * string.split(";"); sValue =
																						 * parts[0]; temp = parts[1];
																						 * String[] parts1 =
																						 * temp.split(">>"); locType =
																						 * parts1[0]; ObjId = parts1[1];
																						 * 
																						 * if(sValue.contains("\uFFFD"))
																						 * sValue =
																						 * sValue.replace("\uFFFD","-");
																						 * System.out.println(sValue);
																						 * System.out.println(ObjId);
																						 * 
																						 * } else { ObjId =
																						 * sValueObjectId; }
																						 * 
																						 * switchTo(sValueObjectId);
																						 * 
																						 * // wait30.until(
																						 * ExpectedConditions.
																						 * presenceOfElementLocated(By.
																						 * xpath(ObjId))); // //
																						 * List<WebElement> oList = //
																						 * this.driver.findElements(By.
																						 * xpath(ObjId));
																						 * List<WebElement> oList =
																						 * findElement(ObjId, locType);
																						 * // WebElement mySelectElm =
																						 * driver.findElement(By.id(
																						 * "mySelectID")); // Select
																						 * mySelect= new
																						 * Select(mySelectElm);
																						 * 
																						 * if (oList.size() > 0) { //
																						 * Added by Justin 01102017 //
																						 * ((JavascriptExecutor)driver).
																						 * executeScript(
																						 * "window.scrollTo(0,"+oList.
																						 * get(0).getLocation().y+")");
																						 * ((JavascriptExecutor)
																						 * driver).executeScript(
																						 * "window.scrollTo(0," +
																						 * oList.get(0).getLocation().x
																						 * + ")");
																						 * 
																						 * scrollToElement(oList.get(0))
																						 * ; // up to this 01102017
																						 * 
																						 * Select select = new
																						 * Select(oList.get(0));
																						 * List<WebElement> options =
																						 * select.getOptions();
																						 * oList.get(0).click();
																						 * System.out.
																						 * println("The value to be checked in the dropdown: "
																						 * + sValue); System.out.
																						 * println("Dropdown Values: ");
																						 * //
																						 * System.out.println(sValue);
																						 * for (WebElement option :
																						 * options) {
																						 * System.out.println(option.
																						 * getText()); } for (WebElement
																						 * option : options) { //
																						 * System.out.println(option.
																						 * getText().trim()); if
																						 * (option.getText().trim().
																						 * toString().equals(sValue.trim
																						 * ())) { //
																						 * System.out.println("true");
																						 * blnCheckValue = true; return
																						 * blnCheckValue; } // else { //
																						 * System.out.
																						 * println("here else"); // } }
																						 * 
																						 * } else {
																						 * ReportResults("FAIL",
																						 * "Object does not exist",
																						 * true); blnCheckValue = false;
																						 * }
																						 * 
																						 * driver.switchTo().
																						 * defaultContent();
																						 * 
																						 * } catch (Exception e) {
																						 * ReportResults("FAIL",
																						 * "Unexpected error/exception occured in verifyDropdownContents(): "
																						 * + e.getMessage(), true);
																						 * System.out.println(e.
																						 * getMessage() + "/n" +
																						 * e.getStackTrace()); } return
																						 * blnCheckValue;
																						 * 
																						 * }// verify a value in a
																						 * dropdown if existing
																						 */

	/*******************************************************************************
	 * @author EE7658, updated by JLQL
	 * @Description - This is the generic method for hitting Enter
	 * @param
	 * @throws Exception
	 *******************************************************************************/
	public void hitSelect() throws Exception {
		try {
			driver.switchTo().activeElement().sendKeys(Keys.ENTER);
		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in hitSelect(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

	}


	/*******************************************************************************
	 * @author NRGJ
	 * @Description - This is the generic method for refreshing the page
	 * @param
	 * @throws Exception
	 *******************************************************************************/
	public void refresh() throws Exception {
		try {
			driver.navigate().refresh();
			System.out.println("Page refresh");
			
		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in refresh(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	
	public boolean selectRx(String sObjId) throws Exception {

		String sValue = null;
		String ObjId = null;
		String frame = null;

		boolean bOutcome = false;

		try {
			if (sObjId.contains(";")) {

				String[] parts = sObjId.split(";");
				sValue = parts[0];
				ObjId = parts[1];
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ObjId = sObjId;
			}

			// Check if needed to switch to a frame or a window
			switchTo(sObjId);

			wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));
			wait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjId)));

			List<WebElement> oList = this.driver.findElements(By.xpath(ObjId));

			if (oList.size() > 0) {
				// before clicking it, ensure that it is displayed in the screen
				// Actions actions = new Actions(driver);
				// actions.moveToElement(oList.get(0)).click();
				// actions.perform();

				// scrollToElement(oList.get(0));
				// this.scrollAndClick(oList.get(0));

				oList.get(0).click();

				bOutcome = true;
			} else {
				ReportResults("FAIL", "Object - The Object: " + ObjId + " does not exist", true);
			}

			driver.switchTo().defaultContent();

			// TimeUnit.SECONDS.sleep(20);
		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in selectRx(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
		return bOutcome;

	}

	/**********************************************************************************
	 * @author EF9749
	 * @Description - This is the generic script for double clicking an object
	 *              (button, links, images, labels etc.)
	 * @param sValueObjectId
	 *            - this contains the object id/xpath of the
	 *            button/links/images/labels etc; e.g. "//button[@id='Submit']"
	 * @throws Exception
	 *******************************************************************************/
	public boolean doubleclickButton(String sObjId) throws Exception {

		String sValue = null;
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;
		boolean bOutcome = false;

		try {

			if (sObjId.contains(";")) {

				String[] parts = sObjId.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ObjId = sObjId;
			}

			// Check if needed to switch to a frame or a window
			switchTo(sObjId);

			// wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));
			// wait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjId)));
			//
			// List<WebElement> oList =
			// this.driver.findElements(By.xpath(ObjId));
			List<WebElement> oList = findElement(ObjId, locType);
			if (oList.size() > 0) {
				// before clicking it, ensure that it is displayed in the screen
				Actions actions = new Actions(driver);
				actions.moveToElement(oList.get(0)).doubleClick();
				actions.perform();

				// scrollToElement(oList.get(0));

				// this.scrollAndClick(oList.get(0));
				this.winHandleBefore = this.driver.getWindowHandle();
				// this.changeWindow();
				// oList.get(0).click();

				bOutcome = true;
			} else {
				ReportResults("FAIL", "Object - The Object: " + ObjId + " does not exist", true);
			}

			driver.switchTo().defaultContent();

			// TimeUnit.SECONDS.sleep(20);
		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in doubleclickButton(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
		return bOutcome;

	}

	// Added for testing
	/**********************************************************************************
	 * @author RJDG
	 * @Description - This is the generic script for clicking an object (button,
	 *              links, images, labels etc.)
	 * @param sValueObjectId
	 *            - this contains the object id/xpath of the
	 *            button/links/images/labels etc; e.g. "//button[@id='Submit']"
	 * @throws Exception
	 *******************************************************************************/
	public boolean customClickButton(String sObjId) throws Exception {

		String sValue = null;
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;

		boolean bOutcome = false;

		try {

			if (sObjId.contains(";")) {

				String[] parts = sObjId.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ObjId = sObjId;
			}

			// Check if needed to switch to a frame or a window
			switchTo(sObjId);
			List<WebElement> oList = findElement(ObjId, locType);

			if (oList.size() > 0) {
				// before clicking it, ensure that it is displayed in the screen
				// Actions actions = new Actions(driver);
				// actions.moveToElement(oList.get(0)).click();

				// actions.perform();

				// scrollToElement(oList.get(0));

				// this.scrollAndClick(oList.get(0));
				this.winHandleBefore = this.driver.getWindowHandle();
				// this.changeWindow();
				// oList.get(0).click();
				JavascriptExecutor exec = (JavascriptExecutor) driver;
				exec.executeScript(
						"window.showModalDialog = function( sURL,vArguments, sFeatures) { window.open(sURL, 'modal', sFeatures); }");
				exec.executeScript("arguments[0].click()", oList.get(0));
				bOutcome = true;
			} else {
				ReportResults("FAIL", "Object - The Object: " + ObjId + " does not exist", true);
			}

			driver.switchTo().defaultContent();

			// TimeUnit.SECONDS.sleep(20);

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in customClickButton(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

		return bOutcome;

	}

	public void validatesorting(String sObjId) throws Exception {

	}

	public void manual(String sObjId) {

		String sValue = null;
		if (sObjId.contains(";")) {

			String[] parts = sObjId.split(";");
			sValue = parts[0];
			System.out.println(sValue);
		}
		int reply = JOptionPane.showConfirmDialog(null, sValue, "Manual Intervention", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "The Script will continue");
		} else
			System.exit(0);

	}

	public boolean dismissAlert(String sObjId) throws Exception {
		String sValue = null;
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;
		boolean bOutcome = false;

		try {

			if (sObjId.contains(";")) {

				String[] parts = sObjId.split(";");
				sValue = parts[0];
				ObjId = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ObjId = sObjId;
			}

			// Check if needed to switch to a frame or a window
			switchTo(sObjId);

			// wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));
			// wait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjId)));
			//
			// List<WebElement> oList =
			// this.driver.findElements(By.xpath(ObjId));
			List<WebElement> oList = findElement(ObjId, locType);
			if (oList.size() > 0) {
				// before clicking it, ensure that it is displayed in the screen
				// Actions actions = new Actions(driver);
				// actions.moveToElement(oList.get(0)).click();

				// actions.perform();

				// scrollToElement(oList.get(0));

				// this.scrollAndClick(oList.get(0));
				this.winHandleBefore = this.driver.getWindowHandle();
				// this.changeWindow();
				oList.get(0).click();

				System.out.println("pop up accept");
				WebDriverWait wait = new WebDriverWait(driver, 5);
				wait.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				alert.dismiss();

				bOutcome = true;
			} else {
				ReportResults("FAIL", "Object - The Object: " + ObjId + " does not exist", true);
			}

			driver.switchTo().defaultContent();

			// TimeUnit.SECONDS.sleep(20);

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in clickButton(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

		return bOutcome;

	}

	public boolean acceptAlert(String sObjId) throws Exception {
		String sValue = null;
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;
		boolean bOutcome = false;

		try {

			if (sObjId.contains(";")) {

				String[] parts = sObjId.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ObjId = sObjId;
			}

			// Check if needed to switch to a frame or a window
			switchTo(sObjId);

			// wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));
			// wait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjId)));
			//
			// List<WebElement> oList =
			// this.driver.findElements(By.xpath(ObjId));
			List<WebElement> oList = findElement(ObjId, locType);
			if (oList.size() > 0) {
				// before clicking it, ensure that it is displayed in the screen
				// Actions actions = new Actions(driver);
				// actions.moveToElement(oList.get(0)).click();

				// actions.perform();

				// scrollToElement(oList.get(0));

				// this.scrollAndClick(oList.get(0));
				this.winHandleBefore = this.driver.getWindowHandle();
				// this.changeWindow();
				oList.get(0).click();

				System.out.println("pop up accept");
				WebDriverWait wait = new WebDriverWait(driver, 5);
				wait.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				alert.accept();

				bOutcome = true;
			} else {
				ReportResults("FAIL", "Object - The Object: " + ObjId + " does not exist", true);
			}

			driver.switchTo().defaultContent();

			// TimeUnit.SECONDS.sleep(20);

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in clickButton(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

		return bOutcome;

	}

	// RJDG 06/16/17

	public List<WebElement> findElement(String ObjId, String locType) {

		List<WebElement> oList = null;

		// List<WebElement> oList = this.driver.findElements(By.xpath(ObjId));
		if (locType.equalsIgnoreCase("id")) {
			wait30.until(ExpectedConditions.presenceOfElementLocated(By.id(ObjId)));
			wait30.until(ExpectedConditions.visibilityOfElementLocated(By.id(ObjId)));
			oList = this.driver.findElements(By.id(ObjId));
		} else if (locType.equalsIgnoreCase("name")) {
			wait30.until(ExpectedConditions.presenceOfElementLocated(By.name(ObjId)));
			wait30.until(ExpectedConditions.visibilityOfElementLocated(By.name(ObjId)));
			oList = this.driver.findElements(By.name(ObjId));
		} else if (locType.equalsIgnoreCase("className")) {
			wait30.until(ExpectedConditions.presenceOfElementLocated(By.className(ObjId)));
			wait30.until(ExpectedConditions.visibilityOfElementLocated(By.className(ObjId)));
			oList = this.driver.findElements(By.className(ObjId));
		} else if (locType.equalsIgnoreCase("cssSelector")) {
			wait30.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ObjId)));
			wait30.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ObjId)));
			oList = this.driver.findElements(By.cssSelector(ObjId));
		} else if (locType.equalsIgnoreCase("xpath")) {
			wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));
			wait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjId)));
			oList = this.driver.findElements(By.xpath(ObjId));
		}

		return oList;
	}

	public List<WebElement> editedfindElement(String ObjId, String locType) {

		List<WebElement> oList = null;

		// List<WebElement> oList = this.driver.findElements(By.xpath(ObjId));
		if (locType.equalsIgnoreCase("id")) {
			oList = this.driver.findElements(By.id(ObjId));
		} else if (locType.equalsIgnoreCase("name")) {
			oList = this.driver.findElements(By.name(ObjId));
		} else if (locType.equalsIgnoreCase("className")) {
				oList = this.driver.findElements(By.className(ObjId));
		} else if (locType.equalsIgnoreCase("cssSelector")) {
			oList = this.driver.findElements(By.cssSelector(ObjId));
		} else if (locType.equalsIgnoreCase("xpath")) {
			oList = this.driver.findElements(By.xpath(ObjId));
		}

		return oList;
	}

	
	public void isEnable(String sObjId) throws Exception {
		String sValue = null;
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;

		try {

			if (sObjId.contains(";")) {

				String[] parts = sObjId.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ObjId = sObjId;
			}

			// Check if needed to switch to a frame or a window
			switchTo(sObjId);
			List<WebElement> oList = findElement(ObjId, locType);

			if (oList.size() > 0) {
				// before clicking it, ensure that it is displayed in the screen
				// Actions actions = new Actions(driver);
				// actions.moveToElement(oList.get(0)).click();

				// actions.perform();

				// scrollToElement(oList.get(0));
				boolean result = oList.get(0).isEnabled();
				System.out.println(result);
				if (sValue.equalsIgnoreCase("true") && result) {
					ReportResults("PASS", "Object - The Object: " + ObjId + " is Enabled", true);
				} else if (sValue.equalsIgnoreCase("false") && !(result)) {
					ReportResults("PASS", "Object - The Object: " + ObjId + " is Disabled", true);
				} else if (sValue.equalsIgnoreCase("true") && !(result)) {
					ReportResults("FAIL", "Object - The Object: " + ObjId + " must not be Disabled", true);
				} else if (sValue.equalsIgnoreCase("false") && result) {
					ReportResults("FAIL", "Object - The Object: " + ObjId + " must not be Enabled", true);
				}

				// this.scrollAndClick(oList.get(0));
				this.winHandleBefore = this.driver.getWindowHandle();

			} else {
				ReportResults("FAIL", "Object - The Object: " + ObjId + " does not exist", true);
			}

			driver.switchTo().defaultContent();

			// TimeUnit.SECONDS.sleep(20);

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in isEnable(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	public void isSelect(String sObjId) throws Exception {
		String sValue = null;
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;

		try {

			if (sObjId.contains(";")) {

				String[] parts = sObjId.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ObjId = sObjId;
			}

			// Check if needed to switch to a frame or a window
			switchTo(sObjId);
			List<WebElement> oList = findElement(ObjId, locType);

			if (oList.size() > 0) {
				// scrollToElement(oList.get(0));
				boolean result = oList.get(0).isSelected();
				System.out.println(result);
				if (sValue.equalsIgnoreCase("true") && result) {
					ReportResults("PASS", "Object - The Object: " + ObjId + " is Selected", true);
				} else if (sValue.equalsIgnoreCase("false") && !(result)) {
					ReportResults("PASS", "Object - The Object: " + ObjId + " is not Selected", true);
				} else if (sValue.equalsIgnoreCase("true") && !(result)) {
					ReportResults("FAIL", "Object - The Object: " + ObjId + " must be Selected", true);
				} else if (sValue.equalsIgnoreCase("false") && result) {
					ReportResults("FAIL", "Object - The Object: " + ObjId + " must not be Selected", true);
				}

				// this.scrollAndClick(oList.get(0));
				this.winHandleBefore = this.driver.getWindowHandle();

			} else {
				ReportResults("FAIL", "Object - The Object: " + ObjId + " does not exist", true);
			}

			driver.switchTo().defaultContent();

			// TimeUnit.SECONDS.sleep(20);

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in isSelect(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	// check if working(not yet tested)
	public void getAttribute(String sObjId) throws Exception {
		String sValue = null;
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;
		String temp0 = null;
		String attrType = null;
		try {

			if (sObjId.contains(";")) {

				String[] parts = sObjId.split(";");
				temp0 = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				String[] parts2 = temp0.split(">>");
				attrType = parts2[0];
				sValue = parts2[1];
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ObjId = sObjId;
			}

			// Check if needed to switch to a frame or a window
			switchTo(sObjId);
			List<WebElement> oList = findElement(ObjId, locType);

			if (oList.size() > 0) {
				// scrollToElement(oList.get(0));
				String result = oList.get(0).getAttribute(attrType);
				System.out.println(result);

				if (result.equals(sValue))
					ReportResults("PASS", "Object - The Object: " + ObjId + " is matched with " + result, true);
				else
					ReportResults("FAIL", "Object - The Object: " + ObjId + " is not matched with " + result, true);
				// this.scrollAndClick(oList.get(0));
				this.winHandleBefore = this.driver.getWindowHandle();

			} else {
				ReportResults("FAIL", "Object - The Object: " + ObjId + " does not exist", true);
			}

			driver.switchTo().defaultContent();

			// TimeUnit.SECONDS.sleep(20);

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in getAttribute(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	public void ifDropDownAutoSelect(String sObjId) throws Exception {
		String sValue = null;
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;

		try {

			if (sObjId.contains(";")) {

				String[] parts = sObjId.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];

				// if(sValue.contains("\uFFFD"))
				// sValue = sValue.replace("\uFFFD","-");
				// System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ObjId = sObjId;
			}

			// Check if needed to switch to a frame or a window
			switchTo(sObjId);
			List<WebElement> oList = findElement(ObjId, locType);

			if (oList.size() > 0) {
				Select select = new Select(oList.get(0));
				// scrollToElement(oList.get(0));

				String selectedValue = select.getFirstSelectedOption().getText();
				System.out.println(selectedValue);

				if (selectedValue.equals(sValue))
					ReportResults("PASS", "Object - The Object: " + ObjId + " contains " + selectedValue, true);
				else
					ReportResults("FAIL", "Object - The Object: " + ObjId + " does not contains " + selectedValue,
							true);

			} else {
				ReportResults("FAIL", "Object - The Object: " + ObjId + " does not exist", true);
			}

			driver.switchTo().defaultContent();

			// TimeUnit.SECONDS.sleep(20);

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in ifDropDownAutoSelect(): " + e.getMessage(),
					true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	// multiple validation (for debugging)
	public void verifyAllDrowdownContents(String sValueObjectId) throws Exception {
		String string = sValueObjectId;
		List<String> sValue = new ArrayList<String>();
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;
		String temp0 = null;

		// count number of semicolons in the parameter
		int iCnt = StringUtils.countMatches(sValueObjectId, ";");
		System.out.println("Number of delimiter: " + iCnt + "");

		try {
			if (string.contains(";")) {
				String[] parts = string.split(";");
				temp0 = parts[0];
				temp = parts[1];
				String[] parts2 = temp0.split(">>");
				String[] parts1 = temp.split(">>");
				for (int x = 0; x < parts2.length; x++) {
					sValue.add(parts2[x]);
				}
				locType = parts1[0];
				ObjId = parts1[1];
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ObjId = sValueObjectId;
			}

			switchTo(sValueObjectId);

			List<WebElement> oList = findElement(ObjId, locType);

			if (oList.size() > 0) {
				// Added by Justin 01102017
				// ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+oList.get(0).getLocation().y+")");
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + oList.get(0).getLocation().x + ")");

				// scrollToElement(oList.get(0));
				// up to this 01102017

				Select select = new Select(oList.get(0));
				List<WebElement> options = select.getOptions();
				oList.get(0).click();
				System.out.println("The value to be checked in the dropdown: " + sValue);
				System.out.println("Dropdown Values: ");

				// List VISIBLE dropdown values
				List<String> visibleOptionsList = new ArrayList<String>();
				for (int i = 0; i < options.size(); i++)
					visibleOptionsList.add(options.get(i).getText());

				Set<String> expected = new HashSet<>(sValue);
				Set<String> actual = new HashSet<>(visibleOptionsList);

				Set<String> missingInExpected = new HashSet<>(expected);
				missingInExpected.removeAll(actual);

				Set<String> notInExpected = new HashSet<>(actual);
				notInExpected.removeAll(expected);

				int missingInExpectedListSize = missingInExpected.size();
				int notInExpectedListSize = notInExpected.size();

				if ((missingInExpectedListSize == 0) && (notInExpectedListSize == 0)) {
					ReportResults("PASS", "ALL expected values are in the list", true);
					// System.out.println("ALL expected values are in the
					// list");
				} else if ((missingInExpectedListSize > 0) && (notInExpectedListSize == 0)) {
					ReportResults("FAIL", "MISSING expected result: " + missingInExpected, true);
					// System.out.println("MISSING expected result: " +
					// missingInExpected);
				} else if ((missingInExpectedListSize == 0) && (notInExpectedListSize > 0)) {
					ReportResults("FAIL", "NOT in the expected result : " + notInExpected, true);
					// System.out.println("NOT in the expected result : " +
					// notInExpected);
				} else if ((missingInExpectedListSize > 0) && (notInExpectedListSize > 0)) {
					ReportResults("FAIL", "MISSING expected result    : " + missingInExpected + "\n"
							+ "NOT in the expected result : " + notInExpected, true);
				}

			} else {
				ReportResults("FAIL", "Object does not exist", true);
			}

			driver.switchTo().defaultContent();

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in verifyDropdownContents(): " + e.getMessage(),
					true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

	}// verify a value in a dropdown if existing

	/*******************************************************************************
	 * @author RJDG
	 * @Description - This is the generic method for verifying an item if not
	 *              included in the dropdown
	 * @param sValueObjectId
	 *            - this contains the object id/xpath of the dropdown
	 * @throws Exception
	 *******************************************************************************/

	public boolean verifyDrowdownNotContains(String sValueObjectId) throws Exception {
		String string = sValueObjectId;
		List<String> sValue = new ArrayList<String>();
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;
		String temp0 = null;
		boolean blnCheckValue = true;
		int count = 0;
		// count number of semicolons in the parameter
		int iCnt = StringUtils.countMatches(sValueObjectId, ";");
		System.out.println("Number of delimiter: " + iCnt + "");
		try {
			if (string.contains(";")) {
				String[] parts = string.split(";");
				temp0 = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				String[] parts2 = temp0.split(">>");
				for (int x = 0; x < parts2.length; x++)
					sValue.add(parts2[x]);

				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ObjId = sValueObjectId;
			}

			switchTo(sValueObjectId);
			List<WebElement> oList = findElement(ObjId, locType);
			if (oList.size() > 0) {
				// Added by Justin 01102017
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + oList.get(0).getLocation().x + ")");
				// scrollToElement(oList.get(0));
				Select select = new Select(oList.get(0));
				List<WebElement> options = select.getOptions();
				oList.get(0).click();
				System.out.println("The value to be checked must not be in the dropdown: " + sValue);
				System.out.println("Dropdown Values: ");
				for (WebElement option : options) {
					System.out.println(option.getText());
				}
				int sizeTemp = sValue.size();
				List<String> notExpected = null;
				for (WebElement option : options) {
					System.out.println(option.getText());
					for (int x = 0; x < sValue.size(); x++) {
						if (option.getText().trim().toString().equals(sValue.get(x).trim())) {
							notExpected.add(option.getText());
							count++;
						}
						if (count > 0) {
							System.out.println("Values is in the dropdown");
							ReportResults("FAIL", "Values are not expected: " + notExpected, false);
						}
					}
				}
			} else {
				ReportResults("FAIL", "Object does not exist", true);
				blnCheckValue = true;
			}

			driver.switchTo().defaultContent();

		} catch (Exception e) {
			ReportResults("FAIL",
					"Unexpected error/exception occured in verifyDropdownNotContains(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
		return blnCheckValue;

	}// verify a value in a dropdown if existing

	/*******************************************************************************
	 * @author EE7658
	 * @Description - This is the generic method for verifying items in the dropdown
	 * @param sValueObjectId
	 *            - this contains the object id/xpath of the dropdown
	 * @throws Exception
	 *******************************************************************************/

	public boolean verifyDrowdownContents1(String sValueObjectId) throws Exception {
		String string = sValueObjectId;
		List<String> sValue = new ArrayList<String>();
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;
		String temp0 = null;
		String temp1 = null;
		int count = 0;
		boolean blnCheckValue = false;
		// count number of semicolons in the parameter
		int iCnt = StringUtils.countMatches(sValueObjectId, ";");
		System.out.println("Number of delimiter: " + iCnt + "");
		try {

			if (string.contains(";")) {
				String[] parts = string.split(";");
				temp0 = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				String[] parts2 = temp0.split(">>");
				for (int x = 0; x < parts2.length; x++)
					sValue.add(parts2[x]);
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ObjId = sValueObjectId;
			}

			switchTo(sValueObjectId);
			List<WebElement> oList = findElement(ObjId, locType);

			if (oList.size() > 0) {
				// Added by Justin 01102017
				// ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+oList.get(0).getLocation().y+")");
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + oList.get(0).getLocation().x + ")");

				// scrollToElement(oList.get(0));
				// up to this 01102017

				Select select = new Select(oList.get(0));
				List<WebElement> options = select.getOptions();
				oList.get(0).click();
				System.out.println("The value to be checked in the dropdown: " + sValue);
				System.out.println("Dropdown Values: ");
				// System.out.println(sValue.size());
				int sizeTemp = sValue.size();
				for (WebElement option : options) {
					System.out.println(option.getText());
					for (int x = 0; x < sValue.size(); x++) {
						if (option.getText().trim().toString().equals(sValue.get(x).trim())) {
							sValue.remove(x);
							count++;
							if (count == sizeTemp)
								return true;
						}
					}
				}

			} else {
				ReportResults("FAIL", "Object does not exist", true);
				blnCheckValue = false;
			}

			driver.switchTo().defaultContent();

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in verifyDropdownContents1(): " + e.getMessage(),
					true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

		ReportResults("FAIL", "Object is not found:" + sValue, false);
		return blnCheckValue;

	}

	public void waitTime(String sObjId) throws Exception {
		String sValue = null;

		try {

			if (sObjId.contains(";")) {
				String[] parts = sObjId.split(";");
				sValue = parts[0];
				System.out.println("Wait for " + sValue + " seconds");

				TimeUnit.SECONDS.sleep(Long.parseLong(sValue));
				// driver.manage().timeouts().implicitlyWait(Long.parseLong(sValue),
				// TimeUnit.SECONDS);
			}

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in waitTime(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	public void compareText(String sObjId) throws Exception {
		String sValue = null;
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;

		try {
			if (sObjId.contains(";")) {
				String[] parts = sObjId.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				System.out.println(ObjId);
			} else {
				ObjId = sObjId;
			}

			// Check if needed to switch to a frame or a window
			switchTo(sObjId);
			List<WebElement> oList = findElement(ObjId, locType);

			if (oList.size() > 0) {
				// scrollToElement(oList.get(0));
				System.out.println(oList.get(0).getText());
				if (oList.get(0).getText().contains(sValue))
					ReportResults("PASS", "Object - The Object value: " + sValue + " is matched", true);
				else
					ReportResults("FAIL", "Object - The Object value: " + sValue + " does not matched", true);

			} else {
				ReportResults("FAIL", "Object - The Object: " + ObjId + " does not exist", true);
			}

			driver.switchTo().defaultContent();

			// TimeUnit.SECONDS.sleep(20);

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in compareText(): " + e.getMessage(),
					true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	/*******************************************************************************
	 * @author RJDG
	 * @Description - This is the generic script for Entering text to a text field
	 * @param sValueObjectId
	 *            - this contains the Value to be filled up in the textbox and the
	 *            object id/xpath of the textfield; e.g.
	 *            "Gabriel;//input[@id='firstName']"
	 * @throws Exception
	 *******************************************************************************/
	public void clearText(String pValueObjectId) throws Exception {

		String sValueObject = pValueObjectId;
		String sValue = null;
		String ObjId = null;
		String frame = null;

		String locType = null;
		String temp = null;
		try {

			if (sValueObject.contains(";")) {

				String[] parts = sValueObject.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ReportResults("FAIL", "Enter Text - The parameter " + sValueObject + "", true);
			}

			switchTo(pValueObjectId);

			// wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));

			List<WebElement> oList = findElement(ObjId, locType);

			// TimeUnit.SECONDS.sleep(2);

			if (oList.size() > 0) {
				oList.get(0).click();
				oList.get(0).clear();
				// oList.get(0).sendKeys(sValue);
				ReportResults("PASS", "Successfully clear text: " + sValue + " on this object: " + ObjId, true);
			} else {
				ReportResults("FAIL", "Clear Text - The Object: " + ObjId + " does not exist", true);
			}

			driver.switchTo().defaultContent();

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in clear(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	/*******************************************************************************
	 * @author JLQL
	 * @Description - This is the generic script for Entering text to a custom
	 *              dropdown field
	 * @param sValueObjectId
	 *            - this contains the Value to be filled up in the dropdown and the
	 *            object id/xpath of the dropdown fields;
	 * @throws Exception
	 *******************************************************************************/
	public void enterDropDown(String pValueObjectId) throws Exception {

		String sValueObject = pValueObjectId;
		String sValue = null;
		String ObjId = null;
		String frame = null;

		String locType = null;
		String temp = null;
		try {

			if (sValueObject.contains(";")) {

				String[] parts = sValueObject.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ReportResults("FAIL", "Enter Text - The parameter " + sValueObject + "", true);
			}

			switchTo(pValueObjectId);

			// wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));

			List<WebElement> oList = findElement(ObjId, locType);

			// TimeUnit.SECONDS.sleep(2);

			if (oList.size() > 0) {
				// oList.get(0).click();
				Thread.sleep(2000);
				oList.get(0).sendKeys(sValue, Keys.ENTER);
				ReportResults("PASS", "Successfully entered text: " + sValue + " on this object: " + ObjId, true);
			} else {
				ReportResults("FAIL", "Enter Text - The Object: " + ObjId + " does not exist", true);
			}

			driver.switchTo().defaultContent();

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in enterDropDown(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	/*******************************************************************************
	 * @author JLQL feat RJDG
	 * @Description - This is the generic script for Entering text to a custom
	 *              dropdown field
	 * @param sValueObjectId
	 *            - this contains the Value to be filled up in the dropdown and the
	 *            object id/xpath of the dropdown fields;
	 * @throws Exception
	 *******************************************************************************/
	public void dropdownNone(String pValueObjectId) throws Exception {

		String sValueObject = pValueObjectId;
		String sValue = null;
		String ObjId = null;
		String frame = null;

		String locType = null;
		String temp = null;
		try {

			if (sValueObject.contains(";")) {

				String[] parts = sValueObject.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ReportResults("FAIL", "Enter Text - The parameter " + sValueObject + "", true);
			}

			switchTo(pValueObjectId);

			// wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));

			List<WebElement> oList = findElement(ObjId, locType);

			// TimeUnit.SECONDS.sleep(2);

			if (oList.size() > 0) {
				// oList.get(0).click();
				Thread.sleep(1500);
				oList.get(0).sendKeys(Keys.DOWN, Keys.ENTER);
				ReportResults("PASS", "Successfully entered text: " + sValue + " on this object: " + ObjId, true);
			} else {
				ReportResults("FAIL", "Enter Text - The Object: " + ObjId + " does not exist", true);
			}

			driver.switchTo().defaultContent();

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in enterDropDown(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	/*******************************************************************************
	 * @author RJDG
	 * @Description - This is the generic script for Entering text to a custom
	 *              dropdown field
	 * @param sValueObjectId
	 *            - this contains the Value to be filled up in the dropdown and the
	 *            object id/xpath of the dropdown fields;
	 * @throws Exception
	 *******************************************************************************/
	public void fileupload(String fileLocation) throws Exception {

		String sValue = null;
		if (fileLocation.contains(";")) {

			String[] parts = fileLocation.split(";");
			sValue = parts[0];
			System.out.println(sValue);
		}
		StringSelection ss = new StringSelection(sValue);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Keyboard keyboard = new Keyboard();

		Thread.sleep(1000);
		System.out.println("Done1");
		keyboard.keyPress(17);
		System.out.println("Done2");
		Thread.sleep(1000);
		keyboard.keyPress(86);
		System.out.println("Done3");
		Thread.sleep(1000);
		keyboard.keyRelease(17);
		System.out.println("Done4");
		Thread.sleep(1000);
		keyboard.keyRelease(86);
		System.out.println("Done5");

		ReportResults("TRUE", "File upload", false);
		// ReportLog.logEvent("True", "[File Upload: " + ss + "] Sending File
		// Location");

		Thread.sleep(3000);

		keyboard.keyPress(18);

		Thread.sleep(3000);

		keyboard.keyPress(79);
		Thread.sleep(1000);
		keyboard.keyRelease(79);
		Thread.sleep(1000);
		keyboard.keyRelease(18);

		ReportResults("TRUE", "[File Upload: ENTER KEY ] Press Key", false);

		Thread.sleep(5000);
	}

	/*******************************************************************************
	 * @author NRGJ
	 * @Description - This is the generic script for verifying the format of the
	 *              text.
	 * @param sValueObjectId
	 *            - this contains the type of format that will be verified and the
	 *            object id/xpath of the element; e.g. "email;//input[@id='email']"
	 * @throws Exception
	 *******************************************************************************/
	public boolean verifyformat(String sValueObjectId) throws Exception {

		TimeUnit.SECONDS.sleep(5);
		boolean bResult = false;

		String string = sValueObjectId;
		String sValue = null;
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;
		String format = null;
		// count number of semicolons in the parameter
		int iCnt = StringUtils.countMatches(sValueObjectId, ";");
		System.out.println("Number of delimiter: " + iCnt + "");

		try {

			if (string.contains(";")) {
				String[] parts = string.split(";");
				sValue = parts[0];
				if (sValue.contains(">>")) {
					String sValueparts[] = sValue.split(">>");
					sValue = sValueparts[0];
					format = sValueparts[1];
				}
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];

				System.out.println(sValue);
				System.out.println(ObjId);

			} else {

				ObjId = sValueObjectId;

			}

			switchTo(sValueObjectId);

			// wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));
			List<WebElement> oList = findElement(ObjId, locType);
			// List<WebElement> oList = driver.findElements(By.xpath(ObjId));

			if (oList.size() > 0) {
				// scrollToElement(oList.get(0));
				String strToFormat = oList.get(0).getText().length() > 0 ? oList.get(0).getText()
						: oList.get(0).getAttribute("value");

				String s = "\n";
				if (strToFormat.contains(s)) {
					strToFormat = strToFormat.replace("\n", " ");
				}

				System.out.println("Actual msg: " + strToFormat);

				switch (sValue) {
				case "date":

					Date date = null;
					SimpleDateFormat sdf = new SimpleDateFormat(format);
					date = sdf.parse(strToFormat);
					if (!strToFormat.equals(sdf.format(date))) {
						date = null;
					}

					System.out.println("Expected format: " + format);

					if (date != null) {
						bResult = true;
						ReportResults("PASS",
								strToFormat + " has a valid " + format + " format on this object: " + ObjId, true);
					} else
						ReportResults("FAIL",
								strToFormat + " has an invalid " + format + " format on this object: " + ObjId, true);
					break;
				case "email":
					System.out.println("Expected format: sample@sample.com");
					if (VALID_EMAIL_ADDRESS.matcher(strToFormat).matches()) {
						bResult = true;
						ReportResults("PASS", strToFormat + " has a valid email format on this object: " + ObjId, true);
					} else
						ReportResults("FAIL", strToFormat + " has an invalid email format on this object: " + ObjId,
								true);

					break;
				case "mobile":
					System.out.println("Expected format: +63 country code and 10-digit number");
					if (VALID_MOBILE_NUMBER.matcher(strToFormat).matches())
					{
						bResult = true;
						ReportResults("PASS", strToFormat + " has a valid mobile number format on this object: " + ObjId, true);
					} else
						ReportResults("FAIL", strToFormat + " has an invalid mobile number format on this object: " + ObjId,
								true);
					break;
					
				case "phone":
					System.out.println("Expected format: +63 country code and 1 to 2 digit area code number and 7-digit number");
					if (VALID_PHONE_NUMBER.matcher(strToFormat).matches())
					{
						bResult = true;
						ReportResults("PASS", strToFormat + " has a valid mobile number format on this object: " + ObjId, true);
					} else
						ReportResults("FAIL", strToFormat + " has an invalid mobile number format on this object: " + ObjId,
								true);
					break;
					
						
				default:
					ReportResults("FAIL", "Specified format is not defined in the framework: " + sValue, true);
					break;

				}

				// // try to change .contains to .equals
				// if (strToFormat.equals(sValue)) {
				// bResult = true;
				// } else {
				// bResult = false;
				// }

			} else {
				ReportResults("FAIL", "Object does not exist", true);
			}

			driver.switchTo().defaultContent();
		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in verifyformat(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

		return bResult;
	}

	/*******************************************************************************
	 * @author JLQL
	 * @Description - This is the generic script for checking if the field is
	 *              populated
	 * @throws Exception
	 *******************************************************************************/
	public void checkText(String sObjId) throws Exception {
		String sValue = null;
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;

		try {
			if (sObjId.contains(";")) {
				String[] parts = sObjId.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				System.out.println(ObjId);
			} else {
				ObjId = sObjId;
			}

			// Check if needed to switch to a frame or a window
			switchTo(sObjId);
			List<WebElement> oList = findElement(ObjId, locType);

			if (oList.size() > 0) {
				// scrollToElement(oList.get(0));
				System.out.println(oList.get(0).getText());
				if (oList.get(0).getText().length() > 0)
					ReportResults("PASS", "Object - The Object is populated", true);
				else
					ReportResults("FAIL", "Object - The Object is null", true);

			} else {
				ReportResults("FAIL", "Object - The Object: " + ObjId + " does not exist", true);
			}

			driver.switchTo().defaultContent();

			// TimeUnit.SECONDS.sleep(20);

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in checkPopulated(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	/*******************************************************************************
	 * @author NRGJ updated RJDG
	 * @Description - This is the generic script for storing values during runtime.
	 * @param sValueObjectId
	 *            - this contains the label and value of the data that will be
	 *            stored in the list together with the object id/xpath of the
	 *            element; e.g. "firstname>>Ralph;//input[@id='firstname']"
	 * @throws Exception
	 *******************************************************************************/

	public boolean storevalue(String sValueObjectId) throws Exception {

		TimeUnit.SECONDS.sleep(2);
		boolean bResult = false;

		String string = sValueObjectId;
		String sValue = null;
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;
		String sKey = null;
		String sAttrib = null;
		String optional=null;
		String s = "\n";
		String isChecked = "false";
		boolean sValue1 = false;
		// count number of semicolons in the parameter
		int iCnt = StringUtils.countMatches(sValueObjectId, ";");
		System.out.println("Number of delimiter: " + iCnt + "");

		try {

			if (string.contains(";")) {
				String[] parts = string.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				String[] parts2 = sValue.split(">>");
				sKey = parts2[0];
				sAttrib = parts2[1];
				optional = parts2.length>2? parts2[2]:null;
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {

				ObjId = sValueObjectId;

			}

			switchTo(sValueObjectId);
			List<WebElement> oList = null ;
			// wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));
//			if(sAttrib.equals("checked")) {
//				try {
//					oList = driver.findElements(By.xpath(ObjId));
//					sValue = "true";
//				}catch(Exception e) {
//					sValue = "false";
//					System.out.println("Key Label : " + sKey + "\nValue: " + sValue);
//
//					valueList.put(sKey, sValue);
//					ReportResults("Pass", "Successfully stored " + sKey + " and " + sValue, true);
//				}
//			}else {
				if (optional == null) {
					oList = findElement(ObjId, locType);
				} else {
					oList = editedfindElement(ObjId, locType);
				}
//			}
			// List<WebElement> oList = driver.findElements(By.xpath(ObjId));

			if (oList!=null) {
				sValue = null;
				if (sAttrib.equals("text"))
					sValue = oList.get(0).getText();
				else if(sAttrib.equals("checked")) {
				
					JavascriptExecutor exec = (JavascriptExecutor) driver;
					
					sValue1 =  (boolean)exec.executeScript("return document.getElementById('"+ObjId+"').checked");
//					if(sValue==null){
//						sValue = "false";
//					}
					sValue = String.valueOf( sValue1);
				}
				else
					sValue = oList.get(0).getAttribute(sAttrib);

				if (sValue.contains(s)) {
					sValue = sValue.replace("\n", " ");
				}

				System.out.println("Key Label : " + sKey + "\nValue: " + sValue);

				valueList.put(sKey, sValue);
				ReportResults("Pass", "Successfully stored " + sKey + " and " + sValue, true);
				// scrollToElement(oList.get(0));
				
				// // try to change .contains to .equals
				// if (strToFormat.equals(sValue)) {
				// bResult = true;
				// } else {
				// bResult = false;
				// }

			} else {
				valueList.put(sKey, null);
			}

			driver.switchTo().defaultContent();
		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in storevalue(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

		return bResult;
	}

	/*******************************************************************************
	 * @author NRGJ
	 * @Description - This is the generic script for sending a page up key command.
	 * @param sValueObjectId
	 *            - this contains the number of times the command will be repeated;
	 *            e.g. "1"
	 * @throws Exception
	 *******************************************************************************/
	public void scrollup(String sObjId) throws Exception {
		String sValue = null;
		try {
			if (sObjId.contains(";")) {
				String[] parts = sObjId.split(";");
				sValue = parts[0];
				System.out.println("Scroll up " + sValue + " time/s");
				Actions actions = new Actions(driver);
				actions.sendKeys(Keys.PAGE_UP);
				for (int i = 0; i < Integer.parseInt(sValue); i++) {
					actions.perform();
				}
			}
		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in scrollup(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	/*******************************************************************************
	 * @author NRGJ
	 * @Description - This is the generic script for sending a page up key command.
	 * @param sValueObjectId
	 *            - this contains the number of times the command will be repeated;
	 *            e.g. "1"
	 * @throws Exception
	 *******************************************************************************/
	public void scrolldown(String sObjId) throws Exception {
		String sValue = null;
		try {
			if (sObjId.contains(";")) {
				String[] parts = sObjId.split(";");
				sValue = parts[0];

				System.out.println("Scroll down " + sValue + " time/s");

				Actions actions = new Actions(driver);
				
				actions.sendKeys(Keys.PAGE_DOWN);
				for (int i = 0; i < Integer.parseInt(sValue); i++) {
					actions.perform();
				}
			}
		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in scrolldown(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	
	/*******************************************************************************
	 * FOR MERALCO
	 * 
	 * @author NRGJ
	 * @Description - This is the script for Entering text to a text field and
	 *              storing it in a list
	 * @param sValueObjectId
	 *            - this contains the Value to be filled up in the textbox and the
	 *            object id/xpath of the textfield; e.g.
	 *            "Gabriel;//input[@id='firstName']"
	 * @throws Exception
	 *******************************************************************************/
	public void customenterText(String pValueObjectId) throws Exception {

		String sValueObject = pValueObjectId;
		String sValue = null;
		String ObjId = null;
		String frame = null;
		String objName = null;
		String locType = null;
		String temp = null;
		try {

			if (sValueObject.contains(";")) {

				String[] parts = sValueObject.split(";");
				sValue = parts[0];
				temp = parts[1];
				objName = parts[2];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {
				ReportResults("FAIL", "Enter Text - The parameter " + sValueObject + "", true);
			}

			switchTo(pValueObjectId);

			// wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));

			List<WebElement> oList = findElement(ObjId, locType);

			// TimeUnit.SECONDS.sleep(2);

			if (oList.size() > 0) {
				oList.get(0).click();
				oList.get(0).clear();
				oList.get(0).sendKeys(sValue);
				valueList.put(objName, sValue);
				ReportResults("PASS", "Successfully entered and stored text: " + sValue + " on this object: " + ObjId,
						true);
			} else {
				ReportResults("FAIL", "Enter Text - The Object: " + ObjId + " does not exist", true);
			}

			driver.switchTo().defaultContent();

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in customEnterText(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
	}

	

	/*******************************************************************************
	 * @author NRGJ updated RJDG
	 * @Description - This is the generic script for using the stored values during runtime.
	 * @param sValueObjectId
	 *            - this contains the label and value of the data that will be
	 *            stored in the list together with the object id/xpath of the
	 *            element; e.g. "firstname>>Nicanor Garcia;//input[@id='firstname']"
	 * @throws Exception
	 *******************************************************************************/

	public void usestoredvalue(String sValueObjectId) throws Exception {

		
		String string = sValueObjectId;
		String sValue = null;
		String ObjId = null;
		String frame = null;
		String locType = null;
		String temp = null;
		String sKey = null;
		String action = null;
		String s = "\n";
		String actual=null;
		String dataOption=null;
		boolean resultVal = false; 
		// count number of semicolons in the parameter
		int iCnt = StringUtils.countMatches(sValueObjectId, ";");
		System.out.println("Number of delimiter: " + iCnt + "");

		try {
			
			if (string.contains(";")) {
				String[] parts = string.split(";");
				sValue = parts[0];
				temp = parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				String[] parts2 = sValue.split(">>");
				sKey = parts2[0];
				action = parts2[1];
				dataOption=parts2.length>2 ? parts2[2] : null ;
				System.out.println(sValue);
				System.out.println(ObjId);

			} else {

				ObjId = sValueObjectId;

			}
			
			switchTo(sValueObjectId);
			List<WebElement> oList = null;
			// wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjId)));
			// if(dataOption.equals("checked")) {
			// try {
			// oList = driver.findElements(By.xpath(ObjId));
			// resultVal = "true";
			// }catch(Exception e) {
			// resultVal = "false";
			// }
			// sValue = valueList.get(sKey);
			//
			// if (sValue.equals(resultVal)) {
			//
			//
			// ReportResults("PASS",
			// "Checkbox is checked.\nExpected: " + sValue + "\nActual: " + actual,
			// true);
			// }else{
			// oList.get(0).click();
			// ReportResults("PASS",
			// "Checkbox is not checked.\nExpected: " + sValue + "\nActual: " + actual,
			// true);
			// }}else {

			if (dataOption != null && dataOption.equals("optional"))
				oList = editedfindElement(ObjId, locType);
			else
				oList = findElement(ObjId, locType);
			// List<WebElement> oList = driver.findElements(By.xpath(ObjId));
			// }
			if (oList.size() > 0) {

				// scrollToElement(oList.get(0));
				sValue = valueList.get(sKey);
				// sValue = null;
				if (action.equals("enter")) {
					oList.get(0).click();
					oList.get(0).clear();
					oList.get(0).sendKeys(sValue);

					ReportResults("Pass", "Successfully entered " + sValue + " on this object: " + ObjId, true);
				} else if (action.equals("verify")) {
					actual = oList.get(0).getText().length() > 0 ? oList.get(0).getText()
							: oList.get(0).getAttribute("value");
					if (dataOption == null || dataOption.equals("optional")) {
						if (actual == null)
							actual = "";

						if (actual.equals(sValue))
							ReportResults("PASS",
									"Message is displayed as expected.\nExpected: " + sValue + "\nActual: " + actual,
									true);
						else
							ReportResults("FAIL", "Message is not displayed as expected.\nExpected: " + sValue
									+ "\nActual: " + actual, true);
					} else if (dataOption.equals("ignorecase")) {
						if (actual.equalsIgnoreCase(sValue))
							ReportResults("PASS", "Message is displayed as expected when case is ignored.\nExpected: "
									+ sValue + "\nActual: " + actual, true);
						else
							ReportResults("FAIL",
									"Message is not displayed as expected when case is ignored.\nExpected: " + sValue
											+ "\nActual: " + actual,
									true);

					} else
						ReportResults("FAIL", "Please check the data for " + ObjId + "\n" + dataOption, true);
				} else if (action.equals("checked")) {

					JavascriptExecutor exec = (JavascriptExecutor) driver;
					resultVal = (boolean) exec.executeScript("return document.getElementById('" + ObjId + "').checked");
					// if(resultVal==null) {
					// resultVal = "false";
					// }
					if (String.valueOf(resultVal).equals(sValue)) {
						ReportResults("PASS", "No change in the checkbox.\nExpected: " + sValue + "\nActual: " + actual,
								true);
					} else {
						oList.get(0).click();
						ReportResults("PASS", "Change in the checkbox.\nExpected: " + sValue + "\nActual: " + actual,
								true);
					}
				} else if (action.equals("select")) {
					Select select = new Select(oList.get(0));
					select.selectByVisibleText(sValue);
					ReportResults("Pass", "Successfully select dropdown " + sValue + " on this object: " + ObjId, true);
				}

				else {
					ReportResults("FAIL", "Please implement the action you entered in usestoredvalue() : " + action,
							true);
				}

				// // try to change .contains to .equals
				// if (strToFormat.equals(sValue)) {
				// bResult = true;
				// } else {
				// bResult = false;
				// }

			} else {
				ReportResults("FAIL", "Object does not exist", true);
			}

			driver.switchTo().defaultContent();
		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in usestoredvalue(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

	}
	
	/*******************************************************************************
	 * @author NRGJ
	 * @Description - This is the generic script for sending an arrow key down command to the body of the page.
	 * @param sValueObjectId
	 *            - this contains the number of times the command will be repeated;
	 *            e.g. "1"
	 * @throws Exception
	 *******************************************************************************/
	public void arrowKeyDown(String sObjId) throws Exception {
		String sValue = null;
		try {
			if (sObjId.contains(";")) {
				String[] parts = sObjId.split(";");
				sValue = parts[0];

				System.out.println("Press Arrow Key down " + sValue + " time/s");

				WebElement oList = driver.findElement(By.tagName("body"));
				oList.click();

				for (int i = 0; i < Integer.parseInt(sValue); i++) {
					oList.sendKeys(Keys.ARROW_DOWN);
				}

			}

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in arrowKeyDown(): " + e.getMessage(),
					true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

	}
	
	/*******************************************************************************
	 * @author NRGJ
	 * @Description - This is the generic script for sending an arrow key up command to the body of the page.
	 * @param sValueObjectId
	 *            - this contains the number of times the command will be repeated;
	 *            e.g. "1"
	 * @throws Exception
	 *******************************************************************************/
	public void arrowKeyUp(String sObjId) throws Exception {
		String sValue = null;
		try {
			if (sObjId.contains(";")) {
				String[] parts = sObjId.split(";");
				sValue = parts[0];

				System.out.println("Press Arrow Key up " + sValue + " time/s");

				WebElement oList = driver.findElement(By.tagName("body"));
				oList.click();

				for (int i = 0; i < Integer.parseInt(sValue); i++) {
					oList.sendKeys(Keys.ARROW_UP);
				}

			}

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in arrowKeyDown(): " + e.getMessage(),
					true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

	}

	/*******************************************************************************
	 * @author JLQL feat. RJDG
	 * @Description - This is the generic script for choosing an option in a custom
	 *              dropdown field using arrow down
	 * @param sObjId
	  *            - this contains the number of times the command will be repeated;
	 *            e.g. "1"
	 * @throws Exception
	 *******************************************************************************/
	public void arrowKeyDownDropDown(String sObjId) throws Exception {
		String sValue = null;
		String ObjId = null;
		String temp =null;
		String locType = null;
		try {
			if (sObjId.contains(";")) {
				String[] parts = sObjId.split(";");
				sValue = parts[0];
				temp =parts[1];
				String[] parts1 = temp.split(">>");
				locType = parts1[0];
				ObjId = parts1[1];
				

				System.out.println("Arrow Key down " + sValue + " time/s");

				List<WebElement> oList = findElement(ObjId, locType);

				Robot robot = new Robot();
				WebElement element = null;
				if (oList.size() > 0) {
					oList.get(0).sendKeys(Keys.ARROW_DOWN);
					for (int i = 0; i < Integer.parseInt(sValue); i++) {
						 robot.keyPress(KeyEvent.VK_DOWN);
						 Thread.sleep(1000);
						 robot.keyRelease(KeyEvent.VK_DOWN);
					}
					robot.keyPress(KeyEvent.VK_ENTER);
					 Thread.sleep(1000);
					robot.keyRelease(KeyEvent.VK_ENTER);
					ReportResults("PASS", "Successfully chosen a value" + ObjId, true);
				}
				else {
					ReportResults("FAIL", "Failed to choose a value" + ObjId, true);
				}

			}

		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in arrowKeyDown(): " + e.getMessage(),
					true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

	}
	
	/*******************************************************************************
	 * @author JLQL
	 * @Description - This is the generic method for hitting Tab
	 * @param
	 * @throws Exception
	 *******************************************************************************/
	public void hitTab() throws Exception {
		try {
			driver.switchTo().activeElement().sendKeys(Keys.TAB);
		} catch (Exception e) {
			ReportResults("FAIL", "Unexpected error/exception occured in hitTab(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}

	} 
	
	/*******************************************************************************
	 * @author RJDG
	 * @Description - This is the generic method for verifying an item if not
	 *              included in the dropdown
	 * @param sValueObjectId
	 *            - this contains the object id/xpath of the dropdown
	 * @throws Exception
	 *******************************************************************************/

	public boolean verifyCustomizeDropdown(String sValueObjectId) throws Exception {
		String string = sValueObjectId;
		List<String> sValue = new ArrayList<String>();
		List<String> dropDownVal = new ArrayList<String>();
		String ObjId = null;
		String frame = null;
		String locType = null;
//		String temp = null;
		int num = 0;
		String temp0 = null;
		boolean blnCheckValue = true;
		int count = 0;
		// count number of semicolons in the parameter
		int iCnt = StringUtils.countMatches(sValueObjectId, ";");
		System.out.println("Number of delimiter: " + iCnt + "");
		try {
			if (string.contains(";")) {
				String[] parts = string.split(";");
				temp0 = parts[0];
//				temp = parts[1];
//				String[] parts1 = temp.split(">>");
//				locType = parts1[0];
//				ObjId = parts1[1];
				String[] parts2 = temp0.split(">>");
				for (int x = 0; x < parts2.length; x++)
					sValue.add(parts2[x]);

				System.out.println(sValue);
//				System.out.println(ObjId);

			} else {
				ObjId = sValueObjectId;
			}

//			switchTo(sValueObjectId);
			Thread.sleep(3000);
			int y = 1;
			for(int ctr=0; ctr<Integer.parseInt(sValue.get(1)); ctr++){
				y= ctr + y;
				
				WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='select-options popupTargetContainer uiPopupTarget uiMenuList uiMenuList--default uiMenuList--left uiMenuList--short']["+sValue.get(0)+"]//ul/li["+y+"]/a")));
			
				dropDownVal.add(element.getAttribute("title"));
				y = 1;
			}
			for(int ctr2=0; ctr2<Integer.parseInt(sValue.get(2)); ctr2++){
				for(int ctr1=0; ctr1<Integer.parseInt(sValue.get(1)); ctr1++){
					if(sValue.get(ctr2+3).equals(dropDownVal.get(ctr1))){
						ReportResults("PASS","Dropdown verified" + dropDownVal.get(ctr1), true);
						num++;
					}
					
				}
			}
			if(num==Integer.parseInt(sValue.get(2))){
				System.out.println("Successful");
			}
			else
				ReportResults("FAIL",
						"No value matched", true);
			driver.switchTo().defaultContent();

		} catch (Exception e) {
			ReportResults("FAIL",
					"Unexpected error/exception occured in verifyCustomizeDropdown(): " + e.getMessage(), true);
			System.out.println(e.getMessage() + "/n" + e.getStackTrace());
		}
		return blnCheckValue;

	}// verify a value in a dropdown if existing
	
	public void altTab(){
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(0));
		driver.close();
		driver.switchTo().window(tabs2.get(1));
		System.out.println("Switch Tab");
	}
	
}
