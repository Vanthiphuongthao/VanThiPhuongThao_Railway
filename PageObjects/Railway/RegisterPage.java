package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

}
