package cabbypatty.cab302_assignment.model;

import cabbypatty.cab302_assignment.SessionStorage;
import cabbypatty.cab302_assignment.store.IAuthDAO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.prefs.BackingStoreException;

import static cabbypatty.cab302_assignment.utils.Random.randomString;

public class Session {
    public String sessionID;
    public Integer userID;
    public Date expiresAt;

    public Session(Integer userID) {
        this.userID = userID;
        this.sessionID = randomString(32);
        this.expiresAt = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7);
    }

    public Session(String sessionID, Integer userID, Date expiresAt) {
        this.sessionID = sessionID;
        this.userID = userID;
        this.expiresAt = expiresAt;
    }

    public static SessionAndUser validateSession(String sessionId, IAuthDAO authDAO) throws BackingStoreException {
        Date now = new Date();
        SessionAndUser su = authDAO.getSessionAndUser(sessionId);
        if (su == null) {
            return null;
        }

        if (su.getSession().expiresAt.before(now)) {
            authDAO.deleteSession(sessionId);
            SessionStorage.clearToken();
            return null;
        }

        Date halfWay = new Date(su.getSession().expiresAt.getTime() - (1000 * 60 * 60 * 24 * 7) + (1000 * 60 * 60 * 24 /* 1 day */));

        if (halfWay.before(now)) {
            su.getSession().expiresAt = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7);
            authDAO.updateSessionExpiration(su.getSession().sessionID, su.getSession().expiresAt);
        }

        return su;
    }
}