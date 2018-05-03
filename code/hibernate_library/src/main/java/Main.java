import api.DBExecutor;
import api.Executor;
import model.Author;
import model.Book;
import model.BookType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.HibernateService;
import service.MySQLService;
import table.AuthorsManager;
import table.BooksManager;
import table.DataManager;

import java.util.Iterator;
import java.util.List;


public class Main {

    private static HibernateService hibernateService;

    public static void main(String[] args) {
        hibernateService = new MySQLService();
        hibernateService.connect("hibernate.cfg.xml");
        Executor executor = new DBExecutor(hibernateService);
        DataManager<Author> authorDataManager = new AuthorsManager(executor);
        DataManager<Book> bookDataManager = new BooksManager(executor);
        Author authorP = new Author();
        authorP.setFirstName("test");
        authorP.setLastName("test");
        authorP.setYearOfBirth(1989);
        authorDataManager.add(authorP);
        Book book1 = new Book();
        book1.setName("Star Wars");
        book1.setAuthor(authorP);
        book1.setBookType(BookType.FANTASY);
        Book book2 = new Book();
        book2.setName("Batman");
        book2.setAuthor(authorP);
        book2.setBookType(BookType.COMICS);
        bookDataManager.add(book1);
        bookDataManager.add(book2);
        for (Author author: authorDataManager.getList()) {
            System.out.println(author.toString());
        }
        for (Book book : bookDataManager.getList()) {
            System.out.println(book.toString());
        }
    }



    /* Method to  READ all the employees */
    public static void listAuthors( ){
        hibernateService.openSession();
        Session session = hibernateService.getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List authors = session.createQuery("FROM Author").list();
            for (Iterator iterator =
                 authors.iterator(); iterator.hasNext();){
                Author author = (Author) iterator.next();
                System.out.println(" Id: " + author.getId());
                System.out.println(" First Name: " + author.getFirstName());
                System.out.println("  Last Name: " + author.getLastName());
                System.out.println("  year of birth: " + author.getYearOfBirth());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            hibernateService.closeSession();
        }
    }

    /* Method to  READ all the employees */
    public static void listBooks( ){
        hibernateService.openSession();
        Session session = hibernateService.getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List<Author> authors = session.createQuery("FROM Book").list();
            for (Iterator iterator =
                 authors.iterator(); iterator.hasNext();){
                Book book = (Book) iterator.next();
                System.out.println(" Id: " + book.getId());
                System.out.println(" Book name: " + book.getName());
                System.out.println("  Book type: " + book.getBookType());
                System.out.println("  author: " + book.getAuthor().toString());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            hibernateService.closeSession();
        }
    }

    /* Method to CREATE an employee in the database */
    public static void addAuthor(String fname, String lname, int year_of_birth){
        hibernateService.openSession();
        Session session = hibernateService.getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Author author = new Author();
            author.setFirstName(fname);
            author.setLastName(lname);
            author.setYearOfBirth(year_of_birth);
            session.save(author);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            hibernateService.closeSession();
        }
    }


    /* Method to CREATE an employee in the database */
    public static void addBook(String name, String authorId, BookType bookType){
        /*hibernateService.openSession();
        Session session = hibernateService.getSession();
        Transaction tx = null;
        try{
            Author author = new Author();
            author.setId(authorId);
            tx = session.beginTransaction();
            Book book = new Book();
            book.setName(name);
            book.setAuthor(author);
            book.setBookType(bookType);
            session.save(book);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            hibernateService.closeSession();
        }*/
        Author author = new Author();
        author.setId(authorId);
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setBookType(bookType);
        DataManager<Book> dataManager = new BooksManager(new DBExecutor(hibernateService));
        dataManager.add(book);
    }
}
