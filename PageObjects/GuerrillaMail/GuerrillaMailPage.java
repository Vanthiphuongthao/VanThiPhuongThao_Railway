package GuerrillaMail;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Utilities;
import Constant.Constant;

public class GuerrillaMailPage {

	// Locatros
	private final By btnInputEmail = By.xpath("//span[@id='inbox-id']");
	private final By inputTxtEmail = By.xpath("//span[@id='inbox-id']/input");
	private final By btnSetButton = By.xpath("//button[text()='Set']");
	private final By newestEmail = By.xpath("//tbody[@id='email_list']//td[@class='td2']");
	public static final String FAKEMAIL_URL = "https://www.guerrillamail.com";
	private final By activeEmailLink = By.xpath("//div[contains(@class, 'email_page')]//a[@target='_blank']");

	private final By btnCloseAd = By.xpath("//div[@id='dismiss-button' and @aria-label='Close ad']");

	// Elements
	protected WebElement getBtnInputEmail() {
		return Constant.WEBDRIVER.findElement(btnInputEmail);
	}

	protected WebElement getInputTxtEmail() {
		return Constant.WEBDRIVER.findElement(inputTxtEmail);
	}

	protected WebElement getBtnSetButton() {
		return Constant.WEBDRIVER.findElement(btnSetButton);
	}

	protected WebElement getActiveEmailLink() {
		return Constant.WEBDRIVER.findElement(Utilities.waitForClickable(activeEmailLink, 30));
	}

	protected WebElement getNewestEmail() {
		return Constant.WEBDRIVER.findElement(Utilities.waitForClickable(newestEmail, 30));
	}

	// Methods
	public GuerrillaMailPage open() {
		Constant.WEBDRIVER.navigate().to(Constant.GUERRILLAMAIL_URL);
		return this;
	}

	public void createFakeMail(String emailFake) {
		getBtnInputEmail().click();
		getInputTxtEmail().clear();
		getInputTxtEmail().sendKeys(emailFake);
		getBtnSetButton().click();
	}

	public void closeAdIfPresent() {
	    List<WebElement> closeButtons =
	            Constant.WEBDRIVER.findElements(btnCloseAd);

	    if (!closeButtons.isEmpty() && closeButtons.get(0).isDisplayed()) {
	        closeButtons.get(0).click();
	    }
	}

	public void activeNewAccount() {
		getNewestEmail().click();
		getActiveEmailLink().click();
	}
//	
//	public void clickBtnInputEmail() {
//		getBtnInputEmail().click();
//	}
//	
//	public void inputFakeEmail(String emailFake) {
//		getTxtEmail().click();
////		emailTextbox.clear();
////		emailTextbox.sendKeys(emailFake);
//	}
//	
//	public void clickSetButton() {
//		getBtnSetButton().click();
//	}
//	
//	// Create Custom Mail Fake
//	public void setCustomFakeEmail(String emaiFake) {
//		clickAddNewEmail();
//		inputFakeEmail(emaiFake);
//		clickSetButton();
//	}
//	
//	public void openNewestEmail() {
//		getNewestEmail().click();
//	}
//	
//	public void clickActivationLink() {
//		getActiveEmailLink().click();
//	}
//	
//	// Open newest email and click activation link
//	public void openActivateAccount() {
//		openNewestEmail();
//		clickActivationLink();
//	}
//	

//	public GuerrillaMailPage openGuerrillaMailInNewTab() {
//		Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
//		Constant.WEBDRIVER.navigate().to(Constant.GUERRILLAMAIL_URL);
//	}

	public void switchToNewestTab() {
		for (String window : Constant.WEBDRIVER.getWindowHandles()) {
			Constant.WEBDRIVER.switchTo().window(window);
		}
	}

}
