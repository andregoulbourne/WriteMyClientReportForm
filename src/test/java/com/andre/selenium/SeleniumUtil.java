package com.andre.selenium;

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
	
	private static final String OS_NAME = "os.name";
	
	private static final String CHROME_DRIVER_PATH_LINUX = "./selenium/chromedriver";
	private static final String CHROME_DRIVER_PATH_WINDOWS = "./selenium/chromedriver.exe";
	
	@LocalServerPort
	int randomServerPort;

	protected static WebDriver driver;

	@BeforeEach
	void setup() throws ChromeDriverMissingException {
		driver = getWebDriverInstance();
		driver.manage().window().maximize();
		System.out.println(randomServerPort);
		driver.get("http://localhost:" + randomServerPort + "/");
	}

	@AfterEach
	void tearDown() {
		driver.quit();
	}

	private static WebDriver getWebDriverInstance() throws ChromeDriverMissingException {
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

		return driver;
	}

}