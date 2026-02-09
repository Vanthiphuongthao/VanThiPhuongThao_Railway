package Enums;

public enum SeatType {
	SOFT_BED_AC("Soft bed with air conditioner"),
	SOFT_SEAT_AC("Soft seat with air conditioner"),
	HARD_SEAT("Hard seat"),
	SOFT_SEAT("Soft seat"),
	HARD_BED("Hard bed"),
	SOFT_BED("Soft bed");

	private final String displayName;

	SeatType(String displayName) {
		this.displayName = displayName;
	}

	public String getSeatType() {
		return displayName;
	}

}
