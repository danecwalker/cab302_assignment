package cabbypatty.cab302_assignment.store.sqlite;
import java.sql.*;

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
                System.err.println("Error connecting to database: "+sqlEx.getMessage());
            }
        }
    }

    public void query(String query) {
        try {
            Statement statement = instance.createStatement();
            statement.execute(query);
        } catch (SQLException sqlEx) {
            System.err.println("Error executing query: "+sqlEx.getMessage());
        }
    }

    public ResultSet exec(String query)  {
        try {
            Statement statement = instance.createStatement();
            statement.execute(query);

            return statement.getResultSet();
        } catch (SQLException sqlEx) {
            System.err.println("Error executing query: "+sqlEx.getMessage());
        }

        return null;
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
            instance = null;
        } catch (SQLException sqlEx) {
            System.err.println("Error closing database connection: "+sqlEx.getMessage());
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
                    + "created_at TIMESTAMP NOT NULL,"
                    + "updated_at TIMESTAMP NOT NULL"
                + ")";
            statement.execute(query);
        } catch (SQLException sqlEx) {
            System.err.println("Error creating `user` table: "+sqlEx.getMessage());
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
            System.err.println("Error creating `session` table: "+sqlEx.getMessage());
        }

        // Create Journal Entry table
        try {
            Statement statement = instance.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS journal_entry ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "author_id INTEGER NOT NULL,"
                    + "body TEXT NOT NULL,"
                    + "mood INTEGER NOT NULL DEFAULT 0,"
                    + "created_at TIMESTAMP NOT NULL,"
                    + "updated_at TIMESTAMP NOT NULL,"
                    + "FOREIGN KEY(author_id) REFERENCES user(id)"
                + ")";
            statement.execute(query);
        } catch (SQLException sqlEx) {
            System.err.println("Error creating `journal_entry` table: "+sqlEx.getMessage());
        }
    }
}