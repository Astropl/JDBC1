package table;

import api.ActionAdapter;
import api.Executor;
import config.Config;
import model.Author;
import parser.DataParser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AuthorsManager extends BaseManager<Author> {

    public AuthorsManager(Config config, Executor executor, DataParser<Author> dataParser) {
        super(config, executor, dataParser);
    }

    @Override
    public void createRepository() {
        executor.execute(new ActionAdapter() {
            @Override
            public void onExecute(Statement statement) {
                try {
                    System.out.println("Method execution");
                    statement.executeUpdate(getCreateTableQuery());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void add(final Author object) {
        executor.execute(new ActionAdapter() {
            @Override
            public void onExecute(Statement statement) {
                try {
                    statement.executeUpdate(getInsertQuery(object));
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    @Override
    public void update(final Author object) {
        executor.execute(new ActionAdapter() {
            @Override
            public void onExecute(PreparedStatement statement) {
                try {
                    statement.setString(1, object.getFirstName());
                    statement.setString(2, object.getLastName());
                    statement.setInt(3, object.getYearOfBirth());
                    statement.setString(4, object.getId());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }, getUpdateQuery());
    }

    @Override
    public List<Author> getList() {
        ResultSet resultSet = executor.executeQuery(new ActionAdapter() {
            @Override
            public ResultSet onExecuteQuery(Statement statement) {
                try {
                    return statement.executeQuery(getSelectQuery());
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            }
        });
        return parser.parseToList(resultSet);
    }

    @Override
    public String getCreateTableQuery() {
        return String.format("create table %s%s (%s varchar(32) NOT NULL, %s varchar(32) NOT NULL, %s varchar(50) NOT NULL, " +
                        "%s integer NOT NULL, PRIMARY KEY (%s))",
                config.getDbName(), Author.AUTHOR_TABLE, Author.AUTHOR_ID_COLUMN, Author.FIRST_NAME_COLUMN,
                Author.LAST_NAME_COLUMN, Author.YEAR_OF_BIRTH_COLUMN,
                Author.AUTHOR_ID_COLUMN);
    }

    @Override
    public String getSelectQuery() {
        return String.format("select %s, %s, %s, %s from %s%s",
                Author.AUTHOR_ID_COLUMN,
                Author.FIRST_NAME_COLUMN,
                Author.LAST_NAME_COLUMN,
                Author.YEAR_OF_BIRTH_COLUMN,
                config.getDbName(),
                Author.AUTHOR_TABLE);
    }

    @Override
    public String getInsertQuery(Author object) {
        return String.format("insert into %s%s values('%s', '%s', '%s', '%d')",
                config.getDbName(), Author.AUTHOR_TABLE, object.getId(), object.getFirstName(),
                object.getLastName(), object.getYearOfBirth());
    }

    @Override
    public String getUpdateQuery() {
        return String.format("update %s%s set %s = ?, %s = ?, %s = ? WHERE %s = ?",
                config.getDbName(), Author.AUTHOR_TABLE, Author.FIRST_NAME_COLUMN, Author.LAST_NAME_COLUMN,
                Author.YEAR_OF_BIRTH_COLUMN, Author.AUTHOR_ID_COLUMN);
    }
}
