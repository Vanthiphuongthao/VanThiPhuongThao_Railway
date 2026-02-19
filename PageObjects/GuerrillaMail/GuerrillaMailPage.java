package GuerrillaMail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class GuerrillaMailPage {

	// Locators
	private final By btnInputEmail = By.xpath("//span[@id='inbox-id']");
	private final By btnSetButton = By.xpath("//button[text()='Set']");

	private final By inputTxtEmail = By.xpath("//span[@id='inbox-id']/input");

	private final String emailSubject = "//tbody[@id='email_list']//td[contains(text(),'%s')]";
	
	private final By resetEmail = By.xpath("//tbody[@id='email_list']//span[starts-with(., 'Use this')]");
	
	private final By activeEmailLink = By.xpath("//div[contains(@class, 'email_page')]//a[@target='_blank']");

	// Elements
	protected WebElement getInputTxtEmail() {
		return Constant.WEBDRIVER.findElement(inputTxtEmail);
	}

	protected WebElement getResetEmailXpath() {
		return Constant.WEBDRIVER.findElement(resetEmail);
	}

	// Methods
	public GuerrillaMailPage open() {
		Constant.WEBDRIVER.navigate().to(Constant.GUERRILLAMAIL_URL);
		return this;
	}

	public void setMail(String emailPrefix) {
		Utilities.click(btnInputEmail);
		getInputTxtEmail().clear();
		Utilities.enter(inputTxtEmail, emailPrefix);
		Utilities.click(btnSetButton);
	}

	public void activateAccount(String subject) {
		String xpath = String.format(emailSubject, subject);
		Utilities.click(By.xpath(xpath));

		Utilities.click(activeEmailLink);
	}

	public void resetAccount() {
		Utilities.click(resetEmail);
		Utilities.click(activeEmailLink);
	}

	public void activateAccountByEmail(String emailPrefix, String subject, String mainTab) {

		// 1. set mail
		Utilities.click(btnInputEmail);
		getInputTxtEmail().clear();
		Utilities.enter(inputTxtEmail, emailPrefix);
		Utilities.click(btnSetButton);

		// 2. open email by subject
		String xpath = String.format(emailSubject, subject);
		Utilities.click(By.xpath(xpath));

		// 3. click activate link
		Utilities.click(activeEmailLink);

		// 4. close all tab
		Utilities.closeAllTabExceptHandle(mainTab);
	}

	public void resetAccountByEmail(String emailPrefix, String mainTab) {

		// 1. set mail
		Utilities.click(btnInputEmail);
		getInputTxtEmail().clear();
		Utilities.enter(inputTxtEmail, emailPrefix);
		Utilities.click(btnSetButton);

		// 2. click reset email
		Utilities.click(resetEmail);

		// 3. click reset link inside email
		Utilities.click(activeEmailLink);
		
		// 4. close all tab
		Utilities.closeAllTabExceptHandle(mainTab);
	}

}
