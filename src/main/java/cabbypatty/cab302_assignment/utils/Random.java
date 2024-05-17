package cabbypatty.cab302_assignment.utils;

/**
 * Utility class for generating random strings.
 */
public class Random {

    /**
     * Generates a random string of the specified length.
     *
     * @param length the length of the random string to generate
     * @return a random string consisting of uppercase and lowercase letters and digits
     */
    public static String randomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt((int) (Math.random() * characters.length())));
        }
        return result.toString();
    }
}
