package api;


import service.JDBCService;

import java.sql.*;

public class DBExecutor implements Executor {

    private JDBCService jdbcService;

    public DBExecutor(JDBCService jdbcService) {
        this.jdbcService = jdbcService;
    }

    @Override
    public void execute(Action action) {
        Statement statement = null;
        Connection connection = jdbcService.connect();
        try {
            statement = connection.createStatement();
            action.onExecute(statement);
            System.out.println("create execution completed!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(statement, connection);
        }
    }

    @Override
    public ResultSet executeQuery(Action action) {
        ResultSet resultSet;
        Statement statement = null;
        Connection connection = jdbcService.connect();
        try {
            statement = connection.createStatement();
            resultSet = action.onExecuteQuery(statement);
            System.out.println("create execution completed!");
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(statement, connection);
        }
        return null;
    }

    @Override
    public void execute(Action action, String sql) {
        PreparedStatement statement = null;
        Connection connection = jdbcService.connect();
        try {
            statement = connection.prepareStatement(sql);
            action.onExecute(statement);
            System.out.println("create execution completed!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(statement, connection);
        }
    }

    private void closeConnection(Statement statement, Connection connection) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        jdbcService.disconnect();
    }
}
