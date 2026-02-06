package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;
import Enums.Message;

public class CreateAccount extends BaseTest {
	@Test
	public void TC07() {
		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);

		System.out.println("TestCase07 - User can't create account with an already in-use email");
		System.out.println("Pre-condition: an actived account is existing");

		// create existing account
		RegisterPage registerPage = new RegisterPage();
		userAccount = registerPage.registerAndActiveAccount(userAccount);

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();

		System.out.println("2. Click on \"Register\" tab");
		RegisterPage registerNewPage = homePage.gotoRegisterPage();

		System.out.println("3. Enter information of the created account in Pre-condition");
		System.out.println("4. Click on \"Register\" button");

		registerNewPage.register(userAccount);
		String actualMsg = registerPage.getLblRegisterErrorMsg().getText();
		String expectedMsg = Message.REGISTER_EMAIL_ALREADY_IN_USE.getMessage();

		Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
	}

	@Test
	public void TC08() {
		UserAccount userAccount = new UserAccount(Constant.USERNAME, "", "");

		System.out.println("TestCase08 - User can't create account while password and PID fields are empty");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Click on \"Register\" tab");
		RegisterPage registerPage = homePage.gotoRegisterPage();

		System.out.println("3. Enter information of the created account in Pre-condition");
		System.out.println("4. Click on \"Register\" button");

		registerPage.register(userAccount);

		// Verify general error message
		String actualFormErrorMsg = registerPage.getLblRegisterErrorMsgText();
		String expectedFormErrorMsg = Message.REGISTER_FORM_HAS_ERRORS.getMessage();
		Assert.assertEquals(actualFormErrorMsg, expectedFormErrorMsg,
				"Form error message is not displayed as expected");

		// Verify password error message
		String actualPasswordErrorMsg = registerPage.getLblPasswordErrorMsgText();
		String expectedPasswordErrorMsg = Message.REGISTER_INVALID_PASSWORD_LENGTH.getMessage();
		Assert.assertEquals(actualPasswordErrorMsg, expectedPasswordErrorMsg,
				"Password error message is not displayed as expected");

		// Verify PID error message
		String actualPIDErrorMsg = registerPage.getLblPIDErrorMsgText();
		String expectedPIDErrorMsg = Message.REGISTER_INVALID_PID_LENGTH.getMessage();
		Assert.assertEquals(actualPIDErrorMsg, expectedPIDErrorMsg, "PID error message is not displayed as expected");
	}

	@Test
	public void TC09() {
		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);
		
		System.out.println("TestCase09 - User create and activate account");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Click on \"Create an account\"");
		homePage.clickCreateAnAccount();

		String actualTabName = homePage.getSelectedTabName();
		String expectedTabNameString = "Register";

		// Verify Home page displays
		Assert.assertEquals(actualTabName, expectedTabNameString, "Register page displays");
		
		System.out.println("3. Enter valid information into all fields");
		System.out.println("4. Click on \"Register\" button");
		RegisterPage registerPage = new RegisterPage();
		registerPage.register(userAccount);
		
		
		
		

	}

}
