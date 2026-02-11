package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Enums.Message;
import Enums.Tabs;
import GuerrillaMail.GuerrillaMailPage;

public class ResetPasswordTest extends BaseTest {
	@Test
	public void TC10() {
		String mainTab = "Safe Railway";

		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);

		System.out.println("TestCase10 - Reset password shows error if the new password is same as current");
		
		System.out.println("Pre-condition: an actived account is existing");
		Business.registerAccount(userAccount);
		Utilities.closeAllTabExceptHandle(mainTab);

		System.out.println("1. Navigate to QA Railway Login page");
		HomePage homePage = new HomePage();
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
		GuerrillaMailPage mailPage = new GuerrillaMailPage();
		mailPage.open();
		mailPage.setMail(userAccount.getUsername());
		mailPage.resetAccount();
		Utilities.closeAllTabExceptHandle(mainTab);

		System.out.println(
				"VP: Error message \"Could not reset password. Please correct the errors and try again.\" displays above the form.");
		Assert.assertTrue(loginPage.isResetPasswordFormDisplayed(), "Reset Password form is not displayed");

		System.out.println("8. Input same password into 2 fields \"new password\" and \"confirm password\"");
		System.out.println("9. Click Reset Password");
		loginPage.resetNewPassword(userAccount, LoginPage.class);

		System.out.println(
				"VP: Message \"The new password cannot be the same with the current password\" is shown");
		String actualResetMsg = loginPage.getLblResetPWMsgText();
		String expectedResetMsg = Message.RESET_ERROR_SAME_PW_MSG.getMessage();
		Assert.assertEquals(actualResetMsg, expectedResetMsg, "Error reset message is not displayed as expected");
	}

	@Test
	public void TC11() {
		String mainTab = "Safe Railway";

		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);

		System.out.println(
				"TestCase11 - Reset password shows error if the new password and confirm password doesn't match");

		System.out.println("Pre-condition: an actived account is existing");
		Business.registerAccount(userAccount);
		Utilities.closeAllTabExceptHandle(mainTab);

		System.out.println("1. Navigate to QA Railway Login page");
		HomePage homePage = new HomePage();
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

		GuerrillaMailPage mailPage = new GuerrillaMailPage();
		mailPage.open();
		mailPage.setMail(userAccount.getUsername());
		mailPage.resetAccount();
		Utilities.closeAllTabExceptHandle(mainTab);

		System.out.println("8. Input different input into 2 fields  \"new password\" and \"confirm password\"");
		System.out.println("9. Click Reset Password");
		loginPage.resetNewPWwithWrongConfirmPW(userAccount, LoginPage.class);

		System.out.println(
				"VP: Error message \"Could not reset password. Please correct the errors and try again.\" displays above the form.");
		String actualResetFormMsg = loginPage.getLblLoginFormErrorMsgText();
		String expectedResetFormMsg = Message.RESET_ERROR_FORM_MSG.getMessage();
		Assert.assertEquals(actualResetFormMsg, expectedResetFormMsg, "Error message is not displayed as expected");

		System.out.println(
				"VP: Error message \"The password confirmation did not match the new password.\" displays next to the confirm password field.");
		String actualErrorConfirmPWMsg = loginPage.getLblLoginConfirmPWErrorMsgText();
		String expectedErrorConfirmPWMsg = Message.RESET_ERROR_CONFIRM_PW_MSG.getMessage();
		Assert.assertEquals(actualErrorConfirmPWMsg, expectedErrorConfirmPWMsg, "Error message is not displayed as expected");
	}

}
