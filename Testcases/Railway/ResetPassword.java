package Railway;

import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Enums.Tabs;
import GuerrillaMail.GuerrillaMailPage;

public class ResetPassword extends BaseTest {
	@Test
	public void TC10() {
		String emailPrefix = "vOAhekVV";
		String emailResetPW = emailPrefix+ "@sharklasers.com";
		String passwordReset = Utilities.generateRandomString(8);
		
		UserAccount userAccount = new UserAccount(emailResetPW, passwordReset, Constant.PID);

		System.out.println("TestCase10 - Reset password shows error if the new password is same as current");
		System.out.println("Pre-condition: an actived account is existing");

		GuerrillaMailPage mailPage = new GuerrillaMailPage();
		mailPage.open();
		mailPage.createFakeMail(emailPrefix);
		mailPage.cleanAllMail();

//		// create existing account
//		AccountFlow accountFlow = new AccountFlow();
//		accountFlow.registerAndActiveAccount(userAccount);

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
		mailPage.openInNewTab();

		mailPage.createFakeMail(emailPrefix);
		mailPage.activateAccountByEmail("thanhletraining03@gmail.com");

		
		System.out.println("8. Input same password into 2 fields \"new password\" and \"confirm password\"");
		System.out.println("9. Click Reset Password");
		loginPage.resetNewPassword(userAccount, LoginPage.class);
		

	}

}
