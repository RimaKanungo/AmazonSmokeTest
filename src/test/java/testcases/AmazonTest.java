package testcases;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.openqa.selenium.By;

import testscripts.AmazonLibrary;
import testscripts.DriverScript;
import testscripts.FunctionLibrary;
import util.TestUtil;


public class AmazonTest extends DriverScript {
	/*
	 * Name of the WebElements present on the WebPage
	 */

	/* .............. Locators for the test ................. */
	public static By locatorSearchTextBox = By.id("twotabsearchtextbox");
	public static By locatorSearchButton = By.className("nav-input");
	public static By locatorBrandTitan = By.xpath("//li[@id='p_89/Titan']//i[@class='a-icon a-icon-checkbox']");
	public static By locatorDisplayAnalog = By.xpath("//li[@id='p_n_feature_seven_browse-bin/1480900031']//i[@class='a-icon a-icon-checkbox']");
	public static By locatorBrandLeather = By.xpath("//li[@id='p_n_material_browse/1480907031']//i[@class='a-icon a-icon-checkbox']");
	public static By locatorDiscountFilter = By.xpath("//span[contains(text(),'25% Off or more')]");

	// Expected page title
	public static String appLoginPageTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";

	// Site name
	public static String testSiteName = "Amazon.in";

	//Expected filter title after filters provided
	public static String filterTitle = "Titan / Analogue / Leather / 25% Off or more";

	//Expected product Description
	public static String expectedProductDescription = "Analog";

	// Navigate to the Amazon App, Search for the Wrist Watches, provide the filters[Display- Analogue, Brand Material- Leather, Brands-Titan, Discounts- 25% or more]
	public static String navigateToAmazonSearchForWristWatches() throws SQLException, InterruptedException, IOException {

		APPLICATION_LOGS.debug("Executing test case : Navigate To Amazon, Search For Wrist Watches and provide the filters");

		// Navigating to the Amaozon App
		methodReturnResult = AmazonLibrary.navigateToAppWebsite();
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		// Waiting for the Element to load
		FunctionLibrary.waitForElementToLoad(locatorSearchTextBox);

		// Inputing product name
		methodReturnResult = FunctionLibrary.clearAndInput(locatorSearchTextBox, "Search Field",
				"Wrist Watches");
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		// Clicking on search button
		methodReturnResult = FunctionLibrary.clickAndWait(locatorSearchButton, "Search Button");
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		// Waiting for the elements to load
		FunctionLibrary.waitForElementToLoad(locatorSearchTextBox);

		// Verify Login page appears
		expectedTitle = appLoginPageTitle;
		methodReturnResult = FunctionLibrary.assertTitle(expectedTitle);
		if (methodReturnResult.contains(failTest)) {

			// Log result
			APPLICATION_LOGS.debug("Not navigated to the test site - " + testSiteName);
			System.err.println("Not navigated to the test site - " + testSiteName);
			return methodReturnResult;

		}

		APPLICATION_LOGS.debug("Navigated to the test site - " + testSiteName);
		return "Pass : Navigated to the test site - " + testSiteName;

	}


	// Provide the filters[Display- Analogue, Brand Material- Leather, Brands-Titan, Discounts- 25% or more]
	public static String filterProductsForWristWatches() throws SQLException, InterruptedException, IOException {

		APPLICATION_LOGS.debug("Executing test case : Navigate To Amazon, Search For Wrist Watches and provide the filters");	

		/*Selecting some of the filters provided:
		 * Selecting the checkbox for Brands- Titan
		 */
		methodReturnResult = FunctionLibrary.checkCheckBox(locatorBrandTitan, "Brand-Titan");
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		//Waiting for the page to load
		FunctionLibrary.waitForPageToLoad();


		// Selecting the checkbox for Display- Analogue
		methodReturnResult = FunctionLibrary.checkCheckBox(locatorDisplayAnalog, "Display-Analog");
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		//Waiting for the page to load
		FunctionLibrary.waitForPageToLoad();

		// Selecting the checkbox for Brand Material- Leather
		methodReturnResult = FunctionLibrary.checkCheckBox(locatorBrandLeather, "Brand-Leather");
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		//Waiting for the page to load
		FunctionLibrary.waitForPageToLoad();

		// Clicking on Discount - 25% Off or more
		methodReturnResult = FunctionLibrary.clickAndWait(locatorDiscountFilter, "Discount");
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		//Waiting for the page to load
		FunctionLibrary.waitForPageToLoad();

		// Verify the filters provided
		expectedTitle = filterTitle;
		methodReturnResult = FunctionLibrary.assertTitle(expectedTitle);
		if (methodReturnResult.contains(failTest)) {

			// Log result
			APPLICATION_LOGS.debug("Correct filters not provided to the test site - " + testSiteName);
			System.err.println("Correct filters not provided to the test site - " + testSiteName);
			return methodReturnResult;

		}

		APPLICATION_LOGS.debug("Correct filters are provided to the test site - " + testSiteName);
		return "Pass : Correct filters are provided to the test site - " + testSiteName;

	}


	// Printing the result of the product description from first page and putting the descriptions into an excel sheet.
	public static String printProductDescriptionAndPrice() throws SQLException, InterruptedException, IOException {
		APPLICATION_LOGS.debug("Executing test case : Print the product description and the price");

		methodReturnResult = AmazonLibrary.printSearchItems();
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		// Verify the product description
		methodReturnResult = FunctionLibrary.verifyTextPresent(expectedProductDescription);
		if (methodReturnResult.contains(failTest)) {

			// Log result
			APPLICATION_LOGS.debug("Analog Watches are not selected from the test site - " + testSiteName);
			System.err.println("Analog Watches are not selected from the test site - " + testSiteName);
			return methodReturnResult;

		}

		APPLICATION_LOGS.debug("Analog Watches are selected from the test site - " + testSiteName);
		return "Pass : Analog Watches are selected from the test site - " + testSiteName;

	}


	// Printing the Nth Product among the searched item
	public static String prinNthtProductDescriptionAndPrice() throws SQLException, InterruptedException, IOException {
		APPLICATION_LOGS.debug(
				"Print the Nth product description and the price"); 
		methodReturnResult = AmazonLibrary.printNthItem();
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}

		// Close Driver
		FunctionLibrary.closeDriver();

		// Verify the Nth product description
		methodReturnResult = FunctionLibrary.verifyTextPresent(expectedProductDescription);
		if (methodReturnResult.contains(failTest)) {

			// Log result
			APPLICATION_LOGS.debug("Analog Watches are not selected from the test site - " + testSiteName);
			System.err.println("Analog Watches are not selected from the test site - " + testSiteName);
			return methodReturnResult;

		}

		APPLICATION_LOGS.debug("Analog Watches are selected from the test site - " + testSiteName);
		return "Pass : Analog Watches are selected from the test site - " + testSiteName;

	}

}
