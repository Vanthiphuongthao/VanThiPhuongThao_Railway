package Railway;

import org.openqa.selenium.By;

import Common.Utilities;
import Constant.Constant;

public class HomePage extends GeneralPage {
	// Locators
	private final By linkCreateAnAccount = By.xpath("//div[@id='content']//a[@href='/Account/Register.cshtml']");

	// Elements

	// Methods
	public HomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
		return this;
	}
	
	public void clickCreateAnAccount() {
		Utilities.click(linkCreateAnAccount);
	}

}
