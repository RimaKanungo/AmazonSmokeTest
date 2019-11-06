package testscripts;

import java.io.IOException;
import java.sql.SQLException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import testcases.AmazonTest;

public class Keyword {
    public static String methodReturnResult = null;

 // Navigate to the Amazon App, Search for the Wrist Watches, provide the filters[Display- Analogue, Brand Material- Leather, Brands-Titan, Discounts- 25% or more]
    public static String navigateToAmazonSearchForWristWatches() throws BiffException,InterruptedException, IOException, RowsExceededException, WriteException, SQLException {
	return AmazonTest.navigateToAmazonSearchForWristWatches();
    }
}
