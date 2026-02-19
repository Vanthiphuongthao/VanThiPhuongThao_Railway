package Railway;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class LoginPage extends GeneralPage {

	// Locators
	private final By txtUsername = By.xpath("//input[@id='username']");
	private final By txtPassword = By.xpath("//input[@id='password']");
	private final By txtEmail = By.xpath("//input[@id='email']");
	private final By txtNewPassword = By.xpath("//input[@id='newPassword']");
	private final By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By txtResetToken = By.xpath("//input[@id='resetToken']");

	private final By btnLogin = By.xpath("//input[@value='login']");
	private final By btnSendInstructions = By.xpath("//input[@type='submit' and @value='Send Instructions']");
	private final By btnResetPassword = By.xpath("//input[@value='Reset Password']");

	private final By linkForgotPassword = By.xpath("//a[@href='/Account/ForgotPassword.cshtml']");

	private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
	private final By lblLoginFormErrorMsg = By.xpath("//p[@class='message error']");
	private final By lblLoginConfirmPWErrorMsg = By.xpath("//li[@class='confirm-password']//label[@class='validation-error']");

	private final By lblResetPWMsg = By.xpath("//p[@class='message success']");
	private final By lblResetPasswordForm = By.xpath("//legend[text()='Password Change Form']");

	// Elements
//	protected WebElement getTxtUsername() {
//		return Constant.WEBDRIVER.findElement(txtUsername);
//	}
//
//	protected WebElement getTxtPassword() {
//		return Constant.WEBDRIVER.findElement(txtPassword);
//	}
//
//	protected WebElement getTxtEmail() {
//		return Constant.WEBDRIVER.findElement(txtEmail);
//	}
//
//	protected WebElement getTxtNewPassword() {
//		return Constant.WEBDRIVER.findElement(txtNewPassword);
//	}
//
//	protected WebElement getTxtConfirmPassword() {
//		return Constant.WEBDRIVER.findElement(txtConfirmPassword);
//	}
//
//	protected WebElement getBtnLogin() {
//		return Constant.WEBDRIVER.findElement(btnLogin);
//	}

	protected WebElement getLblLoginErrorMsg() {
		return Constant.WEBDRIVER.findElement(lblLoginErrorMsg);
	}

//	protected WebElement getLinkForgotPassword() {
//		return Constant.WEBDRIVER.findElement(linkForgotPassword);
//	}
//
//	protected WebElement getBtnSendInstructions() {
//		return Constant.WEBDRIVER.findElement(btnSendInstructions);
//	}
//	
//	protected WebElement getLblLoginConfirmPWErrorMsg() {
//		return Constant.WEBDRIVER.findElement(lblLoginConfirmPWErrorMsg);
//	}

	// Methods
	public <T extends GeneralPage> T login(UserAccount userAccount, Class<T> pageClass) {
		Utilities.enter(txtUsername, userAccount.getEmail());
		Utilities.enter(txtPassword, userAccount.getPassword());
		Utilities.click(btnLogin);

		try {
			return pageClass.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Cannot create page: " + pageClass.getName(), e);
		}
	}

	public <T extends GeneralPage> T resetPassword(UserAccount userAccount, Class<T> pageClass) {
		Utilities.enter(txtEmail, userAccount.getEmail());
		Utilities.click(btnSendInstructions);

		try {
			return pageClass.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Cannot create page: " + pageClass.getName(), e);
		}

	}

	public void resetPW(UserAccount userAccount) {
		Utilities.enter(txtEmail, userAccount.getEmail());
		Utilities.click(btnSendInstructions);
	}

	public <T extends GeneralPage> T resetNewPassword(UserAccount userAccount, Class<T> pageClass) {
		Utilities.enter(txtNewPassword, userAccount.getPassword());
		Utilities.enter(txtConfirmPassword, userAccount.getPassword());
		Utilities.click(btnResetPassword);

		try {
			return pageClass.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Cannot create page: " + pageClass.getName(), e);
		}

	}

	public <T extends GeneralPage> T resetNewPWwithWrongConfirmPW(UserAccount userAccount, Class<T> pageClass) {
		Utilities.enter(txtNewPassword, userAccount.getPassword());
		Utilities.enter(txtConfirmPassword, Utilities.generateRandomString(8));
		Utilities.click(btnResetPassword);

		try {
			return pageClass.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Cannot create page: " + pageClass.getName(), e);
		}

	}

	public String getLblLoginErrorMsgText() {
//		return getLblLoginErrorMsg().getText();
		return Utilities.getElement(lblLoginErrorMsg).getText();
	}

	public List<String> loginWithWrongPasswordManyTimes(UserAccount userAccount, int times) {
		List<String> messages = new ArrayList<>();

		for (int i = 1; i <= times; i++) {
			userAccount.setPassword(Utilities.generateRandomString(8));
			this.login(userAccount, LoginPage.class);
			messages.add(this.getLblLoginErrorMsgText());
		}
		return messages;
	}

	public void clickLinkForgotPassword() {
		Utilities.click(linkForgotPassword);
	}

	public String getLblResetPWMsgText() {
		return Utilities.getElement(lblResetPWMsg).getText();
	}
	

	public boolean isResetPasswordFormDisplayed() {
		return Utilities.isDisplayed(lblResetPasswordForm) && Utilities.isDisplayed(txtNewPassword)
				&& Utilities.isDisplayed(txtConfirmPassword) && Utilities.isDisplayed(txtResetToken);
	}
	
	public String getLblLoginFormErrorMsgText() {
		return Utilities.getElement(lblLoginFormErrorMsg).getText();
	}
	
	public String getLblLoginConfirmPWErrorMsgText() {
		return Utilities.getElement(lblLoginConfirmPWErrorMsg).getText();
	}

}