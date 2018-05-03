package table;

import model.BaseModel;

import java.util.List;

/**
 * Interface used for base data operation - CRUD
 *
 * @param <T> BaseModel details object
 */
public interface DataManager<T extends BaseModel> {

    void add(T object);

    void update(T object);

    void delete(T object);

    List<T> getList();

}
