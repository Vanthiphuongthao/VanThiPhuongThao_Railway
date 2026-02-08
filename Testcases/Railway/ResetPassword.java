package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Enums.Message;
import Enums.Tabs;
import GuerrillaMail.GuerrillaMailPage;

public class ResetPassword extends BaseTest {
	@Test
	public void TC10() {
		String emailFrom = "thanhletraining03@gmail.com";
		String mainTab = "Safe Railway";

		String emailPrefix = "vOAhekVV";
		String emailResetPW = emailPrefix + "@sharklasers.com";
		String passwordReset = Utilities.generateRandomString(8);

		UserAccount userAccount = new UserAccount(emailResetPW, passwordReset, Constant.PID);

		System.out.println("TestCase10 - Reset password shows error if the new password is same as current");
		System.out.println("Pre-condition: an actived account is existing");

		GuerrillaMailPage mailPage = new GuerrillaMailPage();
		mailPage.open();
		mailPage.createFakeMail(emailPrefix);
		mailPage.cleanAllMail();

		System.out.println("1. Navigate to QA Railway Login page");
		HomePage homePage = new HomePage();
		homePage.open();
		homePage.gotoPage(Tabs.LOGIN, LoginPage.class);

		System.out.println("2. Click on \"Forgot Password page\" link");
		LoginPage loginPage = new LoginPage();
		loginPage.clickLinkForgotPassword();

		System.out.println("3. Enter the email address of the activated account");
		System.out.println("4. Click on \"Send Instructions\" button");
		loginPage.resetPassword(userAccount, LoginPage.class);

		System.out.println("5. Login to the mailbox (the same mailbox when creating account)");
		System.out.println(
				"6. Open email with subject contaning \"Please reset your password\" and the email of the account at step 3");
		System.out.println("7. Click on reset link");
		mailPage.open();
		mailPage.createFakeMail(emailPrefix);

		mailPage.activateAccountByEmail(emailFrom);
		Utilities.closeAllTabExceptHandle(mainTab);

		System.out.println("8. Input same password into 2 fields \"new password\" and \"confirm password\"");
		System.out.println("9. Click Reset Password");

		loginPage.resetNewPassword(userAccount, LoginPage.class);

		String actualResetMsg = loginPage.getLblResetPWMsgText();
		String expectedResetMsg = Message.RESET_ERROR_SAME_PW_MSG.getMessage();
		String errorMsg = Message.ERROR_RESET_PW_MSG_NOT_DISPLAYED.getMessage();
		Assert.assertEquals(actualResetMsg, expectedResetMsg, errorMsg);
	}

	@Test
	public void TC11() {
		String emailFrom = "thanhletraining03@gmail.com";
		String mainTab = "Safe Railway";

		String emailPrefix = "vOAhekVV";
		String emailResetPW = emailPrefix + "@sharklasers.com";
		String newPassword = Utilities.generateRandomString(8);

		UserAccount userAccount = new UserAccount(emailResetPW, newPassword, Constant.PID);

		System.out.println("TestCase10 - Reset password shows error if the new password is same as current");
		System.out.println("Pre-condition: an actived account is existing");

		GuerrillaMailPage mailPage = new GuerrillaMailPage();
		mailPage.open();
		mailPage.createFakeMail(emailPrefix);
		mailPage.cleanAllMail();

		System.out.println("1. Navigate to QA Railway Login page");
		HomePage homePage = new HomePage();
		homePage.open();
		homePage.gotoPage(Tabs.LOGIN, LoginPage.class);

		System.out.println("2. Click on \"Forgot Password page\" link");
		LoginPage loginPage = new LoginPage();
		loginPage.clickLinkForgotPassword();

		System.out.println("3. Enter the email address of the activated account");
		System.out.println("4. Click on \"Send Instructions\" button");
		loginPage.resetPassword(userAccount, LoginPage.class);

		System.out.println("5. Login to the mailbox (the same mailbox when creating account)");
		System.out.println(
				"6. Open email with subject contaning \"Please reset your password\" and the email of the account at step 3");
		System.out.println("7. Click on reset link");

		mailPage.open();
		mailPage.createFakeMail(emailPrefix);

		mailPage.activateAccountByEmail(emailFrom);
		Utilities.closeAllTabExceptHandle(mainTab);

		// verify PW form is shown and reset token is displayed
		Assert.assertTrue(loginPage.isResetPasswordFormDisplayed(), Message.RESET_FORM_NOT_DISPLAYED.getMessage());
		

		System.out.println("8. Input different input into 2 fields  \"new password\" and \"confirm password\"");
		System.out.println("9. Click Reset Password");

		loginPage.resetNewPWwithWrongConfirmPW(userAccount, LoginPage.class);
		
		// verify confirmPW field error
		String actualResetFormMsg = loginPage.getLblLoginFormErrorMsgText();
		String expectedResetFormMsg = Message.RESET_ERROR_SAME_PW_MSG.getMessage();
		String errorResetFormMsg = Message.ERROR_MSG_NOT_DISPLAYED.getMessage();
		Assert.assertEquals(actualResetFormMsg, expectedResetFormMsg, errorResetFormMsg);
		
		String actualErrorConfirmPWMsg = loginPage.getLblLoginConfirmPWErrorMsgText();
		String expectedErrorConfirmPWMsg = Message.RESET_ERROR_CONFIRM_PW_MSG.getMessage();
		String errorConfirmPWMsg = Message.ERROR_MSG_NOT_DISPLAYED.getMessage();
		Assert.assertEquals(actualErrorConfirmPWMsg, expectedErrorConfirmPWMsg, errorConfirmPWMsg);
	}

}
