package Railway;

import org.openqa.selenium.By;

import Common.Utilities;

public class TicketPricePage extends GeneralPage {

	// Elements
	private static final String PRICE_TITLE_XPATH = "//tr[@class='TableSmallHeader']/th[normalize-space()=concat('Ticket price from ', '%s', ' to ', '%s')]";

	private static final String PRICE_BY_SEAT_XPATH = "(//tr[td[text()='%s']]//following-sibling::tr//td)"
			+ "[count(//tr[td[text()='%s']]//td[text()='%s']//preceding-sibling::td) + 1]";

	// Methods
	public String getPriceTitle(String departStation, String arriveStation) {
		String dynamicXpath = String.format(PRICE_TITLE_XPATH, departStation, arriveStation);
		return Utilities.getElement(By.xpath(dynamicXpath)).getText();
	}

	public String getPriceBySeat(String seatCode) {
		String dynamicXpath = String.format(PRICE_BY_SEAT_XPATH, seatCode, seatCode, seatCode);

		return Utilities.getElement(By.xpath(dynamicXpath)).getText();
	}

}
