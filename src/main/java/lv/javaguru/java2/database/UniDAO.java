package lv.javaguru.java2.database;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface UniDAO<T,PK extends Serializable> {

    T create() throws SQLException;
    /** Создает новую запись, соответствующую объекту object */
    T persist(T object)  throws SQLException;

    /** Возвращает объект соответствующий записи с первичным ключом key или null */

    T getByPK(int key) throws SQLException;

    /** Сохраняет состояние объекта  в базе данных */
     void update(T object) throws SQLException;

    /** Удаляет запись об объекте из базы данных */
    void delete(T object) throws SQLException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */

    List<T> getAll() throws SQLException;




}
