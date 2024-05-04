package cabbypatty.cab302_assignment.model;

import java.sql.Timestamp;
import java.util.Date;

import static cabbypatty.cab302_assignment.utils.Random.randomString;

public class Session {
    public String sessionID;
    public Integer userID;
    public Date expiresAt;

    public Session(Integer userID) {
        this.userID = userID;
        this.sessionID = randomString(32);
        this.expiresAt = new Timestamp(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7);
    }

    public Session(String sessionID, Integer userID, Date expiresAt) {
        this.sessionID = sessionID;
        this.userID = userID;
        this.expiresAt = expiresAt;
    }
}