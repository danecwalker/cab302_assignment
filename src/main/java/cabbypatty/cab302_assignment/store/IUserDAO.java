package cabbypatty.cab302_assignment.store;

import cabbypatty.cab302_assignment.model.User;

public interface IUserDAO {
    public User getUser(String email);
}
