package cabbypatty.cab302_assignment.store.sqlite;

import cabbypatty.cab302_assignment.model.Session;
import cabbypatty.cab302_assignment.model.SessionAndUser;
import cabbypatty.cab302_assignment.store.IAuthDAO;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class AuthDAO implements IAuthDAO {
    private final SqliteConnection connection;

    public AuthDAO(SqliteConnection connection) {
        this.connection = connection;
    }

    @Override
    public void deleteExpiredSessions() {
        String query = "DELETE FROM session WHERE expires_at < CURRENT_TIMESTAMP";
        connection.exec(query);
    }

    @Override
    public void deleteSession(String sessionId) {
        String query = "DELETE FROM session WHERE id = '"+sessionId+"'";
        connection.exec(query);
    }

    @Override
    public void deleteUserSessions(Integer userId) {
        String query = "DELETE FROM session WHERE user_id = "+userId;
        connection.exec(query);
    }

    @Override
    public SessionAndUser getSessionAndUser(String sessionId) {
        String query = "SELECT session.id, session.user_id, session.expires_at, user.name, user.email, user.dob, user.gender FROM session JOIN user ON session.user_id = user.id WHERE session.id = '"+sessionId+"'";
        ResultSet result = connection.exec(query);
        try {
            if (result.next()) {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(result.getString("dob"));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                String expiresAt = result.getString("expires_at");
                Date expires = sdf.parse(expiresAt);

                return new SessionAndUser(
                        result.getString("id"),
                        result.getInt("user_id"),
                        result.getString("name"),
                        result.getString("email"),
                        date,
                        result.getString("gender"),
                        expires
                );
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.err.println("Error executing query: " + ex.getMessage() + " " + ex.getStackTrace());
        }
        return null;
    }

    @Override
    public Session[] getUserSessions(Integer userId) {
        String query = "SELECT id, user_id, expires_at FROM session WHERE user_id = "+userId;
        ResultSet result = connection.exec(query);
        try {
            Session[] sessions = new Session[result.getFetchSize()];
            int i = 0;
            while (result.next()) {
                sessions[i] = new Session(
                        result.getString("id"),
                        result.getInt("user_id"),
                        result.getTimestamp("expires_at")
                );
                i++;
            }
            return sessions;
        } catch (Exception ex) {
            System.err.println("Error executing query: " + ex.getMessage());
        }
        return new Session[0];
    }

    @Override
    public void setSession(Session session) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        String query = "INSERT INTO session (id, user_id, expires_at) VALUES ('"+session.sessionID+"', "+session.userID+", '"+sdf.format(session.expiresAt)+"')";
        connection.exec(query);
    }

    @Override
    public void updateSessionExpiration(String sessionId, Date expiresAt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        String query = "UPDATE session SET expires_at = '"+sdf.format(expiresAt)+"' WHERE id = '"+sessionId+"'";
        connection.exec(query);
    }
}
