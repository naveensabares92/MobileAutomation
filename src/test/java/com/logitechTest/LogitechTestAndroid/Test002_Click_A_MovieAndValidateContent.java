package com.logitechTest.LogitechTestAndroid;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.logitechAssignmentApp.core.TestBase;
import com.logitechAssignmentApp.pageObjects.MoviesDB_HomePage_PgObjects;

public class Test002_Click_A_MovieAndValidateContent extends TestBase {
	private MoviesDB_HomePage_PgObjects homePage_Objects =  PageFactory.initElements(driver,MoviesDB_HomePage_PgObjects.class);
	private String avengersAgeOfUltronMovieData = "Movie Title : Avengers:Age of Ultron \n"
												+ "Release Year : 2015 \n"
												+ "Rated : PG-13 \n"
												+ "Released on : 01 May 2015 \n"
												+ "Total Runtime : 141 min \n"
												+ "Genre : Action, Adventure, Sci-Fi \n"
												+ "Director : Joss Whedon \n"
												+ "Writer : Joss Whedon, Stan Lee (based on the Marvel comics by), Jack Kirby (based on the Marvel comics by), Joe Simon (character created by: Captain America), Jack Kirby (character created by: Captain America), Jim Starlin (character created by: Thanos) \n"
												+ "Actors : Robert Downey Jr., Chris Hemsworth, Mark Ruffalo, Chris Evans \n"
												+ "Plot : When Tony Stark and Bruce Banner try to jump-start a dormant peacekeeping program called Ultron, things go horribly wrong and it's up to Earth's mightiest heroes to stop the villainous Ultron from enacting his terrible plan. \n"
												+ "Language : English, Korean \n"
												+ "Country : USA \n"
												+ "Awards : 7 wins & 45 nominations. ";
	
	@Test(groups={"Logitech Assignment App Test", "Smoke Test"}, 
		  description = "Android Test 001 - Launch the Application, click Avengers-Age Of Ultron "
		  		+ "movie and validate the content")
	public void AndroidTest001() throws InterruptedException, IOException
	{
		test = report.startTest("Android Test 001 - Launch the Application, click Avengers-Age Of Ultron "
		  		+ "movie And validate the content");
		System.err.println("Application has been launched successfully, Proceeding to Title verification");
		waitUntilElementIsPresentClick(test, 5000, homePage_Objects.firstMovieDisplayed);
		waitUntilElementIsPresent(test, 5000, homePage_Objects.imageOfMovie);
		verifyTextByAnyLocator(test, homePage_Objects.movieData, avengersAgeOfUltronMovieData);
	}

}
