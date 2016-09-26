package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.CountUser;

import java.util.List;

public interface CountUsersDAO extends CrudDAO<CountUser> {

    long getCountByProductId(long productId);
    long getCountByUserId(long userId);
    long getSumCountFromAllTable();
    List getAllCount();
    CountUser getCountUserByUserIdProductId(long userId, long productId);
}
