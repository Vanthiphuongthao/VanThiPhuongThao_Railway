package GuerrillaMail;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class GuerrillaMailPage {

	// Locators
	private final By btnInputEmail = By.xpath("//span[@id='inbox-id']");
	private final By inputTxtEmail = By.xpath("//span[@id='inbox-id']/input");
	private final By btnSetButton = By.xpath("//button[text()='Set']");
	private final By newestEmail = By.xpath("//tbody[@id='email_list']//td[text()='thanhletraining03@gmail.com ']");
	public static final String FAKEMAIL_URL = "https://www.guerrillamail.com";
	private final By activeEmailLink = By.xpath("//div[contains(@class, 'email_page')]//a[@target='_blank']");

	// Elements
	protected WebElement getInputTxtEmail() {
		return Constant.WEBDRIVER.findElement(inputTxtEmail);
	}

	// Methods
	public GuerrillaMailPage open() {
		Constant.WEBDRIVER.navigate().to(Constant.GUERRILLAMAIL_URL);
		return this;
	}

	public void createFakeMail(String emailFake) {
		Utilities.click(btnInputEmail);
		getInputTxtEmail().clear();
		Utilities.enter(inputTxtEmail, emailFake);
		Utilities.click(btnSetButton);
	}

	public void activeNewAccount() {
		Utilities.click(newestEmail);
		Utilities.click(activeEmailLink);
	}

	public void openNewTabs(String urlNewTab, String tabName) {
		((JavascriptExecutor) Constant.WEBDRIVER).executeScript("window.open(arguments[0], '_blank');", urlNewTab);
		String tempString = "";

		// switch to new tab
		for (String window : Constant.WEBDRIVER.getWindowHandles()) {
			Constant.WEBDRIVER.switchTo().window(window);
			if (Constant.WEBDRIVER.getCurrentUrl().contains(tabName)) {
				tempString = Constant.WEBDRIVER.getWindowHandle();
				break;
			}
		}

	}

}
