package Enums;

public enum Station {
	NHA_TRANG("Nha Trang"),
	HUE("Huế"),
	SAI_GON("Sài Gòn"),
	PHAN_THIET("Phan Thiết"),
	QUANG_NGAI("Quảng Ngãi"),	
	DA_NANG("Đà Nẵng");

	private final String displayName;

	Station(String displayName) {
		this.displayName = displayName;
	}

	public String getStation() {
		return displayName;
	}
}
