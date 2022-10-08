package com.andre.selenium;

import org.apache.log4j.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

import com.andre.exceptions.ChromeDriverMissingException;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SeleniumUtil {
	
	private static Logger logger = Logger.getLogger(SeleniumUtil.class);

	private static final String OS_NAME = "os.name";
	
	private static final String CHROME_DRIVER_PATH_LINUX = "./selenium/chromedriver";
	private static final String CHROME_DRIVER_PATH_WINDOWS = "./selenium/chromedriver.exe";
	
	@LocalServerPort
	int randomServerPort;

	protected static WebDriver driver;

	@BeforeEach
	void setup() throws ChromeDriverMissingException {
		try{
			driver = getWebDriverInstance();
			driver.manage().window().maximize();
			System.out.println(randomServerPort);
			driver.get("http://localhost:" + randomServerPort + "/");

		} catch(Exception e){
			logger.error("Setting up in selenium util, "+ e.getMessage());
		}
	}

	@AfterEach
	void tearDown() {
		try{
			driver.quit();
		} catch(Exception e){
			logger.error("Tearing Down in selenium util, "+ e.getMessage());
		}
	}

	private static WebDriver getWebDriverInstance() throws ChromeDriverMissingException {
		try{
			if (driver == null) {
				String os = System.getProperty(OS_NAME);
				if(os.equals("Linux")) {
					System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH_LINUX);
				}else if(os.contains("Windows")) {
					System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH_WINDOWS);
				}else {
					throw new ChromeDriverMissingException();
				}
				
				driver = new ChromeDriver();
			}
		} catch(Exception e){
			logger.error("An execption occurred while setting chrome web driver , "+ e.getMessage());
		}

		return driver;
	}

}