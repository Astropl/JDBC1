package table;

import api.ActionAdapter;
import api.Executor;
import model.Book;
import org.hibernate.Session;

import java.util.List;

public class BooksManager extends BaseManager<Book> {

    private static int test =0;

    public BooksManager(Executor executor) {
        super(executor);
        test++;
    }

    @Override
    public void add(final Book object) {
        executor.execute(new ActionAdapter() {
            @Override
            public void onExecute(Session session) {
                session.save(object);
            }
        });
    }

    @Override
    public void update(Book object) {
    }

    @Override
    public void delete(Book object) {

    }

    @Override
    public List<Book> getList() {
        return executor.executeQuery(new ActionAdapter() {
            @Override
            public List onExecuteQuery(Session session) {
                return session.createQuery(getSelectQuery()).list();
            }
        });
    }

    @Override
    public String getSelectQuery() {
        return String.format("FROM %s", Book.TABLE_NAME);
    }
}
