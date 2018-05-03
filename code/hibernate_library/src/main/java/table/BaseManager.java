package table;


import api.Executor;
import model.BaseModel;

public abstract class BaseManager<T extends BaseModel> implements DataManager<T>, HqlQueries<T> {

    protected Executor executor;

    public BaseManager(Executor executor) {
        this.executor = executor;
    }

}
