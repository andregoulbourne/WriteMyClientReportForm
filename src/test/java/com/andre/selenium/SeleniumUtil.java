package com.andre.selenium;

import com.andre.WriteMyClientReportForm;
import com.andre.exceptions.ChromeDriverMissingException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class SeleniumUtil {

	private static final Logger logger = Logger.getLogger(SeleniumUtil.class);

	private static SeleniumUtil instance;

	protected static WebDriver driver;

	@BeforeEach
	void setupSelenium() throws ChromeDriverMissingException {
		try{
			if(driver == null) {
				startApplication();

				driver = getWebDriverInstance();
				driver.manage().window().maximize();
				driver.get("http://localhost:" + 8081 + "/");
				waitSeconds(5);
			}
			waitSeconds(3);
		} catch(Exception e){
			logger.error("Setting up in selenium util, "+ e.getMessage());
		}
	}

	@AfterEach
	void tearDownSelenium() {
		try{
			//driver.quit();
		} catch(Exception e){
			logger.error("Tearing Down in selenium util, "+ e.getMessage());
		}
	}

	protected void waitSeconds(int seconds) throws InterruptedException {
		long miliSeconds = seconds * 1000L;

		synchronized (this){
			wait(miliSeconds);
		}
	}

	private static WebDriver getWebDriverInstance() throws ChromeDriverMissingException {
		try{
			if (driver == null) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);;
			}
		} catch(Exception e){
			logger.error("An exception occurred while setting chrome web driver , "+ e.getMessage());
			throw new ChromeDriverMissingException();
		}

		return driver;
	}

	private void startApplication() throws InterruptedException {
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