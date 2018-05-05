package service;


import config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLService implements JDBCService {

    private Config config;
    private Connection connection = null;

    public PostgreSQLService(Config config) {
        this.config = config;
    }

    @Override
    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    @Override
    public void disconnect() {
        if (connection == null) {
            System.out.println("No established connection");
            return;
        }
        try {
            connection.close();
            System.out.println("Disconnect from PostgreSQL server successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
