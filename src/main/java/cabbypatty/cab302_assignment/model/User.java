package cabbypatty.cab302_assignment.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    public Integer id;
    public String email;
    public String name;
    public Date dob;
    public String gender;

    public String hash_password;

    public User(Integer id, String email, String name, String hash_password, Date dob, String gender) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.hash_password = hash_password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        if (!isValidDOB(dob)) {
            throw new IllegalArgumentException("User must be at least 13 years old.");
        }
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (!isValidGender(gender)) {
            throw new IllegalArgumentException("Invalid gender value");
        }
        this.gender = gender;
    }

    public String getPassword() {
        return hash_password;
    }

    public void setPassword(String hash_password) {
        if (!isValidPassword(hash_password)) {
            throw new IllegalArgumentException("Password must be at least 12 characters long and include uppercase, lowercase, numbers, and symbols.");
        }
        this.hash_password = hash_password;
    }
    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidGender(String gender) {
        return (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")
                || gender.equalsIgnoreCase("Others"));
    }
    public boolean isValidDOB(Date dob) {
        LocalDate now = LocalDate.now();
        LocalDate dobLocalDate = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(dobLocalDate, now).getYears() >= 13;
    }

    private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{12,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
