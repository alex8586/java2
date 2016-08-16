package lv.javaguru.java2.database;
import java.util.List;

public interface DAO<T> {

    long create(T t);

    void update(T t);

    void delete(T t);

    T getById(long id);

    List<T> getAll();
}
