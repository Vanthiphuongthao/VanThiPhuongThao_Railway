package Common;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

	public static boolean isDisplayed(String element) {
		try {
			return Constant.WEBDRIVER.findElement(By.xpath(element)).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

//	public static By waitForClickable(By locator) {
//		return waitForClickable(locator, Constant.TIMEOUT);
//	}

	public static By waitForClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
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
			WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.stalenessOf(element));
		} catch (Exception e) {

		}
	}

	public static void waitForPageFullyLoad() {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'"));
	}
	

	// click
	public static void click(By locator) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(30));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
		js.executeScript("arguments[0].click();", element);
	}

	// enter
	public static void enter(By locator, String valueString) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(30));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.sendKeys(valueString);
	}

	// generate current date and time
	public static String generateCurrentDateAndTime() {
		return new SimpleDateFormat("ddMMyyyyHHmmssSSS").format(new Date());
	}
	
	public static void verifyMessage(String actualMsg, Message expectedMsg, Message errorMessage) {
		Assert.assertEquals(actualMsg, expectedMsg.getMessage(), errorMessage.getMessage());
	}

}
