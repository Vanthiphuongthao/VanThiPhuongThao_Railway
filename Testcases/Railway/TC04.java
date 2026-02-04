package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;

public class TC04 extends BaseTest {
	@Test
	public void TestCase04() {
		System.out.println("TC04 - System shows message when user enters wrong password many times");

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
}