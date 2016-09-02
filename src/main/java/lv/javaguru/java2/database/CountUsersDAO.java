package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.CountUser;

import java.util.List;

public interface CountUsersDAO {

    long create(CountUser countUser);

    void update(CountUser countUser);

    void delete(CountUser countUser);

    CountUser getById(long id);

    int getCountByProductId(long productId);

    int getCountByUserId(long userId);

    int getCountByProductIdAndUserId(long productId, long userId);

    List getAllCount();

}
