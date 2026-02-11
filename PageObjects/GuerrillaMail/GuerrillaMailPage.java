package GuerrillaMail;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Utilities;
import Constant.Constant;

public class GuerrillaMailPage {

	// Locators
	private final By btnInputEmail = By.xpath("//span[@id='inbox-id']");
	private final By btnSetButton = By.xpath("//button[text()='Set']");
	private final By btnDelete = By.xpath("//input[@id='del_button']");

	private final By inputTxtEmail = By.xpath("//span[@id='inbox-id']/input");

	private final By checkboxMail = By.xpath("//input[@type='checkbox' and @name='mid[]']");

	// dynamic email subject
	private final String emailSubjectXpath = "//tbody[@id='email_list']//td[contains(text(),'%s')]";
	
	private final By resetEmailXpath = By.xpath("//tbody[@id='email_list']//span[starts-with(., 'Use this')]");

	private final By activeEmailLink = By.xpath("//div[contains(@class, 'email_page')]//a[@target='_blank']");

	// Elements
	protected WebElement getInputTxtEmail() {
		return Constant.WEBDRIVER.findElement(inputTxtEmail);
	}
	
	protected WebElement getResetEmailXpath() {
		return Constant.WEBDRIVER.findElement(resetEmailXpath);
	}

	// Methods
	public GuerrillaMailPage open() {
		Constant.WEBDRIVER.navigate().to(Constant.GUERRILLAMAIL_URL);
		return this;
	}

	public void cleanAllMail() {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.WAIT_TO_DELETE_MAIL));
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
		try {
			wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(checkboxMail, 2));
			List<WebElement> listCheckbox = Constant.WEBDRIVER.findElements(checkboxMail);
			listCheckbox.forEach(i -> {
				js.executeScript("arguments[0].click();", i);
				System.out.println(i);
			});
			Utilities.click(btnDelete);
		} catch (Exception e) {
			List<WebElement> listCheckbox = Constant.WEBDRIVER.findElements(checkboxMail);
			listCheckbox.forEach(i -> {
				js.executeScript("arguments[0].click();", i);
				System.out.println(i);
			});
			Utilities.click(btnDelete);
		}
	}

	public void setMail(String emailPrefix) {
		Utilities.click(btnInputEmail);
		getInputTxtEmail().clear();
		Utilities.enter(inputTxtEmail, emailPrefix);
		Utilities.click(btnSetButton);
	}

	public void activateAccountByEmail(String subject) {
		String xpath = String.format(emailSubjectXpath, subject);
		Utilities.click(By.xpath(xpath));
		
		Utilities.click(activeEmailLink);
	}
	
	public void resetAccount() {
		Utilities.click(resetEmailXpath);
		Utilities.click(activeEmailLink);
	}

}
