package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;

public class CreateAccount extends BaseTest {
	@Test
	public void TC07() {
		System.out.println("TestCase07 - User can't create account with an already in-use email");
		
		System.out.println("1. Navigate to QA Railway Website");
		
		HomePage homePage = new HomePage();
		homePage.open();
		
		System.out.println("2. Click on \"Register\" tab");
		RegisterPage registerPage = homePage.gotoRegisterPage();
		
		System.out.println("3. Enter information of the created account in Pre-condition");
		System.out.println("4. Click on \"Register\" button");
		
		userAccount.setEmail(Constant.notACTVEMAIL);
		registerPage.register(userAccount);
		String actualMsg = registerPage.getLblRegisterErrorMsg().getText();
		String expectedMsg = "This email address is already in use.";

		Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
	}
	
	@Test
	public void TC08() {
		System.out.println("TestCase08 - User can't create account while password and PID fields are empty");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Click on \"Register\" tab");
		RegisterPage registerPage = homePage.gotoRegisterPage();

		System.out.println("3. Enter information of the created account in Pre-condition");
		System.out.println("4. Click on \"Register\" button");

		userAccount.setPassword("");
		userAccount.setPid("");
		registerPage.register(userAccount);

		// Verify general error message
		String actualFormErrorMsg = registerPage.getLblRegisterErrorMsg().getText();
		String expectedFormErrorMsg = "There're errors in the form. Please correct the errors and try again.";
		Assert.assertEquals(actualFormErrorMsg, expectedFormErrorMsg, "Form error message is not displayed as expected");

		// Verify password error message
		String actualPasswordErrorMsg = registerPage.getLblPasswordErrorMsg().getText();
		String expectedPasswordErrorMsg = "Invalid password length";
		Assert.assertEquals(actualPasswordErrorMsg, expectedPasswordErrorMsg, "Password error message is not displayed as expected");
		
		// Verify PID error message
		String actualPIDErrorMsg = registerPage.getLblPIDErrorMsg().getText();
		String expectedPIDErrorMsg = "Invalid ID length";
		Assert.assertEquals(actualPIDErrorMsg, expectedPIDErrorMsg, "PID error message is not displayed as expected");
	}

}
