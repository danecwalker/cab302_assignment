package cabbypatty.cab302_assignment.model;

import cabbypatty.cab302_assignment.store.IUserDAO;

import java.util.ArrayList;
import java.util.Date;

public class MockUserDAO implements IUserDAO {
    private ArrayList<User> users = new ArrayList<>();

    @Override
    public User getUser(String email) {
        return this.users.stream().filter(u -> {
            return u.getEmail().equals(email);
        }).findFirst().orElse(null);
    }

    @Override
    public User createUser(String name, String email, String password, Date dob, String gender) {
        User user = new User(this.users.size() + 1, email, name, password, dob, gender);
        this.users.add(user);
        return user;
    }
}
