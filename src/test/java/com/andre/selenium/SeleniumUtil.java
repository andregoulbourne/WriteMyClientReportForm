package com.andre.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SeleniumUtil {
	
	private static final String CHROME_DRIVER_PATH = "./selenium/chromedriver";

	@LocalServerPort
	int randomServerPort;

	protected static WebDriver driver;

	@BeforeEach
	void setup() {
		driver = getWebDriverInstance();
		driver.manage().window().maximize();
		System.out.println(randomServerPort);
		driver.get("http://localhost:" + randomServerPort + "/");
	}

	@AfterEach
	void tearDown() {
		driver.quit();
	}

	private static WebDriver getWebDriverInstance() {
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
			driver = new ChromeDriver();
		}

		return driver;
	}

}