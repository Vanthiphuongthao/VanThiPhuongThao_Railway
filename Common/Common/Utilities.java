package Common;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class Utilities {
	public static By waitForClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		return locator;
	}

	public static WebElement waitForVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
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

	public static boolean isDisplayed(By locator) {
		try {
			return Constant.WEBDRIVER.findElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static WebElement getElement(By locator) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

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

	public static void click(By locator) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

		try {
			element.click();
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
			js.executeScript("arguments[0].click();", element);
		}
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

	// close all tab exception tab handle
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

	public static void selectByVisibleText(By locator, String visibleText) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}

	public static String randomDate(int startOffsetDays, int endOffsetDays, String formatDay) {
		LocalDate today = LocalDate.now();
		LocalDate startDate = today.plusDays(startOffsetDays);
		LocalDate endDate = startDate.plusDays(endOffsetDays);
		long randomDays = ThreadLocalRandom.current().nextLong(startDate.toEpochDay(), endDate.toEpochDay() + 1);
		LocalDate randomDate = LocalDate.ofEpochDay(randomDays);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatDay);
		return randomDate.format(formatter);
	}

	// 21/01
//	public static WebElement waitForText(By locator, String text, int timeout) {
//		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
//		return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text))
//				? Constant.WEBDRIVER.findElement(locator)
//				: null;
//	}

	public static WebElement waitForText(By locator, String text, int timeout) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));

		return Constant.WEBDRIVER.findElement(locator);
	}

//	public static void scrollToElement(WebElement webElement) {
//		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
//		js.executeScript("arguments[0].scrollIntoView(true)", webElement);		
//	}
//	
//	public static void scrollToElement(By locator) {
//		WebElement webElement = Utilities.waitForVisible(locator, Constant.TIMEOUT);
//		scrollToElement(webElement);
//	}

}
