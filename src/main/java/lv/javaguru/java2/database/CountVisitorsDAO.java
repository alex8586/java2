package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.CountVisitor;

import java.util.List;

public interface CountVisitorsDAO {

    void createCountVisitor(CountVisitor countVisitor);

    void updateCountVisitor(CountVisitor countVisitor);

    int getCountByProduct(long productId);

    int getCountByIp(String ip);

    int getCountByProductAndVisitor(long productId, long userId);

    public List getAllCount();

}
