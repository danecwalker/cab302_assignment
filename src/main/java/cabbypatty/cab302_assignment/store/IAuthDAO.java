package cabbypatty.cab302_assignment.store;

import cabbypatty.cab302_assignment.model.Session;
import cabbypatty.cab302_assignment.model.SessionAndUser;

import java.util.Date;

/**
 * Interface for handling authentication-related data access operations.
 */
public interface IAuthDAO {

    /**
     * Deletes all expired sessions from the data store.
     */
    void deleteExpiredSessions();

    /**
     * Deletes a specific session based on the session ID.
     *
     * @param sessionId the ID of the session to delete
     */
    void deleteSession(String sessionId);

    /**
     * Deletes all sessions associated with a specific user.
     *
     * @param userId the ID of the user whose sessions are to be deleted
     */
    void deleteUserSessions(Integer userId);

    /**
     * Retrieves a session and associated user details based on the session ID.
     *
     * @param sessionId the ID of the session to retrieve
     * @return a SessionAndUser object containing session and user information
     */
    SessionAndUser getSessionAndUser( String sessionId );

    /**
     * Retrieves all sessions associated with a specific user.
     *
     * @param userId the ID of the user whose sessions are to be retrieved
     * @return an array of Session objects associated with the user
     */
    Session[] getUserSessions(Integer userId);

    /**
     * Saves or updates a session in the data store.
     *
     * @param session the session to be saved or updated
     */
    void setSession(Session session);

    /**
     * Updates the expiration date of a specific session.
     *
     * @param sessionId the ID of the session to update
     * @param expiresAt the new expiration date for the session
     */
    void updateSessionExpiration(String sessionId , Date expiresAt );
}
