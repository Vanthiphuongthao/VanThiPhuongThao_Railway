package Enums;

public enum Message {
	
	// Register
	REGISTER_EMAIL_ALREADY_IN_USE("This email address is already in use."),
	REGISTER_FORM_HAS_ERRORS("There're errors in the form. Please correct the errors and try again."),
	REGISTER_INVALID_PASSWORD_LENGTH("Invalid password length"),
	REGISTER_INVALID_PID_LENGTH("Invalid ID length"),
	REGISTER_SUCCESS_MSG("Thank you for registering your account"),
	REGISTRATION_CONFIRMED("Registration Confirmed! You can now log in to the site."),
	
	// Login
	LOGIN_FORM_ERROR_INVALID("There was a problem with your login and/or errors exist in your form."),
    LOGIN_INVALID_USERNAME_OR_PASSWORD("Invalid username or password. Please try again."),
    LOGIN_ACCOUNT_LOCK_WARNING("You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes."),
	
	// Reset Password
    RESET_PW_MSG("The new password cannot be the same with the current password"),
    
    
	// Error Message
	WELCOME_MSG_NOT_DISPLAYED("Welcome message is not displayed as expected"),
	ERROR_MSG_NOT_DISPLAYED("Error message is not displayed as expected"),
	FORM_ERR_MSG_NOT_DISPLAYED("Form error message is not displayed as expected"),
	PW_ERR_MSG_NOT_DISPLAYED("Password error message is not displayed as expected"),
	PID_ERR_MSG_NOT_DISPLAYED("PID error message is not displayed as expected"),
	ACCOUNT_LOCK_MSG_NOT_DISPLAYED("Account lock message is not displayed as expected"),
	REG_SUCCESS_MSG_NOT_DISPLAYED("Register success message is NOT displayed"),
	ERROR_RESET_PW_MSG_NOT_DISPLAYED("Error reset message is not displayed as expected"),
	
	// Page is not displayed
	REG_TABNAME_NOT_DISPLAYED("Register tab name is not displayed as expected");
	

	private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
