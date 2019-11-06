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


	// Navigate to the Amazon App, Search for the Wrist Watches, provide the filters[Display- Analogue, Brand Material- Leather, Brands-Titan, Discounts- 25% or more]

	public static String navigateToAmazonSearchForWristWatches() throws SQLException, InterruptedException, IOException {

		APPLICATION_LOGS.debug(
				"Executing test case : Navigate To Amazon, Search For Wrist Watches and provide the filters");

		// Navigating to the App
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

		// Printing the result of the product description from first page and putting the descriptions into an excel sheet.
		methodReturnResult = AmazonLibrary.printSearchItems();
		if (methodReturnResult.contains(failTest)) {
			return methodReturnResult;
		}
		Thread.sleep(5000);

		// Close Driver
		FunctionLibrary.closeDriver();

		return "Fail: Not able to Search items and print them";

	}
}