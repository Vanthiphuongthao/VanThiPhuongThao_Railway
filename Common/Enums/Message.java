package Enums;

public enum Message {
	
	// Register
	REGISTER_EMAIL_ALREADY_IN_USE("This email address is already in use."),
	REGISTER_FORM_HAS_ERRORS("There're errors in the form. Please correct the errors and try again."),
	REGISTER_INVALID_PASSWORD_LENGTH("Invalid password length"),
	REGISTER_INVALID_PID_LENGTH("Invalid ID length"),
	
	// Login
	LOGIN_FORM_ERROR_INVALID("There was a problem with your login and/or errors exist in your form."),
    LOGIN_INVALID_USERNAME_OR_PASSWORD("Invalid username or password. Please try again."),
    LOGIN_ACCOUNT_LOCK_WARNING("You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes."),
	
	
	// Error Message
	ERROR_MSG_WELCOME_MSG("Welcome message is not displayed as expected"),
	ERROR_MSG_ERROR_MSG("Error message is not displayed as expected"),
	ERROR_MSG_ACCOUNT_LOCK("Account lock message is not displayed as expected");
	

	private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
