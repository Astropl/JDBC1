package table;

import api.Executor;
import config.Config;
import model.Author;
import parser.DataParser;

import java.util.List;

public class AuthorsManager extends BaseManager<Author> {

    public AuthorsManager(Config config, Executor executor, DataParser<Author> dataParser) {
        super(config, executor, dataParser);
    }

    @Override
    public void createRepository() {
    }

    @Override
    public void add(Author object) {
    }

    @Override
    public void update(Author object) {
    }

    @Override
    public List<Author> getList() {
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
    public String getInsertQuery(Author object) {
        return null;
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }
}
