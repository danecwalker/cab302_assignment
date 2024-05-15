package cabbypatty.cab302_assignment.model;

import java.util.Date;
import java.sql.Timestamp;

/**
 * A simple model class representing a session with a session and user.
 */
public class SessionAndUser {
    private Session session;
    private User user;

    /**
     * Constructor that allows for creating a SessionAndUser object by directly passing the relevant Session and User objects.
     * @param session The session
     * @param user The user
     */
    public SessionAndUser(Session session, User user) {
        this.session = session;
        this.user = user;
    }

    /**
     * Constructor that creates an instance representing both a session and a user.
     * @param sessionId The id of the session
     * @param userId The id of the user
     * @param name The name of the user
     * @param email The email of the user
     * @param dob The dob of the user
     * @param gender The gender of the user
     * @param expiresAt The expired time for the session
     */
    public SessionAndUser(String sessionId, Integer userId, String name, String email, Date dob, String gender, Date expiresAt) {
        this.session = new Session(sessionId, userId, expiresAt);
        this.user = new User(userId, email, name, null, dob, gender);
    }

    /**
     * Returns the session of the session class represented by this Class object as a Session.
     * @return session The session
     */
    public Session getSession() {
        return session;
    }

    /**
     * Returns the user of the User class represented by this Class object as a user.
     * @return user The user of the session(?)
     */
    public User getUser() {
        return user;
    }
}
