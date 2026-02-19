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
    RESET_ERROR_SAME_PW_MSG("The new password cannot be the same with the current password"),
    RESET_ERROR_FORM_MSG("Could not reset password. Please correct the errors and try again."),
    RESET_ERROR_CONFIRM_PW_MSG("The password confirmation did not match the new password."),
    
    // Book Ticket Message
    BOOK_TICKET_SUCCESS_MSG("Ticket booked successfully!");	

	private final String message;

    public String getMessage() {
		return message;
	}

	Message(String message) {
        this.message = message;
    }
}
