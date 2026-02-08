package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import Enums.Tabs;

public abstract class GeneralPage {

	// Locators
	private final String TAB_NAME_XPATH = "//div[@id='menu']//a[normalize-space()='%s']";

	private final By lblWelcomeMessage = By.xpath("//div[@class='account']//strong");
	private final By lblSuccessMsg = By.xpath("//div[@id='content']//h1");

	private final By selectedTab = By.xpath("//li[@class='selected']");

	// Elements
	protected WebElement getLblWelcomeMessage() {
		return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
	}

	protected WebElement getSelectedTab() {
		return Constant.WEBDRIVER.findElement(selectedTab);
	}

	protected WebElement getLblSuccessMsg() {
		return Constant.WEBDRIVER.findElement(lblSuccessMsg);
	}

	// Methods
	public void clickTab(Tabs tab) {
		String xpath = String.format(TAB_NAME_XPATH, tab.getText());
		Utilities.waitForClickable(By.xpath(xpath), Constant.TIMEOUT);
	}

	public <T extends GeneralPage> T gotoPage(Tabs tab, Class<T> expectedPage) {
		clickTab(tab);

		try {
			return expectedPage.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Cannot init page: " + expectedPage.getName(), e);
		}
	}

	public String getWelcomeMessage() {
		return this.getLblWelcomeMessage().getText();
	}

	public String getSelectedTabName() {
		return this.getSelectedTab().getText();
	}

	public boolean isTabExist(Tabs tab) {
		String xpath = String.format(TAB_NAME_XPATH, tab.getText());
		return Utilities.isDisplayed(By.xpath(xpath));
	}
	
	public String getRegisterSuccessMsgText() {
		return getLblSuccessMsg().getText();
	}

}
