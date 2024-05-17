package cabbypatty.cab302_assignment.store;

import cabbypatty.cab302_assignment.model.User;

import java.util.Date;

/**
 * Interface for handling user-related data access operations.
 */
public interface IUserDAO {

    /**
     * Retrieves a user based on their email address.
     *
     * @param email the email address of the user to retrieve
     * @return a User object representing the user
     */

    User getUser(String email);
    /**
     * Creates a new user with the provided details.
     *
     * @param name the name of the user
     * @param email the email address of the user
     * @param password the password for the user
     * @param dob the date of birth of the user
     * @param gender the gender of the user
     * @return a User object representing the newly created user
     */
    User createUser(String name, String email, String password, Date dob, String gender);
}
