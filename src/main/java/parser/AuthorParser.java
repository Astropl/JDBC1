package parser;


import model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorParser implements DataParser<Author> {

    @Override
    public List<Author> parseToList(ResultSet resultSet) {
        List<Author> authorList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String id = resultSet.getString(Author.AUTHOR_ID_COLUMN);
                String firstName = resultSet.getString(Author.FIRST_NAME_COLUMN);
                String lastName = resultSet.getString(Author.LAST_NAME_COLUMN);
                int yearOfBirth = resultSet.getInt(Author.YEAR_OF_BIRTH_COLUMN);
                authorList.add(new Author(id, firstName, lastName, yearOfBirth));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return authorList;
    }
}
