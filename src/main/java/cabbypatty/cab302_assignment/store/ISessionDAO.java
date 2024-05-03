package cabbypatty.cab302_assignment.store;

import cabbypatty.cab302_assignment.model.Session;
import cabbypatty.cab302_assignment.model.User;

public interface ISessionDAO {
    Session createSession(String userID);
    void deleteSession(String sessionID);
    void deleteAllSessionsForUser(Integer userID);
    boolean sessionExists(String sessionID);
    User getUserFromSession(String sessionID);
}
