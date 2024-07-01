package com.andre.selenium;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.andre.WriteMyClientReportForm;
import com.andre.exceptions.ChromeDriverMissingException;

public abstract class SeleniumUtil {

	private static final Logger logger = Logger.getLogger(SeleniumUtil.class);
	
	private static final String LOCAL = "local";
	
	
	private static final String OS_NAME = "os.name";

	private static final String CHROME_DRIVER_PATH_LINUX = "./selenium/chromedriver";
	private static final String CHROME_DRIVER_PATH_WINDOWS = "./selenium/chromedriver.exe";
	
	//URL to my locally installed selenium grid
	private static final String SELENIUM_GRID_URL = "http://localhost:4444/";
	
	
	protected static WebDriver driver;
	
	static {
		try{
			if(driver == null) {
				startApplication();

				driver = getWebDriverInstance();
				driver.get("http://localhost:" + 8081 + "/");
				waitSeconds(5);
				
				Runtime.getRuntime().addShutdownHook(new Thread(SeleniumUtil::tearDownSelenium));
			}
		} catch(Exception e){
			logger.error("Setting up in selenium util, ", e);
		}
	}

	@BeforeEach
	void setupSelenium() throws InterruptedException {
		waitSeconds(3);
	}

	private static void tearDownSelenium() {
		try{
			// clean up files from the download directory
			driver.quit();
		} catch(Exception e){
			logger.error("Tearing Down in selenium util, "+ e.getMessage());
		}
	}

	protected static void waitSeconds(int seconds) throws InterruptedException {
		long miliSeconds = seconds * 1000L;
		Thread.sleep(miliSeconds);
	}

	private static WebDriver getWebDriverInstance() throws ChromeDriverMissingException {
		try{
			if (driver == null) {
				ChromeOptions options = getChromeOptions();
				if(isLocalSelenium()) {
					String os = System.getProperty(OS_NAME);
					if(os.equals("Linux")) {
						System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH_LINUX);
					}else if(os.contains("Windows")) {
						System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH_WINDOWS);
					}else {
						throw new ChromeDriverMissingException();
					}
					
					
					driver = new ChromeDriver(options);
				} else {
					driver = new RemoteWebDriver(new URL(SELENIUM_GRID_URL), options);
					((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
				}
			
				
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			}
		} catch(Exception e){
			logger.error("An exception occurred while setting chrome web driver , ", e);
			throw new ChromeDriverMissingException();
		}

		return driver;
	}
	
	private static boolean isLocalSelenium() {
		return StringUtils.equals(System.getProperty(LOCAL), "Y");
	}
	
	private static ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		String downloadDir = "./src/test/resources/Selenium/downloads";
		
		var prefs = new HashMap<String, Object>();
		
		if(isLocalSelenium()) {
			prefs.put("download.default_directory", new File(downloadDir).getAbsolutePath());
			options.setExperimentalOption("prefs", prefs);
		}
		options.setAcceptInsecureCerts(true);
		options.addArguments("--start-maximized");
		//se downloadsEnabled seem to break selenium grid if set when using a stand alone grid
		//options.setCapability("se:downloadsEnabled", true);
		options.setCapability("se:name", "writeMyClientReportFormApp");
		return options;
	}

	private static void startApplication() throws InterruptedException {
		String[] args = {"-Dserver.port=8081", "-DskipTests"};
		WriteMyClientReportForm.main(args);
		waitSeconds(5);
	}

	protected void closeAlert() throws InterruptedException {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			logger.info("Alert data: " + alertText);
			alert.accept();
			waitSeconds(2);
		} catch(NoAlertPresentException e){
			logger.error("No such alert exist");
		}
	}
}