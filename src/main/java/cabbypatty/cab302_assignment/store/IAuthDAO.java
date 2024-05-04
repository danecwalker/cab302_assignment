package cabbypatty.cab302_assignment.store;

import cabbypatty.cab302_assignment.model.Session;
import cabbypatty.cab302_assignment.model.SessionAndUser;

import java.util.Date;

public interface IAuthDAO {
    void deleteExpiredSessions();
    void deleteSession(String sessionId);
    void deleteUserSessions(Integer userId);
    SessionAndUser getSessionAndUser( String sessionId );
    Session[] getUserSessions(Integer userId);
    void setSession(Session session);
    void updateSessionExpiration(String sessionId , Date expiresAt );
}
