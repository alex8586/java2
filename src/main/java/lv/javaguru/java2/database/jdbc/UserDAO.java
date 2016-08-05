package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.User;

import java.util.List;

public interface UserDAO {

    void createUserWithId(User user) throws DBException;

    long createUserReturnId(User user) throws DBException;

    void updateUser(User user) throws DBException;

    void deleteUser(User user) throws DBException;

    User getUserById(long id) throws DBException;

    List<User> getAllUsers() throws DBException;
}
