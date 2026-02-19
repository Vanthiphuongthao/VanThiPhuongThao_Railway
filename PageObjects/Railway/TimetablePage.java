package Railway;

import org.openqa.selenium.By;

import Common.Utilities;
import Enums.TimetableHeader;

public class TimetablePage extends GeneralPage {

	// Elements
	private String columIndexByHeader = "count(//th[normalize-space()='%s']/preceding-sibling::th) + 1";
	
	private String rowByRouteXpath = "//tr[td[%s][normalize-space()='%s'] and td[%s][normalize-space()='%s']]";
	
	private String actionByHeaderXpath = rowByRouteXpath + "// td[%s]//a";

	// Methods
	public String getColumnIndexXpath(TimetableHeader header) {
		return String.format(columIndexByHeader, header.getTimetableHeader());
	}

	public TicketPricePage clickCheckPrice(String depart, String arrive) {
		String departCol = getColumnIndexXpath(TimetableHeader.DEPART_STATION);
		String arriveCol = getColumnIndexXpath(TimetableHeader.ARRIVE_STATION);
		String checkPriceCol = getColumnIndexXpath(TimetableHeader.CHECK_PRICE);

		String xpath = String.format(actionByHeaderXpath, departCol, depart, arriveCol, arrive, checkPriceCol);

		Utilities.click(By.xpath(xpath));
		
		return new TicketPricePage();
	}

}
