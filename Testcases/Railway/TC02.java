package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;

public class TC02 extends BaseTest {
	@Test
	public void TestCase02() {
		System.out.println("TC02 - User cannot login with blank \"Username\" textbox");
		
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
}