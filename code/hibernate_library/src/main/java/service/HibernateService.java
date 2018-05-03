package service;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Service used for base hibernate operations
 */
public interface HibernateService {

    boolean connect(String configFile);

    void disconnect();

    SessionFactory getConnection();

    void openSession();

    void closeSession();

    Session getSession();
}
