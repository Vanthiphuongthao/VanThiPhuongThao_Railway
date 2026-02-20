package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

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

	private static final By lblRegistrationConfirmedMsg = By.xpath("//*[@id=\"content\"]/p");

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

	// Methods
	public <T extends GeneralPage> T register(UserAccount userAccount, Class<T> pageClass) {
		Utilities.enter(txtEmail, userAccount.getEmail());
		Utilities.enter(txtPassword, userAccount.getPassword());
		Utilities.enter(txtConfirmPassword, userAccount.getPassword());
		Utilities.enter(txtPID, userAccount.getPid());
		
		Utilities.click(btnRegister);
		
//		this.getTxtEmail().sendKeys(userAccount.getEmail());
//		this.getTxtPassword().sendKeys(userAccount.getPassword());
//		this.getTxtConfirmPassword().sendKeys(userAccount.getPassword());
//		this.getTxtPID().sendKeys(userAccount.getPid());

//		this.getBtnRegister().click();

		try {
			return pageClass.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Cannot create page: " + pageClass.getName(), e);
		}
	}

	public String getLblRegisterErrorMsgText() {
		return Utilities.getElement(lblRegisterErrorMsg).getText();
	}

	public String getLblPasswordErrorMsgText() {
		return Utilities.getElement(lblPasswordErrorMsg).getText();
	}

	public String getLblPIDErrorMsgText() {
		return Utilities.getElement(lblPIDErrorMsg).getText();
	}

	public String getRegistrationConfirmedMsg() {
		return Utilities.getElement(lblRegistrationConfirmedMsg).getText();
	}
	
}
