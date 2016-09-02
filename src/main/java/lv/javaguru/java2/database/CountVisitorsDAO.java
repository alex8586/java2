package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.CountVisitor;

import java.util.List;

public interface CountVisitorsDAO {

    long create(CountVisitor countVisitor);

    void update(CountVisitor countVisitor);

    void delete(CountVisitor countVisitor);

    CountVisitor getById(long id);

    int getCountByProductId(long productId);

    int getCountByIp(String ip);

    int getCountByProductIdAndIp(long productId, String ip);

    List getAllCount();

}
