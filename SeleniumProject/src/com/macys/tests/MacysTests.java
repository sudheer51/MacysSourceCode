package com.macys.tests;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.macys.pages.HomePage;
import com.macys.pages.SearchResultsPage;
import com.macys.util.AppLibrary;

public class MacysTests {
	
	AppLibrary appLib;
	WebDriver driver;
	HomePage hPage;
	Logger logger;

	@Parameters({"browserType","url"})
	@BeforeClass
	public void setUp(String browserType,String url)
	{
		
		System.out.println("In Setup");
		logger = Logger.getLogger("MacysTests");
		PropertyConfigurator.configure("log4j.properties");
		
		appLib = new AppLibrary();
		driver = appLib.getDriverInstance(browserType);
		appLib.openBrowser(url);
		logger.info("Browser opened Successfully");
		hPage = new HomePage(driver);
		logger.info("Instantiated HomePage instance");
		
	}
	@Parameters({"searchItem"})
	@Test
	public void verifySearchItemDetails(String searchItem)
	{
		System.out.println("am in test");
		//SearchResultsPage searchPage = new SearchResultsPage();
		
		SearchResultsPage searchPage = hPage.performSearch(searchItem);
		logger.info("Perform the Search using the searchItem -->" + searchItem);
		
		int actual = searchPage.getItemsCount("//a[@class='productThumbnailLink']",searchItem);
		searchPage.clickOnNextPage();
		actual = actual + searchPage.getItemsCount("//a[@class='productThumbnailLink']",searchItem);
		int expected = 80;
		Assert.assertEquals(expected, actual);
	}
}
/*
 * 1. Every page should contain a parameterized constructor
 * 2. Every page while performing an action that goes to next page should return next page object
 */








