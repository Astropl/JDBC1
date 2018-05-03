package table;

import api.ActionAdapter;
import api.Executor;
import model.Author;
import org.hibernate.Session;

import java.util.List;

public class AuthorsManager extends BaseManager<Author> {

    public AuthorsManager(Executor executor) {
        super(executor);
    }

    @Override
    public void add(final Author object) {
        executor.execute(new ActionAdapter() {
            @Override
            public void onExecute(Session session) {
                session.save(object);
            }
        });
    }

    @Override
    public void update(Author object) {
    }

    @Override
    public void delete(Author object) {

    }

    @Override
    public List<Author> getList() {
        return executor.executeQuery(new ActionAdapter() {
            @Override
            public List onExecuteQuery(Session session) {
                return session.createQuery(getSelectQuery()).list();
            }
        });
    }

    @Override
    public String getSelectQuery() {
        return String.format("FROM %s", Author.TABLE_NAME);
    }
}
