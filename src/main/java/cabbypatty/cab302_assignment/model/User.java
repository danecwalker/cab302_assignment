package cabbypatty.cab302_assignment.model;

import java.util.Date;

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
}
