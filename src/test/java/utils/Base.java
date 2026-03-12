package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Base {

	Properties prop;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static WebDriver driver;

	// launch browser
	public  void launchBrowser() {
		prop = PropertyReader.readProperty();
		String browserName = prop.getProperty("BROWSER");
		String url = prop.getProperty("URL");

		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_leak_detection", false);

		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("firefox")) {
//			FirefoxOptions option = new FirefoxOptions();
//			option.setExperimentalOption("prefs", prefs);
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			options.setExperimentalOption("prefs", prefs);
			driver = new EdgeDriver();
		} else {
			System.out.println("Invalid browser name, please enter valid browser name.");
		}
		driver.manage().window().maximize();
		driver.get(url);
		
	}

	@BeforeSuite
	public void setupReport() {
		ExtentSparkReporter spark = new ExtentSparkReporter("Reports/Report.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
	}

	@AfterMethod
	public void captureResult(ITestResult result) {
	    if (result.getStatus() == ITestResult.FAILURE) {
	        test.fail(result.getThrowable());
	    } else if (result.getStatus() == ITestResult.SUCCESS) {
	        test.pass("Test executed successfully");
	    }
	}
	
	@AfterSuite
	public void endReport() {
		extent.flush();
	}
	
	public void quitBrowser() {
		driver.quit();
	}
	
}
