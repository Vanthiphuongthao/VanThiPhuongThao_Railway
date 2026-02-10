package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Enums.Message;
import Enums.Tabs;

public class LoginTest extends BaseTest {
	@Test
	public void TC01() {
		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);

		System.out.println("TestCase01 - User can log into Railway with valid username and password");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Click on \"Login\" tab");
		LoginPage loginPage = homePage.gotoPage(Tabs.LOGIN, LoginPage.class);

		System.out.println("3. Enter valid Email and Password");
		System.out.println("4. Click on \"Login\" button");

		String actualMsg = loginPage.login(userAccount, HomePage.class).getWelcomeMessage();
		String expectedMsg = "Welcome " + userAccount.getEmail();
		String errorMessage = Message.WELCOME_MSG_NOT_DISPLAYED.getMessage();

		Assert.assertEquals(actualMsg, expectedMsg, errorMessage);

	}

	@Test
	public void TC02() {
		// ms luan 
		//
		UserAccount userAccount = new UserAccount("", Constant.PASSWORD, Constant.PID);

		System.out.println("TestCase02 - User cannot login with blank \"Username\" textbox");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Click on \"Login\" tab");
		LoginPage loginPage = homePage.gotoPage(Tabs.LOGIN, LoginPage.class);
		loginPage.login(userAccount, LoginPage.class);

		System.out.println(
				"3. User doesn't type any words into \"Username\" textbox but enter valid information into \"Password\" textbox ");
		System.out.println("4. Click on \"Login\" button");
		String actualMsg = loginPage.getLblLoginErrorMsgText();
		String expectedMsg = Message.LOGIN_FORM_ERROR_INVALID.getMessage();
		String errorMessage = Message.ERROR_MSG_NOT_DISPLAYED.getMessage();
		Assert.assertEquals(actualMsg, expectedMsg, errorMessage);
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

		LoginPage loginPage = homePage.gotoPage(Tabs.LOGIN, LoginPage.class);
		loginPage.login(userAccount, LoginPage.class);

		String actualMsg = loginPage.getLblLoginErrorMsgText();
		String expectedMsg = Message.LOGIN_FORM_ERROR_INVALID.getMessage();
		String errorMessage = Message.ERROR_MSG_NOT_DISPLAYED.getMessage();
		Assert.assertEquals(actualMsg, expectedMsg, errorMessage);
	}

	@Test
	public void TC04() {
		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);

		System.out.println("TestCase04 - System shows message when user enters wrong password many times");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Click on \"Login\" tab");
		LoginPage loginPage = homePage.gotoPage(Tabs.LOGIN, LoginPage.class);

		System.out.println("3. Enter valid information into \"Username\" textbox except \"Password\" textbox.");
		System.out.println("4. Click on \"Login\" button");
		System.out.println("5. Repeat step 3 and 4 three more times.");

		// login wrong password 4 times
		loginPage.loginWithWrongPasswordManyTimes(userAccount, 4);
		
		// Verify locked account message
		String actualLockMsg = loginPage.getLblLoginErrorMsgText();
		String expectedLockMsg = Message.LOGIN_ACCOUNT_LOCK_WARNING.getMessage();
		String errorMsg = Message.ACCOUNT_LOCK_MSG_NOT_DISPLAYED.getMessage();
		Assert.assertEquals(actualLockMsg, expectedLockMsg, errorMsg);
	}

	@Test
	public void TC05() {
		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);

		System.out.println("TestCase05 - User can't login with an account hasn't been activated");
		System.out.println("Pre-condition: a not-active account is existing");

		// create not-activated account
		Business accountFlow = new Business();
		accountFlow.registerNotActiveAccount(userAccount);

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();

		System.out.println("2. Click on \"Login\" tab");
		System.out.println("3. Enter username and password of account hasn't been activated");
		LoginPage loginPage = homePage.gotoPage(Tabs.LOGIN, LoginPage.class);
		loginPage.login(userAccount, LoginPage.class);

		System.out.println("4. Click on \"Login\" button");
		String actualMsg = loginPage.getLblLoginErrorMsgText();
		String expectedMsg = Message.LOGIN_INVALID_USERNAME_OR_PASSWORD.getMessage();
		String errorMsg = Message.ERROR_MSG_NOT_DISPLAYED.getMessage();
		Assert.assertEquals(actualMsg, expectedMsg, errorMsg);
	}

}
