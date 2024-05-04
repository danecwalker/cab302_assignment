package cabbypatty.cab302_assignment.store.sqlite;

import cabbypatty.cab302_assignment.model.User;
import cabbypatty.cab302_assignment.store.IUserDAO;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class UserDAO implements IUserDAO {
    private final SqliteConnection connection;

    public UserDAO(SqliteConnection connection) {
        this.connection = connection;
    }

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

    @Override
    public User createUser(String name, String email, String password, Date dob, String gender) {
        String query = "INSERT INTO user (name, email, password, dob, gender, created_at, updated_at) VALUES ('"+name+"', '"+email+"', '"+password+"', '"+dob+"', '"+gender+"', strftime(\"%Y-%m-%dT%H:%M:%SZ\", \"now\"), strftime(\"%Y-%m-%dT%H:%M:%SZ\", \"now\"))";
        connection.exec(query);
        return getUser(email);
    }
}
