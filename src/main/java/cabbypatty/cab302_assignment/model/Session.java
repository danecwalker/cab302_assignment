package cabbypatty.cab302_assignment.model;

import java.sql.Date;

import static cabbypatty.cab302_assignment.utils.Random.randomString;

public class Session {
    public String sessionID;
    public String userID;
    public Date accessExpires;
    public Date refreshExpires;

    public Session(String userID) {
        this.userID = userID;
        this.sessionID = randomString(32);
        this.accessExpires = new Date(System.currentTimeMillis() + /* 1 hour */ 3600000);
        this.refreshExpires = new Date(System.currentTimeMillis() + /* 1 week */ 604800000);
    }
}