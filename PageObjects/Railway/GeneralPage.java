package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.json.StaticInitializerCoercer;
import org.testng.Assert;

import Common.Utilities;
import Constant.Constant;

public class GeneralPage {
	// Locators
	private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
	private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
	private final By tabFAQ = By.xpath("//div[@id='menu']//a[@href='/Page/FAQ.cshtml']");
	private final By lblWelcomeMessage = By.xpath("//div[@class='account']//strong");
	private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
	private final By selectedTab = By.xpath("//li[@class='selected']");
//	public static String tabName;
	
	// Elements
	protected WebElement getTabLogin() {
		return Constant.WEBDRIVER.findElement(tabLogin);
	}

	protected WebElement getTabLogout() {
		return Constant.WEBDRIVER.findElement(tabLogout);
	}

	protected WebElement getLblWelcomeMessage() {
		return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
	}

	protected WebElement getTabRegister() {
		return Constant.WEBDRIVER.findElement(tabRegister);
	}

	protected WebElement getTabFAQ() {
		return Constant.WEBDRIVER.findElement(tabFAQ);
	}

	protected WebElement getSelectedTab() {
		return Constant.WEBDRIVER.findElement(selectedTab);
	}

	// Methods
	public String getWelcomeMessage() {
		return this.getLblWelcomeMessage().getText();
	}

	public LoginPage gotoLoginPage() {
		this.getTabLogin().click();
		return new LoginPage();
	}

	public FAQPage gotoFAQPage() {
		this.getTabFAQ().click();
		return new FAQPage();
	}

	public HomePage gotoLogoutPage() {
		this.getTabLogout().click();
		return new HomePage();
	}

	public String getSelectedTabName() {
		return this.getSelectedTab().getText();
	}

	public boolean isTabExist(String tabName) {
		String xpathString = String.format("//a[span[text()='%s']]", tabName);
//		try {
//			return Constant.WEBDRIVER.findElement(By.xpath(xpathString)).isDisplayed();
//		} catch (Exception e) {
//			return false;
//		}
		return Utilities.isDisplayed(xpathString);
	}
	
	
	/*
	 * public static void isTabNotExist() { try { String xpathString =
	 * String.format("//a[span[text()='%s']]", tabName);
	 * Constant.WEBDRIVER.findElement(By.xpath(xpathString)); } catch (Exception e)
	 * { // TODO: handle exception System.out.println("hello fail"); } }
	 */

	/*
	 * public boolean isNotTabExist() { try { isTabExist();
	 * Assert.fail(String.format("%s tab is still displayed after logging out",
	 * tabName));
	 * 
	 * } catch (NoSuchElementException e) { // Expected exception, test passed } }
	 */
	
	
}
