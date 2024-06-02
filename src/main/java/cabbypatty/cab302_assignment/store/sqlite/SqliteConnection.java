package cabbypatty.cab302_assignment.store.sqlite;
import java.sql.*;

/**
 * A class which provides a connection to the SQLite database in the model package.
 */
public class SqliteConnection {
    private static Connection instance = null;

    /**
     * Providing a connection to the SQLite database in the model package.
     */
    public SqliteConnection() {
        connect();
    }

    /**
     * Establishing a connection to a SQLite database using JDBC and ensures that only one connection instance is created and reused throughout the application.
     */
    public void connect() {
        if (instance == null) {
            String url = "jdbc:sqlite:mockdb.sqlite";
            try {
                instance = DriverManager.getConnection(url);
                instance.createStatement().execute("PRAGMA journal_mode=MEMORY");
            } catch (SQLException sqlEx) {
                System.err.println("Error connecting to database: "+sqlEx.getMessage());
            }
        }
    }

    /**
     * A method that allows for executing SQL queries on the connected database. It's a basic mechanism to interact with the database by sending SQL statements for execution.
     * @param query
     */
    public void query(String query) {
        try {
            Statement statement = instance.createStatement();
            statement.execute(query);
        } catch (SQLException sqlEx) {
            System.err.println("Error executing query: "+sqlEx.getMessage());
        }
    }

    /**
     * A method that provides a way to execute SQL queries and retrieve the result set from the database. It's a more comprehensive version of the query method, as it also returns the result set for further processing.
     * @param query The SQL query
     * @return the result set for further processing
     */
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

    /**
     * A method that initializing the connection object.
     * @return instance
     */
    public Connection getInstance() {
        if (instance == null) {
            new SqliteConnection();
        }
        return instance;
    }

    /**
     * A method that ensures that the database connection is closed properly.
     */
    public void close() {
        try {
            instance.close();
            instance = null;
        } catch (SQLException sqlEx) {
            System.err.println("Error closing database connection: "+sqlEx.getMessage());
        }
    }

    /**
     * A method that ensures that the required tables are present in the database with the correct schema for storing user information, session information, and journal entries.
     */
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