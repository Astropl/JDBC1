package api;


import java.sql.ResultSet;

public interface Executor {

    void execute(Action action);

    ResultSet executeQuery(Action action);

    void execute(Action action, String sql);
}
