package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;

public class TC01 extends BaseTest {
	@Test
	public void TestCase01() {
		System.out.println("TC01 - User can log into Railway with valid username and password");
		
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
}