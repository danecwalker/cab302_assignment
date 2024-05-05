package cabbypatty.cab302_assignment.model;

import java.util.List;

public interface IUserDAO {
    /**
     * Adds a new user to the database.
     * @param user The user to add.
     */
    public void addUser(User user);
    /**
     Retrieves a user from the database.
     @param email The email of the user to retrieve.
     @return The user with the given email, or null if not found.
     */
    public User getUser(String email);

    /**
     * Gets all users from the database.
     * @return a list of all users in the database
     */
    public List<User> getAllUsers();
}
