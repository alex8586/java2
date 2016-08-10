package lv.javaguru.java2.database;
import java.util.List;

public interface DAO<T> {

    long create(T t) throws DBException;

    void update(T t) throws DBException;

    void delete(T t) throws DBException;

    T getById(long id) throws DBException;

    List<T> getAll() throws DBException;
}
