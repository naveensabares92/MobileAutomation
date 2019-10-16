package com.logitechAssignmentApp.core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.logitechAssignmentApp.Interface.TestBaseInterface;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class TestBase extends SetCapabilities implements TestBaseInterface{

	public AndroidDriver<MobileElement> driver = null;
	protected static ExtentTest test;
	protected static ExtentReports report;
	
	@BeforeClass
	public void initializeReports()
	{
		report = new ExtentReports(System.getProperty("user.dir")+"\\Reports\\ExtentReportResults_" + dateTimeFormatter() + ".html");
	}
	
	/*
	 * This method launches the application based on the config.properties
	 * @see com.logitechAssignmentApp.Interface.TestBaseInterface#launchApp()
	 */
	@BeforeTest
	public void launchApp() throws IOException, InterruptedException
	{
	setFinalParameters();	
	DesiredCapabilities capabilities = new DesiredCapabilities();
	try 
		{
		capabilities.setCapability("platformName", super.platformName);
		capabilities.setCapability("platformVersion", super.platformVersion); 
		capabilities.setCapability("deviceName", super.deviceName);
		capabilities.setCapability("appPackage", super.appPackage);
		capabilities.setCapability("appActivity",super.appActivity);
		capabilities.setCapability("noReset","true");
		driver = new AndroidDriver<MobileElement>(new URL(super.envURL), capabilities);
		Thread.sleep(5000);
		} 
	catch (MalformedURLException e) 
		{	
		// TODO Auto-generated catch block
		System.err.println("Appium Server URL Provided is incorrect");
		e.printStackTrace();
		}
	catch(Exception e){
		}
	}
	
	/*
	 * This method closes the application under test
	 * @see com.logitechAssignmentApp.Interface.TestBaseInterface#closeApp()
	 */
	
	@AfterTest
	public void closeApp()
	{
		driver.quit();
	}
	
	@AfterClass
	public void endReport(){
		report.endTest(test);
		report.flush();

	}
	
	
	public boolean verifyTitle(ExtentTest test, String title) throws IOException 
	{
		// TODO Auto-generated method stub
		try {		
			if(driver.getTitle().equals(title))
			{
			System.out.println("The entered "+ title +" is verified");
			test.log(LogStatus.PASS,test.addScreenCapture(captureSuccessScreenshot(driver, test)), "verifyTitle step passed" + " -" + title);
			return true;
			} 
			else
			System.out.println("The entered Title "+ title +" does not match");
			test.log(LogStatus.FAIL,test.addScreenCapture(captureFailureScreenshot(driver, test)), "verifyTitle step failed" + " -" + title);
			} catch (Exception e) {
				System.err.println("Invalid Title in verifyTitle page");
				test.log(LogStatus.ERROR,test.addScreenCapture(captureFailureScreenshot(driver, test)), "verifyTitle step failed" + " -" + title);
				throw new RuntimeException();
				//e.printStackTrace();
			}
			return false;
	}
	
	public boolean verifyTextByAnyLocator(ExtentTest test, By by, String text) 
	{
	    try {
	    	System.err.println(driver.findElement(by).getText());
	    	System.err.println(text);
	    	System.err.println(driver.findElement(by).getText().length());
	    	System.err.println(text.length());
			if(driver.findElement(by).getText().trim().equalsIgnoreCase(text.trim())){
				System.err.println("Verification success; Texts match");
				test.log(LogStatus.PASS,test.addScreenCapture(captureSuccessScreenshot(driver, test)) ,"The Expected and Actual text matches");
				return true;
			}
			else
			{
				test.log(LogStatus.FAIL,test.addScreenCapture(captureFailureScreenshot(driver, test)), "The Expected and Actual text does not match");
				System.err.println("Mismatch in Texts compared");
				return false;
			}
		} catch (NoSuchElementException e) {
			System.err.println("The entered element is not found- verifyTextBy (by) " + by);
			test.log(LogStatus.ERROR, "Unable to locate element");
			throw new RuntimeException();
			//e.printStackTrace();
		}catch (Exception e){
			System.err.println("Generic Error");
			return false;
		}
	}
	
	
	
	public void waitUntilElementIsPresent(ExtentTest test, int timeToWait, By by){
		WebDriverWait wait=new WebDriverWait(driver, timeToWait);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	public void waitUntilElementIsPresentClick(ExtentTest test, int timeToWait, By by){
		WebDriverWait wait=new WebDriverWait(driver, timeToWait);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		driver.findElement(by).click();
	}
	
	
	public static String captureFailureScreenshot(WebDriver driver, ExtentTest test) throws IOException {
	File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	File Dest = new File(System.getProperty("user.dir")+"\\Screenshots\\Failure\\" + test.getClass()+"_" +System.currentTimeMillis()
	+ ".png");
	String errflpath = Dest.getAbsolutePath();
	FileUtils.copyFile(scrFile, Dest);
	return errflpath;
	}
	
	public static String captureSuccessScreenshot(WebDriver driver, ExtentTest test) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File(System.getProperty("user.dir")+"\\Screenshots\\Success\\" + test.getClass()+"_" +System.currentTimeMillis()
		+ ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
		}

	public String dateTimeFormatter()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
		Date date = new Date();
		System.out.println(formatter.format(date));
		return formatter.format(date);
	}
}
