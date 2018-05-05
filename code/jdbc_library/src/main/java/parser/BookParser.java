package parser;


import model.Book;
import model.BookType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookParser implements DataParser<Book> {

    @Override
    public List<Book> parseToList(ResultSet resultSet) {
        List<Book> bookList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String id = resultSet.getString(Book.BOOK_ID_COLUMN);
                String authorId = resultSet.getString(Book.AUTHOR_ID_COLUMN);
                String bookName = resultSet.getString(Book.BOOK_NAME_COLUMN);
                BookType bookType = BookType.parseType(resultSet.getString(Book.BOOK_TYPE_COLUMN));
                bookList.add(new Book(id, authorId, bookName, bookType));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookList;
    }
}
