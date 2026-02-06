package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import GuerrillaMail.GuerrillaMailPage;

public class RegisterPage extends GeneralPage {
	// Locators
	private final By txtEmail = By.xpath("//input[@id='email']");
	private final By txtPassword = By.xpath("//input[@id='password']");
	private final By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By txtPID = By.xpath("//input[@id='pid']");
	private final By btnRegister = By.xpath("//input[@value='Register']");

	private final By lblRegisterErrorMsg = By.xpath("//p[@class='message error']");
	private final By lblPasswordErrorMsg = By.xpath("//label[@for='password' and @class='validation-error']");
	private final By lblPIDErrorMsg = By.xpath("//label[@for='pid' and @class='validation-error']");

	// Elements
	protected WebElement getTxtEmail() {
		return Constant.WEBDRIVER.findElement(txtEmail);
	}

	protected WebElement getTxtPassword() {
		return Constant.WEBDRIVER.findElement(txtPassword);
	}

	protected WebElement getTxtConfirmPassword() {
		return Constant.WEBDRIVER.findElement(txtConfirmPassword);
	}

	protected WebElement getTxtPID() {
		return Constant.WEBDRIVER.findElement(txtPID);
	}

	protected WebElement getBtnRegister() {
		return Constant.WEBDRIVER.findElement(btnRegister);
	}

	protected WebElement getLblRegisterErrorMsg() {
		return Constant.WEBDRIVER.findElement(lblRegisterErrorMsg);
	}

	protected WebElement getLblPasswordErrorMsg() {
		return Constant.WEBDRIVER.findElement(lblPasswordErrorMsg);
	}

	protected WebElement getLblPIDErrorMsg() {
		return Constant.WEBDRIVER.findElement(lblPIDErrorMsg);
	}

	// Methods
	public HomePage register(UserAccount userAccount) {
		this.getTxtEmail().sendKeys(userAccount.getEmail());
		this.getTxtPassword().sendKeys(userAccount.getPassword());
		this.getTxtConfirmPassword().sendKeys(userAccount.getPassword());
		this.getTxtPID().sendKeys(userAccount.getPid());

		this.getBtnRegister().click();

		return new HomePage();
	};

	public String getLblRegisterErrorMsgText() {
		return getLblRegisterErrorMsg().getText();
	}
	
	public String getLblPasswordErrorMsgText() {
		return getLblPasswordErrorMsg().getText();
	}
	
	public String getLblPIDErrorMsgText() {
		return getLblPIDErrorMsg().getText();
	}

	public UserAccount registerAndNotActive(UserAccount userAccount) {
		// 1. open Guerrilla Mail
		GuerrillaMailPage guerrillaMailPage = new GuerrillaMailPage();
		guerrillaMailPage.open();

		// 2. generate fake email
		String emailPrefix = Utilities.generateRandomString(8);
		String email = emailPrefix + "@sharklasers.com";

		// 3. create fake mail inbox
		guerrillaMailPage.createFakeMail(emailPrefix);
		String guerrillaWindow = Constant.WEBDRIVER.getWindowHandle();

		// 4. open railway in new tab
		((JavascriptExecutor) Constant.WEBDRIVER).executeScript("window.open(arguments[0], '_blank');",
				Constant.RAILWAY_URL);
		String railwayWindow = "";

		// 5. switch to railway tab
		for (String window : Constant.WEBDRIVER.getWindowHandles()) {
			Constant.WEBDRIVER.switchTo().window(window);
			if (Constant.WEBDRIVER.getCurrentUrl().contains("railway")) {
				railwayWindow = Constant.WEBDRIVER.getWindowHandle();
				break;
			}
		}

		// 6. register account
		HomePage homePage = new HomePage();
		homePage.open();

		RegisterPage registerPage = homePage.gotoRegisterPage();

		userAccount.setEmail(email);
		registerPage.register(userAccount);

		// created not-activated account
		return userAccount;
	};

	public UserAccount registerAndActiveAccount(UserAccount userAccount) {
		// 1. open Guerrilla Mail
		GuerrillaMailPage guerrillaMailPage = new GuerrillaMailPage();
		guerrillaMailPage.open();

		// 2. generate fake email
		String emailPrefix = Utilities.generateRandomString(8);
		String email = emailPrefix + "@sharklasers.com";

		// 3. create fake mail
		guerrillaMailPage.createFakeMail(emailPrefix);
		String guerrillaWindow = Constant.WEBDRIVER.getWindowHandle();

		// 4. open railway in new tab
		((JavascriptExecutor) Constant.WEBDRIVER).executeScript("window.open(arguments[0], '_blank');",
				Constant.RAILWAY_URL);
		String railwayWindow = "";

		// 5. switch to railway tab
		for (String window : Constant.WEBDRIVER.getWindowHandles()) {
			Constant.WEBDRIVER.switchTo().window(window);
			if (Constant.WEBDRIVER.getCurrentUrl().contains("railway")) {
				railwayWindow = Constant.WEBDRIVER.getWindowHandle();
				break;
			}
		}

		// 6. register account
		HomePage homePage = new HomePage();
		homePage.open();

		RegisterPage registerPage = homePage.gotoRegisterPage();

		userAccount.setEmail(email);
		registerPage.register(userAccount);

		// 7. activate account via email
		Constant.WEBDRIVER.switchTo().window(guerrillaWindow);
		guerrillaMailPage.activeNewAccount();

		Constant.WEBDRIVER.switchTo().window(railwayWindow);

		return userAccount;
	}

}
