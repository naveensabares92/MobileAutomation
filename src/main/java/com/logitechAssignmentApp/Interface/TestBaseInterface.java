package com.logitechAssignmentApp.Interface;

import java.io.IOException;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.ExtentTest;

public interface TestBaseInterface {

	public void launchApp() throws IOException, InterruptedException;
	
	public void closeApp();
	
	public boolean verifyTextByAnyLocator(ExtentTest test, By by, String text);
	
	public boolean verifyTitle(ExtentTest test, String title) throws IOException;
	
	public void waitUntilElementIsPresent(ExtentTest test, int timeToWait, By by);
	
	public void waitUntilElementIsPresentClick(ExtentTest test, int timeToWait, By by);
	
}
