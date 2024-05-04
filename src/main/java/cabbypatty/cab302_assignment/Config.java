package cabbypatty.cab302_assignment;

import cabbypatty.cab302_assignment.store.IAuthDAO;
import cabbypatty.cab302_assignment.store.IUserDAO;

import java.io.IOException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class Config {
    IUserDAO userDAO;
    IAuthDAO authDAO;

    public Config(IUserDAO userDAO, IAuthDAO authDAO) throws CertificateException, IOException, NoSuchAlgorithmException {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }

    public IUserDAO getUserDAO() {
        return userDAO;
    }

    public IAuthDAO getAuthDAO() {
        return authDAO;
    }
}
