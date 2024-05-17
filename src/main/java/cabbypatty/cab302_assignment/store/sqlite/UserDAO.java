package cabbypatty.cab302_assignment.store.sqlite;

import cabbypatty.cab302_assignment.model.User;
import cabbypatty.cab302_assignment.store.IUserDAO;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 * A model class which is responsible for handling the CRUD operations with the SQLite database.
 */
public class UserDAO implements IUserDAO {
    private final SqliteConnection connection;

    /**
     * interacting with a database to perform operations related to users.
     * @param connection The UserDAO instance
     */
    public UserDAO(SqliteConnection connection) {
        this.connection = connection;
    }

    /**
     * A method that returns a single user based on the provided email.
     * @param email The email of the user
     * @return a single user or null
     */
    public User getUser(String email) {
        String query = "SELECT id, name, email, password, dob, gender FROM user WHERE email = '"+email+"'";
        ResultSet resultSet = connection.exec(query);
        try {
            if (resultSet.next()) {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("dob"));
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("name"),
                        resultSet.getString("password"),
                        date,
                        resultSet.getString("gender")
                );
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.err.println("Error executing query: " + ex.getMessage() + " " + ex.getCause());
            return null;
        }
    }

    /**
     * A method that executes a SQL query to create the users table if it does not already exist.
     *
     * @param name The name of user
     * @param email The email of user
     * @param password The passwoed of the user
     * @param dob The date of birth of the user
     * @param gender The gender of the user
     * @return create a user with the email
     */
    @Override
    public User createUser(String name, String email, String password, Date dob, String gender) {
        String query = "INSERT INTO user (name, email, password, dob, gender, created_at, updated_at) VALUES ('"+name+"', '"+email+"', '"+password+"', '"+dob+"', '"+gender+"', strftime(\"%Y-%m-%dT%H:%M:%SZ\", \"now\"), strftime(\"%Y-%m-%dT%H:%M:%SZ\", \"now\"))";
        connection.exec(query);
        return getUser(email);
    }
}
