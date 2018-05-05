package table;


import api.ActionAdapter;
import api.Executor;
import config.Config;
import model.Author;
import model.Book;
import parser.DataParser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BooksManager extends BaseManager<Book> {

    public BooksManager(Config config, Executor executor, DataParser<Book> dataParser) {
        super(config, executor, dataParser);
    }

    @Override
    public void createRepository() {
        executor.execute(new ActionAdapter() {
            @Override
            public void onExecute(Statement statement) {
                try {
                    statement.executeUpdate(getCreateTableQuery());
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    @Override
    public void add(final Book object) {
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
    public void update(final Book object) {
        executor.execute(new ActionAdapter() {
            @Override
            public void onExecute(PreparedStatement statement) {
                try {
                    statement.setString(1, object.getAuthorId());
                    statement.setString(2, object.getName());
                    statement.setString(3, object.getBookType().toString());
                    statement.setString(5, object.getId());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }, getUpdateQuery());
    }

    @Override
    public List<Book> getList() {
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
        return String.format("create table %s%s (%s varchar(32) NOT NULL, %s varchar(32), %s varchar(50), %s varchar(32), " +
                        "PRIMARY KEY (%s), FOREIGN KEY (%s) REFERENCES %s%s (%s))", config.getDbName(),
                Book.BOOK_TABLE, Book.BOOK_ID_COLUMN, Book.AUTHOR_ID_COLUMN, Book.BOOK_NAME_COLUMN, Book.BOOK_TYPE_COLUMN,
                Book.BOOK_ID_COLUMN, Book.AUTHOR_ID_COLUMN, config.getDbName(), Author.AUTHOR_TABLE, Author.AUTHOR_ID_COLUMN);
    }

    @Override
    public String getSelectQuery() {
        return String.format("select %s, %s, %s, %s from %s%s",
                Book.BOOK_ID_COLUMN,
                Book.AUTHOR_ID_COLUMN,
                Book.BOOK_NAME_COLUMN,
                Book.BOOK_TYPE_COLUMN,
                config.getDbName(),
                Book.BOOK_TABLE);
    }

    @Override
    public String getInsertQuery(Book object) {
        return String.format("insert into %s%s values('%s', '%s', '%s', '%s')",
                config.getDbName(), Book.BOOK_TABLE, object.getId(), object.getAuthorId(), object.getName(),
                object.getBookType().toString());
    }

    @Override
    public String getUpdateQuery() {
        return String.format("update %s%s set %s = ?, %s = ?, %s = ? WHERE %s = ?",
                config.getDbName(), Book.BOOK_TABLE, Book.AUTHOR_ID_COLUMN, Book.BOOK_NAME_COLUMN, Book.BOOK_TYPE_COLUMN,
                Book.BOOK_ID_COLUMN);
    }
}
