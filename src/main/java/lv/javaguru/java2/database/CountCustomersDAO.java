package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.CountCustomer;

import java.util.List;

public interface CountCustomersDAO {

    long create(CountCustomer countCustomer);

    void update(CountCustomer countCustomer);

    CountCustomer getById(long id);

    int getCountByProduct(long productId);

    int getCountByCustomer(long userId);

    int getCountByProductAndVisitor(long productId, long userId);

    List getAllCount();

}
