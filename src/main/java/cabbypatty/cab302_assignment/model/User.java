package cabbypatty.cab302_assignment.model;

import java.util.Date;

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
}
