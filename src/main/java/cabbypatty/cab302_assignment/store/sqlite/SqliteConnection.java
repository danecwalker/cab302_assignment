package cabbypatty.cab302_assignment.store.sqlite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteConnection {
    private static Connection instance = null;

    public SqliteConnection() {
        connect();
    }

    public void connect() {
        if (instance == null) {
            String url = "jdbc:sqlite:mockdb.sqlite";
            try {
                instance = DriverManager.getConnection(url);
            } catch (SQLException sqlEx) {
                System.err.println(STR."Error connecting to database: \{sqlEx.getMessage()}");
            }
        }
    }

    public Connection getInstance() {
        if (instance == null) {
            new SqliteConnection();
        }
        return instance;
    }

    public void close() {
        try {
            instance.close();
        } catch (SQLException sqlEx) {
            System.err.println(STR."Error closing database connection: \{sqlEx.getMessage()}");
        }
    }

    public void reset() {
        try {
            instance.close();
            instance = null;
        } catch (SQLException sqlEx) {
            System.err.println(STR."Error closing database connection: \{sqlEx.getMessage()}");
        }
    }

    public void setupTables() {
        // Create User table
        try {
            Statement statement = instance.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS user ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name TEXT NOT NULL,"
                    + "email TEXT NOT NULL,"
                    + "password TEXT NOT NULL,"
                    + "dob DATE NOT NULL,"
                    + "gender TEXT NOT NULL,"
                    + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                + ")";
            statement.execute(query);
        } catch (SQLException sqlEx) {
            System.err.println(STR."Error creating `user` table: \{sqlEx.getMessage()}");
        }

        // Create Session table
        try {
            Statement statement = instance.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS session ("
                    + "id TEXT PRIMARY KEY NOT NULL,"
                    + "user_id INTEGER NOT NULL,"
                    + "expires_at TIMESTAMP NOT NULL,"
                    + "FOREIGN KEY(user_id) REFERENCES user(id)"
                + ")";
            statement.execute(query);
        } catch (SQLException sqlEx) {
            System.err.println(STR."Error creating `session` table: \{sqlEx.getMessage()}");
        }
    }
}