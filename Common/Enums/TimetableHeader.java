package Enums;

public enum TimetableHeader {
	DEPART_STATION("Depart Station"),
	ARRIVE_STATION("Arrive Station"),
	CHECK_PRICE("Check Price"),
	BOOK_TICKET("Book ticket");

	private final String text;

	TimetableHeader(String text) {
		this.text = text;
	}

	public String getTimetableHeader() {
		return text;
	}
}
