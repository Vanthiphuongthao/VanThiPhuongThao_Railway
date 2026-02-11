package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Enums.Email;
import Enums.Message;
import Enums.Tabs;
import GuerrillaMail.GuerrillaMailPage;

public class CreateAccountTest extends BaseTest {

	@Test
	public void TC07() {
		String mainTab = "Safe Railway";

		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);

		System.out.println("TestCase07 - User can't create account with an already in-use email");

		System.out.println("Pre-condition: an actived account is existing");
		Business.registerAccount(userAccount, mainTab);

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();

		System.out.println("2. Click on \"Register\" tab");
		RegisterPage registerNewPage = homePage.gotoPage(Tabs.REGISTER, RegisterPage.class);

		System.out.println("3. Enter information of the created account in Pre-condition");
		System.out.println("4. Click on \"Register\" button");
		registerNewPage.register(userAccount, RegisterPage.class);

		System.out
				.println("VP: Error message \"This email address is already in use.\" displays above the form.");
		String actualMsg = registerNewPage.getLblRegisterErrorMsgText();
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
		RegisterPage registerPage = homePage.gotoPage(Tabs.REGISTER, RegisterPage.class);

		System.out.println("3. Enter information of the created account in Pre-condition");
		System.out.println("4. Click on \"Register\" button");
		registerPage.register(userAccount, RegisterPage.class);

		System.out.println(
				"VP: Message \"There're errors in the form. Please correct the errors and try again.\" appears above the form");
		String actualFormErrorMsg = registerPage.getLblRegisterErrorMsgText();
		String expectedFormErrorMsg = Message.REGISTER_FORM_HAS_ERRORS.getMessage();
		Assert.assertEquals(actualFormErrorMsg, expectedFormErrorMsg, "Form error message is not displayed as expected");

		System.out.println("VP: Next to password fields, error message \"Invalid password length.\" displays");
		String actualPasswordErrorMsg = registerPage.getLblPasswordErrorMsgText();
		String expectedPasswordErrorMsg = Message.REGISTER_INVALID_PASSWORD_LENGTH.getMessage();
		Assert.assertEquals(actualPasswordErrorMsg, expectedPasswordErrorMsg, "Password error message is not displayed as expected");

		System.out.println("VP: Next to PID field, error message \"Invalid ID length.\" displays");
		String actualPIDErrorMsg = registerPage.getLblPIDErrorMsgText();
		String expectedPIDErrorMsg = Message.REGISTER_INVALID_PID_LENGTH.getMessage();
		Assert.assertEquals(actualPIDErrorMsg, expectedPIDErrorMsg, "PID error message is not displayed as expected");
	}

	@Test
	public void TC09() {
		String emailSubject = Email.ACTIVE_ACCOUNT_EMAIL_SUBJECT.setEmail();
		String mainTab = "Safe Railway";

		String emailPrefix = Utilities.generateRandomString(8);
		String email = emailPrefix + Email.EMAIL_DOMAIN_SHARKLASERS.setEmail();

		UserAccount userAccount = new UserAccount(email, Constant.PID, Constant.PID);

		System.out.println("TestCase09 - User create and activate account");
		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Click on \"Create an account\"");
		System.out.println(
				"VP: Home page is shown with guide containing href \"create an account\" to \"Register\" page");
		homePage.clickCreateAnAccount();

		System.out.println("Expected: Register page is shown");
		String actualTabName = homePage.getSelectedTabName();
		String expectedTabName = Tabs.REGISTER.getTab();
		Assert.assertEquals(actualTabName, expectedTabName, "Register tab name is not displayed as expected");

		System.out.println("3. Enter valid information into all fields");
		System.out.println("4. Click on \"Register\" button");
		RegisterPage registerPage = new RegisterPage();
		registerPage.register(userAccount, RegisterPage.class);

		System.out.println("VP: \"Thank you for registering your account\" is shown");
		String actualSuccessMsg = registerPage.getRegisterSuccessMsgText();
		String expectedSuccessMsg = Message.REGISTER_SUCCESS_MSG.getMessage();
		Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, "Register success message is not displayed");

		System.out.println(
				"5. Get email information (webmail address, mailbox and password) and navigate to that webmail");
		System.out.println("6. Login to the mailbox");
		System.out.println(
				"7. Open email with subject containing \"Please confirm your account\"  and the email of the new account at step 3");
		System.out.println("8. Click on the activate link");

		GuerrillaMailPage mailPage = new GuerrillaMailPage();
		mailPage.open();
		mailPage.activateAccountByEmail(emailPrefix, emailSubject, mainTab);

		System.out.println(
				"VP: Redirect to Railways page and message \"Registration Confirmed! You can now log in to the site\" is shown");
		String actualConfirmedMsg = registerPage.getRegistrationConfirmedMsg();
		String expectedConfirmedMsg = Message.REGISTRATION_CONFIRMED.getMessage();
		Assert.assertEquals(actualConfirmedMsg, expectedConfirmedMsg, "Register success message is not displayed");
	}

}
