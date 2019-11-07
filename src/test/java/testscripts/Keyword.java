package testscripts;

import java.io.IOException;
import java.sql.SQLException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import testcases.AmazonTest;

public class Keyword {
	public static String methodReturnResult = null;

	// Navigate to the Amazon App, Search for the Wrist Watches 
	public static String navigateToAmazonSearchForWristWatches() throws BiffException,InterruptedException, IOException, RowsExceededException, WriteException, SQLException {
		return AmazonTest.navigateToAmazonSearchForWristWatches();
	}

	//Provide the filters[Display- Analogue, Brand Material- Leather, Brands-Titan, Discounts- 25% or more]
	public static String filterProductsForWristWatches() throws BiffException,InterruptedException, IOException, RowsExceededException, WriteException, SQLException {
		return AmazonTest.filterProductsForWristWatches();
	}

	// Print the product description and the price
	public static String printProductDescriptionAndPrice() throws BiffException,InterruptedException, IOException, RowsExceededException, WriteException, SQLException {
		return AmazonTest.printProductDescriptionAndPrice();
	}

	// Print the Nth product description and the price
	public static String prinNthtProductDescriptionAndPrice() throws BiffException,InterruptedException, IOException, RowsExceededException, WriteException, SQLException {
		return AmazonTest.prinNthtProductDescriptionAndPrice();
	}
}
