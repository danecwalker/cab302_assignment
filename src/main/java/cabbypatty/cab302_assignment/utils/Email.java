package cabbypatty.cab302_assignment.utils;

import java.util.regex.Pattern;

/**
 * Utility class for validating email addresses.
 */
public class Email {

    /**
     * Validates an email address based on a regular expression pattern.
     *
     * @param email the email address to validate
     * @return true if the email address is valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
