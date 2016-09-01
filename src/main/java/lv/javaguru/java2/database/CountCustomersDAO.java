package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.CountCustomer;

import java.util.List;

public interface CountCustomersDAO {

    void createCountCustomer(CountCustomer countCustomer);

    void updateCountCustomer(CountCustomer countCustomer);

    int getCountByProduct(long productId);

    int getCountByCustomer(long userId);

    int getCountByProductAndVisitor(long productId, long userId);

    List getAllCount();

}
