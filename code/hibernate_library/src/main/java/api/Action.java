package api;

import model.BaseModel;
import org.hibernate.Session;

import java.util.List;

/**
 * Action used by {@link Executor}
 */
public interface Action<T extends BaseModel> {
    void onExecute(Session session);

    List<T> onExecuteQuery(Session session);
}
