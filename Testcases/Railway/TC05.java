package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TC05 extends BaseTest {
	@Test
	public void TestCase05() {
		System.out.println("TC05 - User can't login with an account hasn't been activated");

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