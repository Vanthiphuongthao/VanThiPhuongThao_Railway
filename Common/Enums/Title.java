package Enums;

public enum Title {
	// Register
		TICKET_PRICE_DN_SG("Ticket price from Đà Nẵng to Sài Gòn");
	
	private final String title;

    Title(String title) {
        this.title = title;
    }

    public String getTitleTicketPrice() {
        return title;
    }

}
