package cabbypatty.cab302_assignment.store.sqlite;

import cabbypatty.cab302_assignment.model.Journal;
import cabbypatty.cab302_assignment.store.IJournalDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A model class which is responsible for handling the CRUD operations with the SQLite database for journal.
 */
public class JournalDAO implements IJournalDAO {
    private SqliteConnection connection;

    /**
     * interacting with a database to perform operations related to journals.
     * @param connection The JournalDAO instance
     */
    public JournalDAO(SqliteConnection connection) {
        this.connection = connection;
    }

    /**
     * A method that returns a single user based on the provided email.
     * @param id The id of the journal
     * @return a single user or null
     */
    @Override
    public Journal getJournal(int id) {
        String query = "SELECT id, body, date, author FROM journal_entry WHERE id = '"+id+"'";
        ResultSet result = connection.exec(query);
        try {
            if (result.next()) {
                return new Journal(
                        result.getInt("id"),
                        result.getString("body"),
                        result.getTimestamp("created_at"),
                        result.getInt("author_id")
                );
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.err.println("Error executing query: " + ex.getMessage() + " " + ex.getStackTrace());
        }

        return null;
    }

    /**
     * A method that executes a SQL query to create the journals table if it does not already exist.
     */
    @Override
    public Journal createJournal(String body, Integer author_id) {
        String query = "INSERT INTO journal_entry (body, author_id, created_at, updated_at) VALUES ('"+body+"', '"+author_id+"', strftime(\"%Y-%m-%dT%H:%M:%SZ\", \"now\"), strftime(\"%Y-%m-%dT%H:%M:%SZ\", \"now\")) RETURNING id";
        ResultSet result = connection.exec(query);
        try {
            if (result.next()) {
                return getJournal(result.getInt("id"));
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.err.println("Error executing query: " + ex.getMessage() + " " + ex.getStackTrace());
        }

        return null;
    }

    /**
     * A method that retrieves journals authored by a specific user from the database.
     * @param author_id The id of author
     * @return an array containing the constructed Journal objects
     */
    @Override
    public Journal[] getJournals(Integer author_id) {
        String query = "SELECT COUNT(*) FROM journal_entry WHERE author_id = '"+author_id+"'";
        ResultSet result = connection.exec(query);
        Integer l = null;
        try {
            l = result.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Journal[] journals = new Journal[l];

        query = "SELECT id, body, created_at, author_id FROM journal_entry WHERE author_id = '"+author_id+"'";
        result = connection.exec(query);
        try {
            int i = 0;
            while (result.next()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
                String createdAt = result.getString("created_at");
                Date created = sdf.parse(createdAt);
                journals[i] = new Journal(
                        result.getInt("id"),
                        result.getString("body"),
                        created,
                        result.getInt("author_id")
                );
                i++;
            }
            return journals;
        } catch (Exception ex) {
            System.err.println("Error executing query: " + ex.getMessage() + " " + ex.getStackTrace());
        }
        return new Journal[0];
    }

    /**
     * A method that deletes the journal with the id of the journal.
     * @param id The id of the journal
     */
    @Override
    public void deleteJournal(int id) {
        String query = "DELETE FROM journal_entry WHERE id = '"+id+"'";
        connection.exec(query);
    }

    /**
     * A method that updates the journal's body with the id.
     * @param id The id of the journal
     * @param body The body of the journal
     */
    @Override
    public void updateJournal(int id, String body) {
        String query = "UPDATE journal_entry SET body = '"+body+"' WHERE id = '"+id+"'";
        connection.exec(query);
        query = "UPDATE journal_entry SET updated_at = strftime(\"%Y-%m-%dT%H:%M:%SZ\", \"now\") WHERE id = '"+id+"'";
        connection.exec(query);
    }
}
