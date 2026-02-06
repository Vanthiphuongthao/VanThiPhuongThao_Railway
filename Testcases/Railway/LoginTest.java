package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Enums.Message;

public class LoginTest extends BaseTest {
	@Test
	public void TC01() {
		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);

		System.out.println("TestCase01 - User can log into Railway with valid username and password");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Click on \"Login\" tab");
		LoginPage loginPage = homePage.gotoLoginPage();

		System.out.println("3. Enter valid Email and Password");
		System.out.println("4. Click on \"Login\" button");

		String actualMsg = loginPage.login(userAccount).getWelcomeMessage();
		String expectedMsg = "Welcome " + userAccount.getEmail();
		String errorMessage = Message.ERROR_MSG_WELCOME_MSG.getMessage();

		Assert.assertEquals(actualMsg, expectedMsg, errorMessage);

	}

	@Test
	public void TC02() {
		UserAccount userAccount = new UserAccount(Constant.USERNAME, "", Constant.PID);

		System.out.println("TestCase02 - User cannot login with blank \"Username\" textbox");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Click on \"Login\" tab");
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.login(userAccount);

		System.out.println(
				"3. User doesn't type any words into \"Username\" textbox but enter valid information into \"Password\" textbox ");
		System.out.println("4. Click on \"Login\" button");
		String actualMsg = loginPage.getLblLoginErrorMsgText();
		Utilities.verifyMessage(actualMsg, Message.LOGIN_FORM_ERROR_INVALID, Message.ERROR_MSG_ERROR_MSG);
	}

	@Test
	public void TC03() {
		UserAccount userAccount = new UserAccount(Constant.USERNAME, "@", Constant.PID);

		System.out.println("TestCase03 - User cannot log into Railway with invalid password");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Click on \"Login\" tab");
		System.out.println("3. Enter valid Email and invalid Password");
		System.out.println("4. Click on \"Login\" button");

		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.login(userAccount);

		String actualMsg = loginPage.getLblLoginErrorMsg().getText();
		Utilities.verifyMessage(actualMsg, Message.LOGIN_FORM_ERROR_INVALID, Message.ERROR_MSG_ERROR_MSG);
	}

	@Test
	public void TC04() {
		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);

		System.out.println("TestCase04 - System shows message when user enters wrong password many times");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Click on \"Login\" tab");
		LoginPage loginPage = homePage.gotoLoginPage();

		System.out.println("3. Enter valid information into \"Username\" textbox except \"Password\" textbox.");
		System.out.println("4. Click on \"Login\" button");
		System.out.println("5. Repeat step 3 and 4 three more times.");

		// Enter wrong password 5 times
		for (int i = 1; i <= 4; i++) {
			userAccount.setPassword(Utilities.generateRandomString(8));
			loginPage.login(userAccount);

			String actualMsg = loginPage.getLblLoginErrorMsg().getText();
			String expectedMsg = Message.LOGIN_INVALID_USERNAME_OR_PASSWORD.getMessage();

			Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected at attempt " + i);
		}
		// Verify locked account message
		String actualLockMsg = loginPage.getLblLoginErrorMsg().getText();
		Utilities.verifyMessage(actualLockMsg, Message.LOGIN_ACCOUNT_LOCK_WARNING, Message.ERROR_MSG_ACCOUNT_LOCK);
	}

	@Test
	public void TC05() {
		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);

		System.out.println("TestCase05 - User can't login with an account hasn't been activated");
		System.out.println("Pre-condition: a not-active account is existing");

		// create not-activated account
		RegisterPage registerPage = new RegisterPage();
		userAccount = registerPage.registerAndNotActive(userAccount);

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();

		System.out.println("2. Click on \"Login\" tab");
		System.out.println("3. Enter username and password of account hasn't been activated");
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.login(userAccount);

		System.out.println("4. Click on \"Login\" button");
		String actualMsg = loginPage.getLblLoginErrorMsg().getText();

		Utilities.verifyMessage(actualMsg, Message.LOGIN_INVALID_USERNAME_OR_PASSWORD, Message.ERROR_MSG_ERROR_MSG);
	}

}
