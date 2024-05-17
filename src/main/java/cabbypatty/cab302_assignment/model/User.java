package cabbypatty.cab302_assignment.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple model class representing a user with an id, email address, name, date of birth, gender, and hash-password.
 */
public class User {
    public Integer id;
    public String email;
    public String name;
    public Date dob;
    public String gender;

    public String hash_password;

    /**
     * Constructs a new User data with the specified id, email, name, date of birth, gender, and hash_password.
     * @param id The id of the user
     * @param name The name of the user
     * @param email The email address of the user
     * @param dob The date of birth of the user
     * @param gender The gender of the user
     * @param hash_password The hash_password of the user
     */
    public User(Integer id, String email, String name, String hash_password, Date dob, String gender) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.hash_password = hash_password;
    }

    /**
     * Returns the name of the user class represented by this Class object as a String.
     * @return name The name of user
     */
    public String getName() {
        return name;
    }

    /** Setting the name of the user
     * @param name The name of User
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    /**
     * Returns the email of the user class represented by this Class object as a String.
     * @return email The email of user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setting the email of the user
     * @param email The email of user
     */
    public void setEmail(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.email = email;
    }

    /**
     * Returns the dob of the user class represented by this Class object as a Date.
     * @return dob The date of birth of the user
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Setting the dob of the user
     * @param dob The date of birth of the user
     */
    public void setDob(Date dob) {
        if (!isValidDOB(dob)) {
            throw new IllegalArgumentException("User must be at least 13 years old.");
        }
        this.dob = dob;
    }

    /**
     * Returns the gender of the user class represented by this Class object as a String.
     * @return gender The gender of the user
     */
    public String getGender() {
        return gender;
    }

    /**
     * Setting the gender of the user
     * @param gender The gender of the user
     */
    public void setGender(String gender) {
        if (!isValidGender(gender)) {
            throw new IllegalArgumentException("Invalid gender value");
        }
        this.gender = gender;
    }

    /**
     * Returns the hash_password of the user class represented by this Class object as a String.
     * @return hash_password The password of the user
     */
    public String getPassword() {
        return hash_password;
    }

    /**
     * Setting the password of the user
     * @param hash_password The password of the user
     */
    public void setPassword(String hash_password) {
        if (!isValidPassword(hash_password)) {
            throw new IllegalArgumentException("Password must be at least 12 characters long and include uppercase, lowercase, numbers, and symbols.");
        }
        this.hash_password = hash_password;
    }

    /**
     * Checking if the email is already existed or valid string use
     * @param email The email of the user
     * @return The existed user
     */
    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Checking if the gender input is valid string form
     * @param gender The gender of the user
     * @return The selected gender
     */
    private boolean isValidGender(String gender) {
        return (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")
                || gender.equalsIgnoreCase("Others"));
    }

    /**
     * Checking if the dob input is valid age for using this app
     * @param dob The dob of the user
     * @return The valid age for the app
     */
    public boolean isValidDOB(Date dob) {
        LocalDate now = LocalDate.now();
        LocalDate dobLocalDate = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(dobLocalDate, now).getYears() >= 13;
    }

    /**
     * Checking if the password, input is valid string form and existed user with the password
     * @param password The password of the user
     * @return Existed user with the password
     */
    private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{12,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
