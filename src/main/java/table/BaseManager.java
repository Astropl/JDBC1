package table;


import api.Executor;
import config.Config;
import model.BaseModel;
import parser.DataParser;

public abstract class BaseManager<T extends BaseModel> implements DataManager<T>, SqlQueries<T> {

    protected Executor executor;
    protected Config config;
    protected DataParser<T> parser;

    public BaseManager(Config config, Executor executor, DataParser<T> parser) {
        this.config = config;
        this.executor = executor;
        this.parser = parser;
    }

}
