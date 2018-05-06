package api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class ActionAdapter implements Action {

    public ActionAdapter() {
        super();
    }

    @Override
    public void onExecute(Statement statement) {

    }

    @Override
    public void onExecute(PreparedStatement statement) {

    }

    @Override
    public ResultSet onExecuteQuery(Statement statement) {
        return null;
    }
}
