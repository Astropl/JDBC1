package table;

import model.BaseModel;

import java.util.List;

public interface DataManager<T extends BaseModel> {

    void createRepository();

    void add(T object);

    void update(T object);

    List<T> getList();

}
