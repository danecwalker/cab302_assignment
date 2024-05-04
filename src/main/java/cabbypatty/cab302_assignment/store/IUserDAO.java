package cabbypatty.cab302_assignment.store;

import cabbypatty.cab302_assignment.model.User;

import java.util.Date;

public interface IUserDAO {
    public User getUser(String email);
    public User createUser(String name, String email, String password, Date dob, String gender);
}
