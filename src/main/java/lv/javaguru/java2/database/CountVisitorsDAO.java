package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.CountVisitor;

import java.util.List;

public interface CountVisitorsDAO {

    long create(CountVisitor countVisitor);

    void update(CountVisitor countVisitor);

    void delete(CountVisitor countVisitor);

    CountVisitor getById(long id);

    long getCountByProductId(long productId);

    long getCountByIp(String ip);

    long getSumCountFromAllTable();

    List getAllCount();

    CountVisitor getCountUserByUserIpProductId(String ip, long productId);

}
