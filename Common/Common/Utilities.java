package Common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.lang.annotation.ElementType;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Constant.Constant;
import Enums.Message;

public class Utilities {

	// Generate random string
	public static String generateRandomString(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuilder result = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			result.append(characters.charAt(random.nextInt(characters.length())));
		}
		return result.toString();
	}

	public static boolean isDisplayed(By locator) {
		try {
			return Constant.WEBDRIVER.findElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static WebElement getElement(By locator) {
		return Constant.WEBDRIVER.findElement(locator);
	}

//	public static By waitForClickable(By locator) {
//		return waitForClickable(locator, Constant.TIMEOUT);
//	}

	public static By waitForClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		return locator;
	}

//	public static By waitForVisible(By locator) {
//		return waitForVisible(locator, Constant.TIMEOUT);
//	}

	public static By waitForVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return locator;
	}

	public static void waitUntilStale(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));
			wait.until(ExpectedConditions.stalenessOf(element));
		} catch (Exception e) {

		}
	}

	public static void waitForPageFullyLoad() {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'"));
	}

	public static void click(By locator) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.WAIT_TO_DELETE_MAIL));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
		js.executeScript("arguments[0].click();", element);
	}

	public static void enter(By locator, String valueString) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.sendKeys(valueString);
	}

	public static String getText(By locator) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element.getText().trim();
	}

	// close all tab exception
	public static void closeAllTabExceptHandle(String keepHandle) {
		for (String handle : Constant.WEBDRIVER.getWindowHandles()) {
			Constant.WEBDRIVER.switchTo().window(handle);
			if (!Constant.WEBDRIVER.getTitle().contains(keepHandle)) {
				Constant.WEBDRIVER.close();
			}
		}
		Constant.WEBDRIVER.switchTo().window(Constant.WEBDRIVER.getWindowHandle());
	}

	// generate current date and time
	public static String generateCurrentDateAndTime() {
		return new SimpleDateFormat("ddMMyyyyHHmmssSSS").format(new Date());
	}

	public static void openUrlInNewTab(String url) {
		WebDriver driver = Constant.WEBDRIVER;

		String currentHandle = driver.getWindowHandle();

		((JavascriptExecutor) driver).executeScript("window.open(arguments[0], '_blank');", url);

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(currentHandle)) {
				driver.switchTo().window(handle);
				break;
			}
		}
	}

	public static String getDateAfterDays(int days) {
		LocalDate targetDate = LocalDate.now().plusDays(days);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
		return targetDate.format(formatter);
	}

	public static String getDateAfterDaysFromSelected(By dropdownLocator, int days) {

		// 1. set selected date from UI
		WebElement element = Constant.WEBDRIVER.findElement(dropdownLocator);
		Select select = new Select(element);
		String selectedDateText = select.getFirstSelectedOption().getText();

		// 2. set date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
		LocalDate selectedDate = LocalDate.parse(selectedDateText, formatter);
		LocalDate targetDate = selectedDate.plusDays(days);

		// 4. return formatted date
		return targetDate.format(formatter);
	}

	public static void selectByVisibleText(By locator, String visibleText) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));

		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}

	// 02/10/2026
//	public static String getCellValueByHeader(String headerName) {
//
//		String dynamicXpath = String.format("//td[count(//th[normalize-space()='%s']/preceding-sibling::th)+1]",
//				headerName);
//
//		return Constant.WEBDRIVER.findElement(By.xpath(dynamicXpath)).getText();
//	}

	public static String getCellValueByHeader(String headerName, int rowIndex) {
		String dynamicXpath = String.format(
				"(//tr[%d]/td[count(//th[normalize-space()='%s']/preceding-sibling::th)+1])", rowIndex, headerName);

		return Constant.WEBDRIVER.findElement(By.xpath(dynamicXpath)).getText();
	}



}
