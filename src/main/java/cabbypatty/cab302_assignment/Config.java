package cabbypatty.cab302_assignment;

import cabbypatty.cab302_assignment.store.IAuthDAO;
import cabbypatty.cab302_assignment.store.IJournalDAO;
import cabbypatty.cab302_assignment.store.IUserDAO;

import java.io.IOException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class Config {
    IUserDAO userDAO;
    IAuthDAO authDAO;
    IJournalDAO journalDAO;

    public Config(IUserDAO userDAO, IAuthDAO authDAO, IJournalDAO journalDAO) throws CertificateException, IOException, NoSuchAlgorithmException {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
        this.journalDAO = journalDAO;
    }

    public IUserDAO getUserDAO() {
        return userDAO;
    }

    public IAuthDAO getAuthDAO() {
        return authDAO;
    }

    public IJournalDAO getJournalDAO() {
        return journalDAO;
    }
}
