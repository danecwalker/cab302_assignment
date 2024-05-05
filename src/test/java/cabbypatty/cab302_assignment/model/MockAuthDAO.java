package cabbypatty.cab302_assignment.model;

import cabbypatty.cab302_assignment.store.IAuthDAO;

import java.util.ArrayList;
import java.util.Date;

public class MockAuthDAO implements IAuthDAO {

    private ArrayList<Session> sessions = new ArrayList<>();

    @Override
    public void deleteExpiredSessions() {
        this.sessions.removeIf(s -> s.expiresAt.before(new Date()));
    }

    @Override
    public void deleteSession(String sessionId) {
        this.sessions.removeIf(s -> s.sessionID.equals(sessionId));
    }

    @Override
    public void deleteUserSessions(Integer userId) {
        this.sessions.removeIf(s -> s.userID.equals(userId));
    }

    @Override
    public SessionAndUser getSessionAndUser(String sessionId) {
        Session session = this.sessions.stream().filter(s -> s.sessionID.equals(sessionId)).findFirst().orElse(null);
        if (session == null) {
            return null;
        }

        return new SessionAndUser(session, new User(session.userID, "test", "test", "test", new Date(), "test"));
    }

    @Override
    public Session[] getUserSessions(Integer userId) {
        return this.sessions.stream().filter(s -> s.userID.equals(userId)).toArray(Session[]::new);
    }

    @Override
    public void setSession(Session session) {
        this.sessions.add(session);
    }

    @Override
    public void updateSessionExpiration(String sessionId, Date expiresAt) {
        this.sessions.stream().filter(s -> s.sessionID.equals(sessionId)).findFirst().ifPresent(s -> s.expiresAt = expiresAt);
    }
}
