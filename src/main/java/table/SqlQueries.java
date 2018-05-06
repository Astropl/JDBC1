package table;


import model.BaseModel;

public interface SqlQueries<T extends BaseModel> {

    String getCreateTableQuery();

    String getSelectQuery();

    String getInsertQuery(T object);

    String getUpdateQuery();
}
