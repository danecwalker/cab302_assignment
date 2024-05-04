package cabbypatty.cab302_assignment.model;

import java.sql.Date;
import java.sql.Timestamp;

import static cabbypatty.cab302_assignment.utils.Random.randomString;

public class Session {
    public String sessionID;
    public Integer userID;
    public Timestamp expiresAt;

    public Session(Integer userID) {
        this.userID = userID;
        this.sessionID = randomString(32);
        this.expiresAt = new Timestamp(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7);
    }

    public Session(String sessionID, Integer userID, Timestamp expiresAt) {
        this.sessionID = sessionID;
        this.userID = userID;
        this.expiresAt = expiresAt;
    }
}