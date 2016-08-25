package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.BaseEntity;

import java.util.List;

public interface CrudDAO<T extends BaseEntity> {
    long create(T t);

    void update(T t);

    void delete(T t);

    T getById(long id);

    List<T> getAll();
}
