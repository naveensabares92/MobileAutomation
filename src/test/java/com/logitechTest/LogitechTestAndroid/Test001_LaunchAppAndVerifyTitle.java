package com.logitechTest.LogitechTestAndroid;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.logitechAssignmentApp.core.TestBase;
import com.logitechAssignmentApp.pageObjects.MoviesDB_HomePage_PgObjects;


public class Test001_LaunchAppAndVerifyTitle extends TestBase{
	private MoviesDB_HomePage_PgObjects homePage_Objects =  PageFactory.initElements(driver,MoviesDB_HomePage_PgObjects.class);
	
	@Test(groups={"Smoke Test"}, description = "Smoke Test 001 - Launch the Application, verify Title and Quit")
	public void SmokeTest_001() throws InterruptedException, IOException
	{
		test = report.startTest("Smoke Test 001 - Launch the Application, verify Title and Quit");
		System.err.println("Application has been launched successfully, Proceeding to Title verification");
		waitUntilElementIsPresent(test, 5000, homePage_Objects.moviesDBTitle);
		verifyTextByAnyLocator(test, homePage_Objects.moviesDBTitle, "Movies DB");
	}
	
}
