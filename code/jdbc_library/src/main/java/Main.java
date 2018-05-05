import api.DBExecutor;
import api.Executor;
import config.Config;
import model.Author;
import model.Book;
import model.BookType;
import service.JDBCService;
import service.PostgreSQLService;
import table.AuthorsManager;
import table.BooksManager;
import table.DataManager;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Config config = new Config("library", "jdbc:postgresql://localhost/library", "pbrzozowski", "pbrzozowski");
        JDBCService dbService = new PostgreSQLService(config);
        Executor executor = new DBExecutor(dbService);
        DataManager<Author> authorManager = new AuthorsManager(config, executor, null);
        DataManager<Book> bookManager = new BooksManager(config, executor, null);
        authorManager.createRepository();
        bookManager.createRepository();
        authorManager.add(new Author("1", "Piotr", "Brzozowski", 1992));
        authorManager.add(new Author("2", "Krzysztof", "Wolski", 1993));
        bookManager.add(new Book("1", "1", "Star Wars", BookType.FANTASY));
        bookManager.add(new Book("2", "2", "XXX", BookType.COMICS));
        authorManager.update(new Author("1", "Piotr", "Nowak", 1992));
        bookManager.update(new Book("1", "2", "WWWW", BookType.FANTASY));
        List<Book> bookList = bookManager.getList();
        List<Author> authorList = authorManager.getList();
        for (Book book : bookList) {
            System.out.println(book.toString());
        }
        for (Author author : authorList)
            System.out.println(author.toString());
        }
}

