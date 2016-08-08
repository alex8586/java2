package lv.javaguru.java2.database.jdbc;
import lv.javaguru.java2.database.DBException;
import java.util.List;

public interface DAO<T> {

    void createWithId(T t) throws DBException;

    long createReturnId(T t) throws DBException;

    void update(T t) throws DBException;

    void delete(T t) throws DBException;

    T getById(long id) throws DBException;

    List<T> getAll() throws DBException;
}
