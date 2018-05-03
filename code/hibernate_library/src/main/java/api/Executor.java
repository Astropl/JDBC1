package api;


import java.util.List;

/**
 * Base execution interface used for data operation
 */
public interface Executor {

    void execute(Action action);

    List executeQuery(Action action);
}
