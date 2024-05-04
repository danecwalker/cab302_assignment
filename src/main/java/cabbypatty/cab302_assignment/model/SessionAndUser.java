package cabbypatty.cab302_assignment.model;

import java.util.Date;
import java.sql.Timestamp;

public class SessionAndUser {
    private Session session;
    private User user;

    public SessionAndUser(Session session, User user) {
        this.session = session;
        this.user = user;
    }

    public SessionAndUser(String sessionId, Integer userId, String name, String email, Date dob, String gender, Date expiresAt) {
        this.session = new Session(sessionId, userId, expiresAt);
        this.user = new User(userId, email, name, null, dob, gender);
    }

    public Session getSession() {
        return session;
    }

    public User getUser() {
        return user;
    }
}
