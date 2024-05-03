package cabbypatty.cab302_assignment.model;

public class User {
    public Integer id;
    public String email;
    public String name;

    public String hash_password;

    public User(Integer id, String email, String name, String hash_password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.hash_password = hash_password;
    }
}
