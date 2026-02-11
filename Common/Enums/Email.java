package Enums;

public enum Email {
	
	// Email From
	ACTIVE_ACCOUNT_EMAIL_SUBJECT("thanhletraining03@gmail.com"),
	EMAIL_DOMAIN_SHARKLASERS("@sharklasers.com");
	
	private final String email;
	
	Email(String email) {
		this.email = email;
	}
	
	public String setEmail() {
		return email;
	}
}
