package Railway;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import Common.Utilities;
import Enums.TicketHeader;
import Constant.Constant;

public class MyTicketPage extends GeneralPage {
	// Elements
	private final String CANCEL_XPATH = "//tr[td[%d][normalize-space()='%s'] and td[%d][normalize-space()='%s']]//td[%d]//input[@value='Cancel']";
	private final String ROW_XPATH = "//tr[td[%d][normalize-space()='%s'] and td[%d][normalize-space()='%s']]";

	// Methods
	public void clickCancelTicket(String depart, String arrive) {

		int departIndex = getColumnIndex(TicketHeader.DEPART_STATION);
		int arriveIndex = getColumnIndex(TicketHeader.ARRIVE_STATION);
		int operationIndex = getColumnIndex(TicketHeader.OPERATION);

		String cancelXpath = String.format(CANCEL_XPATH, departIndex, depart, arriveIndex, arrive, operationIndex);

		Utilities.click(By.xpath(cancelXpath));
	}
	
	public void acceptCancelAlert() {
		Alert alert = Constant.WEBDRIVER.switchTo().alert();
		alert.accept();
	}
	
	public boolean isTicketDisplayed(String depart, String arrive) {

	    int departIndex = getColumnIndex(TicketHeader.DEPART_STATION);
	    int arriveIndex = getColumnIndex(TicketHeader.ARRIVE_STATION);

	    String rowXpath = String.format(
	        ROW_XPATH,
	        departIndex, depart,
	        arriveIndex, arrive
	    );

	    return Utilities.isDisplayed(By.xpath(rowXpath));
	}

}
