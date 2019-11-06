package testscripts;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.By;



public class AmazonLibrary extends DriverScript {

	// Store method return result
	public static String methodReturnResult = null;

	// Site name
	public static String testSiteName = "Amazon.in";

	// Expected page titles
	public static String appLoginPageTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";


	// Create a browser instance and navigate to the Test Site
	public static String navigateToAppWebsite() throws MalformedURLException, InterruptedException {

		APPLICATION_LOGS.debug("Creating a browser instance and navigating to the test site ...");

		// Disable log messages
		java.util.logging.Logger.getLogger("org.apache.http.impl.client").setLevel(java.util.logging.Level.WARNING);

		if (wbdv == null) {

			try {

				if (CONFIG.getProperty("is_remote").equals("true")) {

					// Generate Remote address
					String remote_address = "http://" + CONFIG.getProperty("remote_ip") + ":4444/wd/hub";
					remote_url = new URL(remote_address);

					if (CONFIG.getProperty("test_browser").contains("Internet Explorer")) {
						dc = DesiredCapabilities.internetExplorer();
						dc.setCapability("silent", true);
					}

					else {
						ProfilesIni allProfiles = new ProfilesIni();
						dc = DesiredCapabilities.firefox();
					}

					wbdv = new RemoteWebDriver(remote_url, dc);
					driver = new EventFiringWebDriver(wbdv);
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				}

				else {

					if (CONFIG.getProperty("test_browser").toLowerCase().contains("internet explorer")
							|| CONFIG.getProperty("test_browser").toLowerCase().contains("ie")) {
						dc = DesiredCapabilities.internetExplorer();
						dc.setCapability("silent", true);
						dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
						wbdv = new InternetExplorerDriver(dc);
						driver = new EventFiringWebDriver(wbdv);
					}

					else if (CONFIG.getProperty("test_browser").toLowerCase().contains("firefox")
							|| CONFIG.getProperty("test_browser").toLowerCase().contains("ff")) {

						ProfilesIni allProfiles = new ProfilesIni();
						FirefoxProfile profile = allProfiles.getProfile("default");
						profile.setAcceptUntrustedCertificates(true);
						profile.setAssumeUntrustedCertificateIssuer(false);
						wbdv = new FirefoxDriver(profile);
						driver = new EventFiringWebDriver(wbdv);

					}

					else if (CONFIG.getProperty("test_browser").toLowerCase().contains("safari")) {

						dc = DesiredCapabilities.safari();

					}

					else if (CONFIG.getProperty("test_browser").toLowerCase().contains("chrome")) {
						// Edit the path to your chrome driver in your system below!
						System.setProperty("chromedriver", "D:\\AllAutomationSetup\\BrowserDrivers\\chromedriver.exe");
						dc = DesiredCapabilities.chrome();
						wbdv = new ChromeDriver(dc);
						driver = new EventFiringWebDriver(wbdv);

					}

				}

			}

			catch (Throwable initBrowserException) {

				APPLICATION_LOGS
				.debug("Error came while creating a browser instance : " + initBrowserException.getMessage());

				return failTest + " : Error came while creating a browser instance : "
				+ initBrowserException.getMessage();

			}

		}

		APPLICATION_LOGS.debug("Created browser instance successfully");

		try {

			// Implicitly wait for 30 seconds for browser to open
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			// Delete all browser cookies
			driver.manage().deleteAllCookies();

			// Navigate to Amazon application
			driver.navigate().to(CONFIG.getProperty("testSiteURL"));

			// Handle certificate error
			if (CONFIG.getProperty("test_browser").contains("Internet Explorer")) {

				if (driver.getTitle().contains(navigationBlockedTitle)) {

					driver.navigate().to("javaScript:document.getElementById('overridelink').click()");

					FunctionLibrary.waitForPageToLoad();

				}
			}

			// Maximize browser window
			APPLICATION_LOGS.debug("Maximizing Browser window...");
			driver.manage().window().maximize();
			APPLICATION_LOGS.debug("Browser window is maximized");

		}

		catch (Throwable navigationError) {

			APPLICATION_LOGS.debug("Error came while navigating to the test site : " + navigationError.getMessage());
			return failTest + " : Error came while navigating to the test site.";
		}

		Thread.sleep(3000L);

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


	//Create a method and print the result of the product description from first page and put into an excel sheet
	public static String printSearchItems() throws MalformedURLException, InterruptedException {
		APPLICATION_LOGS.debug("To print the result of the product description from first page and put into an excel sheet - " );

		try {

			int numberOfProducts = 0;
			String productDescription = null;
			String price = null;

			numberOfProducts = driver.findElements(By.xpath("//span[contains(@cel_widget_id,'SEARCH_RESULTS')]")).size();

			for(int counter = 1; counter<= numberOfProducts+1; counter++)
			{

				productDescription = driver.findElement(By.xpath("((//span[contains(@cel_widget_id,'SEARCH_RESULTS')])["+counter+"]//span[contains(@class,'a-size-base-plus')])[2]")).getText();
				price = driver.findElement(By.xpath("(//span[contains(@cel_widget_id,'SEARCH_RESULTS')])["+counter+"]//span[contains(@class,'a-price-whole')]")).getText();

				resultReader.writetoTheCell("Result",1,counter,productDescription);
				resultReader.writetoTheCell("Result",2,counter,price);
				System.out.println("Product description: "+productDescription+" "+"Price: "+price);

			}
		}

		catch (Throwable navigationError) {
			APPLICATION_LOGS.debug("Error came while printing the product description : " + navigationError.getMessage());
			return failTest + " : Error came while printing the product description";
		}

		APPLICATION_LOGS.debug("Able to print the product descriptions searched from first page - " + testSiteName);
		return "Pass : Able to print the product descriptions searched from first page - " + testSiteName;

	}


	//Create a method and print the Nth item

	public static String printNthItem() throws MalformedURLException, InterruptedException {
		APPLICATION_LOGS.debug("To print the Nth item with price - " );

		try {
			Scanner scan = new Scanner(System.in);

			//Enter the Nth item
			System.out.print("Enter the Nth item you would like to print: ");

			int n = scan.nextInt();
			String NthProductDescription = null;
			String NthPrice = null;


			NthProductDescription = driver.findElement(By.xpath("((//span[contains(@cel_widget_id,'SEARCH_RESULTS')])["+n+"]//span[contains(@class,'a-size-base-plus')])[2]")).getText();
			NthPrice = driver.findElement(By.xpath("(//span[contains(@cel_widget_id,'SEARCH_RESULTS')])["+n+"]//span[contains(@class,'a-price-whole')]")).getText();


			System.out.println("Product description: "+NthProductDescription+" "+"Price: "+NthPrice);
		}

		catch (Throwable navigationError) {
			APPLICATION_LOGS.debug("Error came while printing the Nth item : " + navigationError.getMessage());
			return failTest + " : Error came while printing the Nth item";
		}

		APPLICATION_LOGS.debug("Able to print the Nth product descriptions - " + testSiteName);
		return "Pass : Able to print the Nth product descriptions - " + testSiteName;

	}

}



