package service;


import java.sql.Connection;

public interface JDBCService {

    Connection connect();

    void disconnect();

    Connection getConnection();
}
