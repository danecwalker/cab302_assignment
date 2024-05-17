package cabbypatty.cab302_assignment;

import cabbypatty.cab302_assignment.store.IAuthDAO;
import cabbypatty.cab302_assignment.store.IJournalDAO;
import cabbypatty.cab302_assignment.store.IUserDAO;

import java.io.IOException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;


/**
 * Configuration class for managing DAOs.
 * This class holds references to the user, authentication, and journal DAOs.
 */
public class Config {
    IUserDAO userDAO;
    IAuthDAO authDAO;
    IJournalDAO journalDAO;

    /**
     * Constructor for Config.
     * Initializes the configuration with the provided DAOs.
     *
     * @param userDAO The user DAO.
     * @param authDAO The authentication DAO.
     * @param journalDAO The journal DAO.
     * @throws CertificateException If a certificate error occurs.
     * @throws IOException If an I/O error occurs.
     * @throws NoSuchAlgorithmException If a requested cryptographic algorithm is not available in the environment.
     */
    public Config(IUserDAO userDAO, IAuthDAO authDAO, IJournalDAO journalDAO) throws CertificateException, IOException, NoSuchAlgorithmException {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
        this.journalDAO = journalDAO;
    }

    /**
     * Gets the user DAO.
     *
     * @return The user DAO.
     */
    public IUserDAO getUserDAO() {
        return userDAO;
    }

    /**
     * Gets the authentication DAO.
     *
     * @return The authentication DAO.
     */
    public IAuthDAO getAuthDAO() {
        return authDAO;
    }

    /**
     * Gets the journal DAO.
     *
     * @return The journal DAO.
     */

    public IJournalDAO getJournalDAO() {
        return journalDAO;
    }
}
