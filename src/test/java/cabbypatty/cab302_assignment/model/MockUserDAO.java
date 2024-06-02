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

    @Override
    public User updateUser(Integer id, String name, String email, Date dob, String gender) {
        User user = this.users.stream().filter(u -> {
            return u.getId().equals(id);
        }).findFirst().orElse(null);

        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setDob(dob);
            user.setGender(gender);
        }

        return user;
    }
}
