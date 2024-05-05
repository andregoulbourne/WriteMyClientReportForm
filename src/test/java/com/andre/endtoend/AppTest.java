package com.andre.endtoend;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.andre.selenium.SeleniumUtil;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AppTest extends SeleniumUtil {

	@Test
	@Order(1)
	void testUrl_getPage() throws InterruptedException {
		assertEquals("WriteClientReport",driver.getTitle());
	}

	@Test
	@Order(2)
	void testgetElementOnPage(){
		var generateCommentButtonText = driver.findElement(By.xpath("//button[contains(text(), 'Generate')]")).getText();

		assertEquals("Generate Comments", generateCommentButtonText);
	}


	@Test
	@Order(3)
	void testinitializeNewSummary() throws InterruptedException {
		var numberOfEntries = driver.findElements(By.xpath("//div[@class='ag-center-cols-container']//div")).size();

		driver.findElement(By.xpath("//button[text()='Initialize a Summary']")).click();

		waitSeconds(3);

		closeAlert();

		var numberOfEntriesAfterIntiliazingANewEntry = driver.findElements(By.xpath("//div[@class='ag-center-cols-container']//div")).size();

		assertTrue(numberOfEntries < numberOfEntriesAfterIntiliazingANewEntry);
	}

	@Test
	@Order(4)
	void testUpdateIntializedSummary() throws InterruptedException {
		var student = "Jessica";
		var status = "2";
		var covered = "linear equations, the general equation of a line and plotting linear equations";
		var recomendation = "practice more problems";
		var gender = "she";

		getFieldForAttribute("student").sendKeys(student);
		getFieldForAttribute("student").sendKeys(Keys.SHIFT, Keys.TAB);
		waitSeconds(1);
		closeAlert();
		getFieldForAttribute("status").sendKeys(status);
		getFieldForAttribute("status").sendKeys(Keys.SHIFT, Keys.TAB);
		waitSeconds(1);
		closeAlert();
		getFieldForAttribute("coveredValue").sendKeys(covered);
		getFieldForAttribute("coveredValue").sendKeys(Keys.SHIFT, Keys.TAB);
		waitSeconds(1);
		closeAlert();
		getFieldForAttribute("recomendation").sendKeys(recomendation);
		getFieldForAttribute("recomendation").sendKeys(Keys.SHIFT, Keys.TAB);
		waitSeconds(1);
		closeAlert();
		getFieldForAttribute("gender").sendKeys(gender);
		clickTheFirstCellOfLatestEntry();
		waitSeconds(1);
		closeAlert();


		assertEquals(student, getFieldForAttribute("student").getText());
		assertEquals(status, getFieldForAttribute("status").getText());
		assertEquals(covered, getFieldForAttribute("coveredValue").getText());
		assertEquals(recomendation, getFieldForAttribute("recomendation").getText());
		assertEquals(gender, getFieldForAttribute("gender").getText());

	}


	private WebElement getLastEntryRecentlyInserted(){
		var allEntries = driver.findElements(By.xpath("//div[@class='ag-center-cols-container']/child::node()"));

		return allEntries.get(allEntries.size()-1);
	}

	private WebElement getFieldForAttribute(String attribute) {
		var xpath = String.format(".//div[@col-id='%s']", attribute);
		return getLastEntryRecentlyInserted().findElement(By.xpath(xpath));
	}

	private void clickTheFirstCellOfLatestEntry(){
		getLastEntryRecentlyInserted().findElement(By.xpath("./child::div[1]//span[@class='ag-cell-value']")).click();
	}

	@Test
	@Order(5)
	void testGenerateComment() throws InterruptedException {
		getLastEntryRecentlyInserted().findElement(By.xpath(".//input[@ref='eInput']")).click();
		waitSeconds(1);
		driver.findElement(By.xpath("//button[contains(text(), 'Generate')]")).click();
		waitSeconds(2);
		// unselecting
		getLastEntryRecentlyInserted().findElement(By.xpath(".//input[@ref='eInput']")).click();

		var comment = "On a scale from 1-3, Jessica confidence level with the material was at a 2 at the beginning of the session. In the session we worked on finding linear equations, the general equation of a line and plotting linear equations. After the session Jessica said she was feeling more confident with the material then prior. Recommendations: practice more problems.";

		assertEquals(comment, driver.findElement(By.xpath("//p[contains(text(), 'On a scale from 1-3, Jessica confidence level with the material was at a 2 at the beginning of the session')]")).getText());
	}

	@Test
	@Order(6)
	void testDeleteInsertedEntry() throws InterruptedException {
		var id = getLastEntryRecentlyInserted().findElement(By.xpath("./child::div[1]//span[@class='ag-cell-value']")).getText();

		getLastEntryRecentlyInserted().findElement(By.xpath(".//input[@ref='eInput']")).click();
		waitSeconds(1);
		driver.findElement(By.xpath("//button[contains(text(), 'Delete')]")).click();
		waitSeconds(2);

		closeAlert();

		assertNotEquals(id, getLastEntryRecentlyInserted().findElement(By.xpath("./child::div[1]//span[@class='ag-cell-value']")).getText());
	}

}
