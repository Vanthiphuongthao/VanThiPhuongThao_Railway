package Enums;

public enum Tabs {

	HOME("Home"),
	FAQ("FAQ"),
	CONTACT("Contact"),
	TIMETABLE("Timetable"),
	TICKET_PRICE("Ticket price"),
	BOOK_TICKET("Book ticket"),
	MY_TICKET("My ticket"),
	CHANGE_PASSWORD("Change password"),
	REGISTER("Register"),
	LOGIN("Login"),
	LOGOUT("Logout");
	

	private final String text;

	Tabs(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
}