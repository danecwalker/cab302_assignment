package cabbypatty.cab302_assignment.store.sqlite;

import cabbypatty.cab302_assignment.model.User;
import cabbypatty.cab302_assignment.store.IUserDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO implements IUserDAO {
    private final Connection connection;

    public UserDAO(SqliteConnection connection) {
        this.connection = connection.getInstance();
    }

    public User getUser(String email) {
        try {
            Statement statement = connection.createStatement();
            String query = STR."SELECT id, name, email, password FROM user WHERE email = '\{email}'";
            statement.execute(query);
            ResultSet result = statement.getResultSet();
            if (result.next()) {
                System.out.println("User found");
                return new User(result.getInt("id"), result.getString("email"), result.getString("name"), result.getString("password"));
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.err.println("Error executing query: " + ex.getMessage());
            return null;
        }
    }
}
