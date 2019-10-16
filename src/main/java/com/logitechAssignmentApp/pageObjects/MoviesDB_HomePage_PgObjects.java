package com.logitechAssignmentApp.pageObjects;

import org.openqa.selenium.By;

public class MoviesDB_HomePage_PgObjects {
	
	public By moviesDBTitle = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView");
	
	public By firstMovieDisplayed = By.xpath("(//android.support.v7.widget.RecyclerView//android.widget.FrameLayout//android.widget.LinearLayout/android.widget.TextView)[1]");

	public By imageOfMovie = By.id("com.logitech.assignment:id/toolbarImage");
	
	public By movieData = By.id("com.logitech.assignment:id/movie_data");
}

