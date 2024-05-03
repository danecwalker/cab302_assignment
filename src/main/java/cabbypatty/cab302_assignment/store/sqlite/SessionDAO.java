package cabbypatty.cab302_assignment.store.sqlite;

import cabbypatty.cab302_assignment.model.Session;
import cabbypatty.cab302_assignment.model.User;
import cabbypatty.cab302_assignment.store.ISessionDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import static cabbypatty.cab302_assignment.utils.Random.randomString;

public class SessionDAO implements ISessionDAO {
    private final Connection instance;

    public SessionDAO(SqliteConnection connection) {
        this.instance = connection.getInstance();
        if (instance == null) {
            System.err.println("Error connecting to database");
        }
    }
    @Override
    public Session createSession(String userID) {
        try {
            Statement statement = instance.createStatement();
            String query = STR."INSERT INTO session (user_id, session_id, access_expires, refresh_expires) VALUES ('\{userID}', '\{randomString(32)}', '\{new Date(System.currentTimeMillis() + 3600000)}', '\{new Date(System.currentTimeMillis() + 604800000)}')";
            statement.execute(query);
            return new Session(userID);
        } catch (Exception ex) {
            System.err.println("Error executing query: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public void deleteSession(String sessionID) {
        try {
            Statement statement = instance.createStatement();
            String query = STR."DELETE FROM session WHERE session_id = '\{sessionID}'";
            statement.execute(query);
        } catch (Exception ex) {
            System.err.println("Error executing query: " + ex.getMessage());
        }
    }

    @Override
    public void deleteAllSessionsForUser(Integer userID) {
        try {
            Statement statement = instance.createStatement();
            String query = STR."DELETE FROM session WHERE user_id = '\{userID}'";
            statement.execute(query);
        } catch (Exception ex) {
            System.err.println("Error executing query: " + ex.getMessage());
        }
    }


    @Override
    public boolean sessionExists(String sessionID) {
        try {
            Statement statement = instance.createStatement();
            String query = STR."SELECT COUNT(*) FROM session WHERE session_id = '\{sessionID}'";
            statement.execute(query);
            ResultSet result = statement.getResultSet();
            return result.getInt(1) > 0;
        } catch (Exception ex) {
            System.err.println("Error executing query: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public User getUserFromSession(String sessionID) {
        try {
            Statement statement = instance.createStatement();
            String query = STR."SELECT id, name, email, password FROM user WHERE id = (SELECT user_id FROM session WHERE session_id = '\{sessionID}')";
            statement.execute(query);
            ResultSet result = statement.getResultSet();
            if (result.next()) {
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
