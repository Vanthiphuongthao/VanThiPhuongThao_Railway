package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;

public class LoginTest extends BaseTest {
	@Test
	public void TC01() {
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

		Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
	}
	
	@Test
	public void TC02() {
		System.out.println("TestCase02 - User cannot login with blank \"Username\" textbox");
		
		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();
		
		System.out.println("2. Click on \"Login\" tab");
		LoginPage loginPage = homePage.gotoLoginPage();
		userAccount.setPassword("");
		loginPage.login(userAccount);
		
		System.out.println("3. User doesn't type any words into \"Username\" textbox but enter valid information into \"Password\" textbox ");
		System.out.println("4. Click on \"Login\" button");
		String actualMsg = loginPage.getLblLoginErrorMsg().getText();
		String expectedMsg = "There was a problem with your login and/or errors exist in your form.";

		Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
	}
	
	@Test
	public void TC03() {
		System.out.println("TestCase03 - User cannot log into Railway with invalid password");
		
		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();
		
		System.out.println("2. Click on \"Login\" tab");
		System.out.println("3. Enter valid Email and invalid Password");
		System.out.println("4. Click on \"Login\" button");
		
		LoginPage loginPage = homePage.gotoLoginPage();
		userAccount.setPassword("@");
		loginPage.login(userAccount);
		
		String actualMsg = loginPage.getLblLoginErrorMsg().getText();
		String expectedMsg = "There was a problem with your login and/or errors exist in your form.";

		Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
	}
	
	@Test
	public void TC04() {
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
		for (int i = 1; i <= 5; i++) {
			userAccount.setPassword(Utilities.generateRandomString(8));
			loginPage.login(userAccount);

			String actualMsg = loginPage.getLblLoginErrorMsg().getText();
			String expectedMsg = "Invalid username or password. Please try again.";

			Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected at attempt " + i);
		}
		// Verify locked account message
		String actualLockMsg = loginPage.getLblLoginErrorMsg().getText();
		String expectedLockMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

		Assert.assertEquals(actualLockMsg, expectedLockMsg, "Account lock message is not displayed as expected");
	}
	
	@Test
	public void TC05() {
		System.out.println("TestCase05 - User can't login with an account hasn't been activated");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();
		
		System.out.println("2. Click on \"Login\" tab");
		System.out.println("3. Enter username and password of account hasn't been activated");
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.login(notActUserAccount);
		
		System.out.println("4. Click on \"Login\" button");
		String actualMsg = loginPage.getLblLoginErrorMsg().getText();
		String expectedMsg = "Invalid username or password. Please try again.";

		Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
	}
	
}
