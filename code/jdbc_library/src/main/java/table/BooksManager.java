package table;

import api.Executor;
import config.Config;
import model.Book;
import parser.DataParser;

import java.util.List;

public class BooksManager extends BaseManager<Book> {

    public BooksManager(Config config, Executor executor, DataParser<Book> dataParser) {
        super(config, executor, dataParser);
    }

    @Override
    public void createRepository() {
    }

    @Override
    public void add(Book object) {
    }

    @Override
    public void update(Book object) {
    }

    @Override
    public List<Book> getList() {
        return null;
    }

    @Override
    public String getCreateTableQuery() {
        return null;
    }

    @Override
    public String getSelectQuery() {
        return null;
    }

    @Override
    public String getInsertQuery(Book object) {
        return null;
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }
}
