package table;

import model.BaseModel;

public interface HqlQueries<T extends BaseModel> {

    String getSelectQuery();
}
