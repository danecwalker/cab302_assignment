package cabbypatty.cab302_assignment.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AuthDAOTest {
    private MockAuthDAO mockAuthDAO;

    @BeforeEach
    void setUp() {
        mockAuthDAO = new MockAuthDAO();
    }

    @Test
    void testAddAndRetrieveSession() {
        Session session = new Session("session1", 1, new Date(System.currentTimeMillis() + 10000)); // 10 seconds from now
        mockAuthDAO.setSession(session);

        // Check if the session can be retrieved
        Session[] sessions = mockAuthDAO.getUserSessions(1);
        assertEquals(1, sessions.length);
        assertEquals("session1", sessions[0].sessionID);
    }

    @Test
    void testUpdateSessionExpiration() {
        Session session = new Session("session2", 2, new Date());
        mockAuthDAO.setSession(session);

        // Update the expiration date
        Date newExpirationDate = new Date(System.currentTimeMillis() + 20000); // 20 seconds from now
        mockAuthDAO.updateSessionExpiration("session2", newExpirationDate);

        SessionAndUser sessionAndUser = mockAuthDAO.getSessionAndUser("session2");
        assertNotNull(sessionAndUser);
        assertEquals(newExpirationDate, sessionAndUser.getSession().expiresAt);
    }

    @Test
    void testDeleteSession() {
        Session session = new Session("session3", 3, new Date(System.currentTimeMillis() + 10000));
        mockAuthDAO.setSession(session);

        mockAuthDAO.deleteSession("session3");
        assertNull(mockAuthDAO.getSessionAndUser("session3"));
    }

    @Test
    void testDeleteExpiredSessions() {
        Session session = new Session("session4", 4, new Date(System.currentTimeMillis() - 1000)); // Expired session
        mockAuthDAO.setSession(session);

        mockAuthDAO.deleteExpiredSessions();
        Session[] sessions = mockAuthDAO.getUserSessions(4);
        assertEquals(0, sessions.length);
    }

    @Test
    void testDeleteUserSessions() {
        Session sessionOne = new Session("session5", 5, new Date(System.currentTimeMillis() + 10000));
        Session sessionTwo = new Session("session6", 5, new Date(System.currentTimeMillis() + 10000));
        mockAuthDAO.setSession(sessionOne);
        mockAuthDAO.setSession(sessionTwo);

        mockAuthDAO.deleteUserSessions(5);
        Session[] sessions = mockAuthDAO.getUserSessions(5);
        assertEquals(0, sessions.length);
    }
}

