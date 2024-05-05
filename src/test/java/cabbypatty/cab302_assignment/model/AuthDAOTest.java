package cabbypatty.cab302_assignment.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AuthDAOTest {
    private MockAuthDAO mockAuthDAO;

    @BeforeEach
    void setUp() {
        this.mockAuthDAO = new MockAuthDAO();
    }

    @Test
    void testAddAndRetrieveSession() {
        Session session = new Session("session1", 1, new Date(System.currentTimeMillis() + 10000)); // 10 seconds from now
        this.mockAuthDAO.setSession(session);

        // Check if the session can be retrieved
        Session[] sessions = this.mockAuthDAO.getUserSessions(1);
        assertEquals(1, sessions.length);
        assertEquals("session1", sessions[0].sessionID);
    }

    @Test
    void testUpdateSessionExpiration() {
        Session session = new Session("session2", 2, new Date());
        this.mockAuthDAO.setSession(session);

        // Update the expiration date
        Date newExpirationDate = new Date(System.currentTimeMillis() + 20000); // 20 seconds from now
        this.mockAuthDAO.updateSessionExpiration("session2", newExpirationDate);

        SessionAndUser sessionAndUser = this.mockAuthDAO.getSessionAndUser("session2");
        assertNotNull(sessionAndUser);
        assertEquals(newExpirationDate, sessionAndUser.getSession().expiresAt);
    }

    @Test
    void testDeleteSession() {
        Session session = new Session("session3", 3, new Date(System.currentTimeMillis() + 10000));
        this.mockAuthDAO.setSession(session);

        this.mockAuthDAO.deleteSession("session3");
        assertNull(this.mockAuthDAO.getSessionAndUser("session3"));
    }

    @Test
    void testDeleteExpiredSessions() {
        Session session = new Session("session4", 4, new Date(System.currentTimeMillis() - 1000)); // Expired session
        this.mockAuthDAO.setSession(session);

        this.mockAuthDAO.deleteExpiredSessions();
        Session[] sessions = this.mockAuthDAO.getUserSessions(4);
        assertEquals(0, sessions.length);
    }

    @Test
    void testDeleteUserSessions() {
        Session sessionOne = new Session("session5", 5, new Date(System.currentTimeMillis() + 10000));
        Session sessionTwo = new Session("session6", 5, new Date(System.currentTimeMillis() + 10000));
        this.mockAuthDAO.setSession(sessionOne);
        this.mockAuthDAO.setSession(sessionTwo);

        this.mockAuthDAO.deleteUserSessions(5);
        Session[] sessions = this.mockAuthDAO.getUserSessions(5);
        assertEquals(0, sessions.length);
    }
}

