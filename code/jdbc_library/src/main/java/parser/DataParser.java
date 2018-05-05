package parser;


import model.BaseModel;

import java.sql.ResultSet;
import java.util.List;

public interface DataParser<T extends BaseModel> {

    List<T> parseToList(ResultSet resultSet);
}
