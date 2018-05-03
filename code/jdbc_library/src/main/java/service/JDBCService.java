package service;


import java.sql.Connection;

/**
 * Service used for base jdbc operations
 */
public interface JDBCService {

    boolean connect();

    void disconnect();

    Connection getConnection();
}
