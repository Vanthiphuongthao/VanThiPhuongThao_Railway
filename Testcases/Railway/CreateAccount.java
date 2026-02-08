package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Enums.Message;
import Enums.Tabs;
import GuerrillaMail.GuerrillaMailPage;

public class CreateAccount extends BaseTest {

	@Test
	public void TC07() {
		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);

		System.out.println("TestCase07 - User can't create account with an already in-use email");
		System.out.println("Pre-condition: an actived account is existing");

		// create existing account
		AccountFlow accountFlow = new AccountFlow();
		accountFlow.registerAndActiveAccount(userAccount);

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();

		System.out.println("2. Click on \"Register\" tab");
		RegisterPage registerNewPage = homePage.gotoPage(Tabs.REGISTER, RegisterPage.class);

		System.out.println("3. Enter information of the created account in Pre-condition");
		System.out.println("4. Click on \"Register\" button");

		registerNewPage.register(userAccount, RegisterPage.class);
		String actualMsg = registerNewPage.getLblRegisterErrorMsgText();
		String expectedMsg = Message.REGISTER_EMAIL_ALREADY_IN_USE.getMessage();
		String errorMsg = Message.ERROR_MSG_NOT_DISPLAYED.getMessage();

		Assert.assertEquals(actualMsg, expectedMsg, errorMsg);
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

		// Verify general error message
		String actualFormErrorMsg = registerPage.getLblRegisterErrorMsgText();
		String expectedFormErrorMsg = Message.REGISTER_FORM_HAS_ERRORS.getMessage();
		String errorFormMsg = Message.FORM_ERR_MSG_NOT_DISPLAYED.getMessage();
		Assert.assertEquals(actualFormErrorMsg, expectedFormErrorMsg, errorFormMsg);

		// Verify password error message
		String actualPasswordErrorMsg = registerPage.getLblPasswordErrorMsgText();
		String expectedPasswordErrorMsg = Message.REGISTER_INVALID_PASSWORD_LENGTH.getMessage();
		String errorPwErrorMsg = Message.PW_ERR_MSG_NOT_DISPLAYED.getMessage();
		Assert.assertEquals(actualPasswordErrorMsg, expectedPasswordErrorMsg, errorPwErrorMsg);

		// Verify PID error message
		String actualPIDErrorMsg = registerPage.getLblPIDErrorMsgText();
		String expectedPIDErrorMsg = Message.REGISTER_INVALID_PID_LENGTH.getMessage();
		String errorPIDErrorMsg = Message.PID_ERR_MSG_NOT_DISPLAYED.getMessage();
		Assert.assertEquals(actualPIDErrorMsg, expectedPIDErrorMsg, errorPIDErrorMsg);
	}

	@Test
	public void TC09() {
		String emailFrom = "thanhletraining03@gmail.com";
		String mainTab = "Safe Railway";
		
		String emailPrefix = Utilities.generateRandomString(8);
		String email = emailPrefix + "@sharklasers.com";
		
		UserAccount userAccount = new UserAccount(email, Constant.PID, Constant.PID);

		System.out.println("TestCase09 - User create and activate account");
		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Click on \"Create an account\"");
		homePage.clickCreateAnAccount();

		String actualTabName = homePage.getSelectedTabName();
		String expectedTabName = Tabs.REGISTER.getText();
		String errorTabName = Message.REG_TABNAME_NOT_DISPLAYED.getMessage();
		Assert.assertEquals(actualTabName, expectedTabName, errorTabName);

		System.out.println("3. Enter valid information into all fields");
		System.out.println("4. Click on \"Register\" button");
		
		RegisterPage registerPage = new RegisterPage();
		registerPage.register(userAccount, RegisterPage.class);
		
		// verify
		String actualSuccessMsg = registerPage.getRegisterSuccessMsgText();
		String expectedSuccessMsg = Message.REGISTER_SUCCESS_MSG.getMessage();
		String errorSucessMsg = Message.REG_SUCCESS_MSG_NOT_DISPLAYED.getMessage();
		Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, errorSucessMsg);
		
		System.out.println(
				"5. Get email information (webmail address, mailbox and password) and navigate to that webmail");
		System.out.println("6. Login to the mailbox");
		System.out.println(
				"7. Open email with subject containing \"Please confirm your account\"  and the email of the new account at step 3");
		System.out.println("8. Click on the activate link");
		
		GuerrillaMailPage mailPage = new GuerrillaMailPage();
		mailPage.open();
		
		System.out.println("open link");
		mailPage.createFakeMail(emailPrefix);
		mailPage.activateAccountByEmail(emailFrom);
		Utilities.closeAllTabExceptHandle(mainTab);
		
		// verify confirm message
		String actualConfirmedMsg = registerPage.getRegistrationConfirmedMsg();
		String expectedConfirmedMsg = Message.REGISTRATION_CONFIRMED.getMessage();
		String errorConfirmedMsg = Message.REG_SUCCESS_MSG_NOT_DISPLAYED.getMessage();
		Assert.assertEquals(actualConfirmedMsg, expectedConfirmedMsg, errorConfirmedMsg);
		
	}

}
