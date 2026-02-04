package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;

public class TC03 extends BaseTest {
	@Test
	public void TestCase03() {
		System.out.println("TC03 - User cannot log into Railway with invalid password");
		
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
}