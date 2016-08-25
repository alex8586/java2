package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.User;

public interface UserDAO extends CrudDAO<User> {
    User getByEmail(String email);
}
