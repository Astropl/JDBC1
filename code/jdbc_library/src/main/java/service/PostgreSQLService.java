package service;

import config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLService implements JDBCService{

    private Config config;
    private Connection connection;

    public PostgreSQLService(Config config) {
        this.config = config;
    }

    @Override
    public boolean connect() {
        try {
            //uncomment if connection not working properly: choose mysql or postgresql according to choosedn jdbc driver
            //Class.forName("org.postgresql.Driver").newInstance();
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword());
            System.out.println("DB connection created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void disconnect() {
        try {
            connection.close();
            System.out.println("DB connection closed!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connection = null;
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
